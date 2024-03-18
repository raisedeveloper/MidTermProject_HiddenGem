package com.example.HiddenGem.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.HiddenGem.entity.BoardF;
import com.example.HiddenGem.entity.Like;
import com.example.HiddenGem.entity.Menu;
import com.example.HiddenGem.entity.Reply;
import com.example.HiddenGem.service.BoardFService;
import com.example.HiddenGem.service.LikeService;
import com.example.HiddenGem.service.MenuService;
import com.example.HiddenGem.service.ReplyService;
import com.example.HiddenGem.util.JsonUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boardf")
public class BoardFController {
	/*
	 * 변수 선언
	 */
	@Autowired
	private BoardFService boardFService;
	@Autowired
	private JsonUtil jsonUtil;
	@Autowired
	ReplyService replyService;
	@Autowired
	LikeService likeService;
	@Autowired
	MenuService menuService;

	// 다른 변수 더 넣을 공간

	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;

	/*
	 * 여기부터 구현
	 */

	/*
	 * list
	 */
	@GetMapping("/list") // 이름은 p로하고 값이 없으면 1로

	public String list(@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "f", defaultValue = "title") String field,
			@RequestParam(name = "q", defaultValue = "") String query, HttpSession session, Model model) {
		List<BoardF> boardFList = boardFService.getBoardFList(page, field, query);

		// pagenation
		int totalBoardCount = boardFService.getBoardFCount(field, query);

		// 자바의 기본값이 double이니 double 사용함
		int totalPages = (int) Math.ceil(totalBoardCount / (double) BoardFService.COUNT_PER_PAGE);

		int startPage = (int) Math.ceil((page - 0.5) / BoardFService.PAGE_PER_SCREEN - 1)
				* BoardFService.PAGE_PER_SCREEN + 1;
		int endPage = Math.min(totalPages, startPage + BoardFService.PAGE_PER_SCREEN - 1);
		List<String> pageList = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++) {
			pageList.add(String.valueOf(i));
		}

		// 파라미터 넘기기- 어디갔다가 다시오면 안나올 수 있음

		session.setAttribute("currentBoardPage", page);
		model.addAttribute("boardList", boardFList);
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);

		return "boardf/list";
	}

	/*
	 * insert
	 */

	@GetMapping("/insert")
	   public String insertForm(Model model) {
	      return "boardf/insert";
	   }

	   @PostMapping("/insert")
	   public String insertProc(String title, String foodCategory, String opening, String location, String tel,
	         String info, String content, String uid, MultipartHttpServletRequest req, HttpSession session) {

	      String sessUid = (String) session.getAttribute("sessUid");
	      String foodImg = content;
	 
	      // boardf에 들어가야 하는 것
	      BoardF board = new BoardF(title, foodCategory, opening, location, tel, info, sessUid, foodImg);
	      boardFService.insertBoardF(board);
	      return "redirect:/boardf/list";
	   }

	/*
	 * detail
	 */
	@SuppressWarnings("null")
	@GetMapping("/detail/{fid}/{uid}")
	public String detail(@PathVariable int fid, @PathVariable String uid, String option, HttpSession session,
			Model model) {
		// 본인이 조회 시 or 댓글 작성 후에는 조회수 증가 x
		String sessUid = (String) session.getAttribute("sessUid");
		if (!uid.equals(sessUid) && (option == null || option.equals(""))) {
			boardFService.increaseViewCount(fid);
		}

		BoardF boardf = boardFService.getBoardF(fid);
		String jsonFiles = boardf.getFoodImg();

		// jsonFiles 빈 거 방지, json을 파일로 만들어서 보냄
		if (!(jsonFiles == null || jsonFiles.equals(""))) {
			List<String> fileList = jsonUtil.json2List(jsonFiles);
			model.addAttribute("fileList", fileList);

		}

		model.addAttribute("boardf", boardf);

		// 좋아요 처리
		Like like = likeService.getLike(fid, sessUid);
		if (like == null) {
			session.setAttribute("likeClicked", 1);

		} else {
			session.setAttribute("likeClicked", like.getValue());
		}

		model.addAttribute("count", boardf.getLikeCount());

		/*
		 * reply 보여주기
		 */

		List<Reply> replyList = replyService.getReplyList(fid);
		model.addAttribute("replyList", replyList);

		/*
		 * 지도 보여주기
		 */
		model.addAttribute("address", boardf.getLocation());
		model.addAttribute("title", boardf.getTitle());

		/*
		 * 메뉴 보여주기
		 */
//		List<Menu> menuList = menuService.getMenuList(boardf.getTitle());
//		model.addAttribute("menuList" , menuList);
		Menu menu = menuService.getMenuByName(boardf.getTitle());
		String[] name = menu.getFood().split(", ");
		String[] price = menu.getPrice().split(", ");
		List<Menu> menuList = new ArrayList<>();

		for (int i = 0; i < price.length; i++) {
			menuList.add(new Menu(name[i], price[i]));
		}

		model.addAttribute("menuList", menuList);
		return "boardf/detail";
	}

	// AJAX 처리
	@GetMapping("/like/{fid}")
	public String like(@PathVariable int fid, HttpSession session, Model model) {
		String sessUid = (String) session.getAttribute("sessUid");
		Like like = likeService.getLike(fid, sessUid);
		if (like == null) {
			likeService.insertLike(new Like(sessUid, fid, 1));
			session.setAttribute("likeClicked", 1);
		} else {
			int value = likeService.toggleLike(like);
			session.setAttribute("likeClicked", value);
		}
		int count = likeService.getLikeCount(fid);
		boardFService.updateLikeCount(fid, count);
		model.addAttribute("count", count);
		return "boardf/detail::#likeCount"; // 콜론 두개 : 자바의 람다식 표현 - 값이 바뀌는 것f
	}

	/*
	 * reply 등록
	 */
	@PostMapping("/reply") // 로그인한 사람 누구인지 알기 위해 HttpSession session 사용
	public String reply(int fid, String uid, String comment, HttpSession session) {
		String sessUid = (String) session.getAttribute("sessUid");
		int isMine = (sessUid.equals(uid)) ? 1 : 0;
		Reply reply = new Reply(comment, isMine, sessUid, fid);

		replyService.insertReply(reply);
		boardFService.increaseReplyCount(fid);

		return "redirect:/boardf/detail/" + fid + "/" + uid + "?option=DNI";
	}

}
