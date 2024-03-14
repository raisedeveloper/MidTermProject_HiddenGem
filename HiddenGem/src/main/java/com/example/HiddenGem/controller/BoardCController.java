 package com.example.HiddenGem.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.HiddenGem.entity.BoardC;
import com.example.HiddenGem.service.BoardCService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boardC")
public class BoardCController {
	@Autowired private BoardCService boardCService;
//	@Autowired private ReplyService replyService;
//	@Autowired private LikeService likeService;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/list")
	public String list(@RequestParam(name="p", defaultValue="1") int page,
				@RequestParam(name="f", defaultValue="title") String field,
				@RequestParam(name="q", defaultValue="") String query,
				HttpSession session, Model model) {
		List<BoardC> boardList = boardCService.getBoardCList(page, field, query);
		
		int totalBoardCount = boardCService.getBoardCCount(field, query);
		int totalPages = (int) Math.ceil(totalBoardCount / (double)BoardCService.COUNT_PER_PAGE);
		int startPage = (int) Math.ceil((page-0.5)/BoardCService.PAGE_PER_SCREEN - 1) * BoardCService.PAGE_PER_SCREEN + 1;
		int endPage = Math.min(totalPages, startPage + BoardCService.PAGE_PER_SCREEN - 1);
		List<Integer> pageList = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++)
			pageList.add(i);
		
		session.setAttribute("currentBoardPage", page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageList", pageList);
		
		return "boardC/list";
	}
	
	@GetMapping("/insert")
	public String insertForm() {
		return "boardC/insert";
	}
	
	@PostMapping("/insert")
	public String insertProc(String title, String content, 
			MultipartHttpServletRequest req, HttpSession session) {
		String sessUid = (String) session.getAttribute("sessUid");
		
		
		BoardC boardC = new BoardC(sessUid, title, content);
		boardCService.insertBoardC(boardC);
		return "redirect:/board/list";
	}

	@GetMapping("/detail/{bid}/{uid}")
	public String detail(@PathVariable int bid, @PathVariable String uid, String option,
			HttpSession session, Model model) {
		// 본인이 조회한 경우 또는 댓글 작성후에는 조회수 증가시키지 않음
		String sessUid = (String) session.getAttribute("sessUid");
		if (!uid.equals(sessUid) && (option==null || option.equals("")))
			boardCService.increaseViewCount(bid);
		
		BoardC boardC = boardCService.getBoardC(bid);
		model.addAttribute("boardC", boardC);
		
//		// 좋아요 처리
//		Like like = likeService.getLike(bid, sessUid);
//		if (like == null)
//			session.setAttribute("likeClicked", 0);
//		else
//			session.setAttribute("likeClicked", like.getValue());
//		model.addAttribute("count", board.getLikeCount());
//		
		// 댓글 처리
//		List<Reply> replyList = replyService.getReplyList(bid);
//		model.addAttribute("replyList", replyList);
		return "boardC/detail";
	}
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, HttpSession session) {
		boardCService.deleteBoardC(bid);
		return "redirect:/boardC/list?p=" + session.getAttribute("currentBoardPage");
	}
	
//	@PostMapping("/reply")
//	public String reply(int bid, String uid, String comment, HttpSession session) {
//		String sessUid = (String) session.getAttribute("sessUid");
//		int isMine = (sessUid.equals(uid)) ? 1 : 0;
//		Reply reply = new Reply(comment, sessUid, bid, isMine);
//		
//		replyService.insertReply(reply);
//		boardService.increaseReplyCount(bid);
//		
//		return "redirect:/boardC/detail/" + bid + "/" + uid + "?option=DNI";
//	}
//	
//	// AJAX 처리
//	@GetMapping("/like/{bid}")
//	public String like(@PathVariable int bid, HttpSession session, Model model) {
//		String sessUid = (String) session.getAttribute("sessUid");
//		Like like = likeService.getLike(bid, sessUid);
//		if (like == null) {
//			likeService.insertLike(new Like(sessUid, bid, 1));
//			session.setAttribute("likeClicked", 1);
//		} else {
//			int value = likeService.toggleLike(like);
//			session.setAttribute("likeClicked", value);
//		}
//		int count = likeService.getLikeCount(bid);
//		boardService.updateLikeCount(bid, count);
//		model.addAttribute("count", count);
//		return "boardC/detail::#likeCount"; // 콜론 두개 : 자바의 람다식 표현 - 값이 바뀌는 것
//	}
//
}
