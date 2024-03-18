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

import com.example.HiddenGem.entity.BoardC;
import com.example.HiddenGem.entity.Like;
import com.example.HiddenGem.service.BoardCService;
import com.example.HiddenGem.service.LikeService;
import com.example.HiddenGem.util.JsonUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boardC")
public class BoardCController {
	@Autowired private BoardCService boardCService;
	@Autowired private LikeService likeService;
	@Autowired private JsonUtil jsonUtil;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/listC")
	public String listC(@RequestParam(name="p", defaultValue="1") int page,
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
		
		return "boardC/listC";
	}
	
	@GetMapping("/insertC")
	public String insertForm() {
		return "boardC/insertC";
	}
	
	@PostMapping("/insertC")
	public String insertProc(String title, String content, 
			MultipartHttpServletRequest req, HttpSession session) {
		String sessUid = (String) session.getAttribute("sessUid");
		List<MultipartFile> uploadFileList = req.getFiles("files");
		
		List<String> fileList = new ArrayList<>();
		for (MultipartFile part: uploadFileList) {
			// 첨부 파일이 없는 경우 - application/octet-stream
			if (part.getContentType().contains("octet-stream"))
				continue;
			
			String filename = part.getOriginalFilename();
			String uploadPath = uploadDir  + "upload/" + filename ;
			try {
				part.transferTo(new File(uploadPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(filename);
		}
		String files = jsonUtil.list2Json(fileList);
//		
		BoardC boardC = new BoardC(sessUid, title, content, files);
		boardCService.insertBoardC(boardC);
		return "redirect:/boardC/listC";
	}

	@GetMapping("/detailC/{cid}/{uid}")
	public String detailC(@PathVariable int cid, @PathVariable String uid, String option,
			HttpSession session, Model model) {
		// 본인이 조회한 경우 또는 댓글 작성후에는 조회수 증가시키지 않음
		String sessUid = (String) session.getAttribute("sessUid");
		if (!uid.equals(sessUid) && (option==null || option.equals("")))
			boardCService.increaseViewCount(cid);
		
		BoardC boardC = boardCService.getBoardC(cid);
		model.addAttribute("boardC", boardC);
		
		// 좋아요 처리
		Like like = likeService.getLike(cid, sessUid);
		if (like == null)
			session.setAttribute("likeClicked", 0);
		else
			session.setAttribute("likeClicked", like.getValue());
		model.addAttribute("count", boardC.getLikeCount());
		

		return "boardC/detailC";
	}
	
	@GetMapping("/deleteC/{cid}")
	public String deleteC(@PathVariable int cid, HttpSession session) {
		boardCService.deleteBoardC(cid);
		return "redirect:/boardC/listC?p=" + session.getAttribute("currentBoardPage");
	}
	

	// AJAX 처리
	@GetMapping("/like/{cid}")
	public String like(@PathVariable int cid, HttpSession session, Model model) {
		String sessUid = (String) session.getAttribute("sessUid");
		Like like = likeService.getLike(cid, sessUid);
		if (like == null) {
			likeService.insertLike(new Like(sessUid, cid, 1));
			session.setAttribute("likeClicked", 1);
		} else {
			int value = likeService.toggleLike(like);
			session.setAttribute("likeClicked", value);
		}
		int count = likeService.getLikeCount(cid);
		boardCService.updateLikeCount(cid, count);
		model.addAttribute("count", count);
		return "boardf/detail::#likeCount"; // 콜론 두개 : 자바의 람다식 표현 - 값이 바뀌는 것f
	}

}
