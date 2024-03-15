package com.example.HiddenGem.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.HiddenGem.entity.User;
import com.example.HiddenGem.service.UserService;
import com.example.HiddenGem.util.ImageUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService uSvc;
	@Autowired
	private ImageUtil imageUtil;
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;

	@GetMapping("/register")
	public String registerForm() {
		return "user/register";
	}

	@PostMapping("/register")
	public String registerProc(MultipartHttpServletRequest req, Model model, String uid, String pwd, String pwd2,
			String uname, String email, String github, String insta, String location) {
		MultipartFile filePart = req.getFile("profile");
		String fileName = null;

		if (uSvc.getUserByUid(uid) != null) {
			model.addAttribute("msg", "사용자 아이디 중복");
			model.addAttribute("url", "/mid/user/register");
			return "common/alertMsg";
		}
		if (pwd.equals(pwd2) && pwd != null) {
			if (filePart.getContentType().contains("image")) {
				fileName = filePart.getOriginalFilename();
				String path = uploadDir + "profile/" + fileName;
				System.out.println(fileName);
				try {
					filePart.transferTo(new File(path));
				} catch (Exception e) {
					e.printStackTrace();
				}
				fileName = imageUtil.squareImage(uid, fileName);
			}
			User user = new User(uid, pwd, uname, email, fileName);
			uSvc.registerUser(user);
			model.addAttribute("msg", "등록을 마쳤습니다. 로그인 하세요.");
			model.addAttribute("url", "/mid/user/login");
			return "common/alertMsg";
		} else {
			model.addAttribute("msg", "비밀번호가 동일하지 않습니다. ");
			model.addAttribute("url", "/mid/user/register");
			return "common/alertMsg";
		}
	}

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model) {
		int result = uSvc.login(uid, pwd);
		switch (result) {
		case UserService.correct_login:
			User user = uSvc.getUserByUid(uid);
			session.setAttribute("sessUid", uid);
			session.setAttribute("sessUname", user.getUname());
			session.setAttribute("profile", user.getProfile());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("access", user.getAccess());
			
			// 환영 메시지
			log.info("Info Login: {}, {}", uid, user.getUname());
			model.addAttribute("msg", user.getUname() + "님 환영합니다.");
			model.addAttribute("url", "/mid/boardC/listC");
			break;

		case UserService.USER_NOT_EXIST:
			model.addAttribute("msg", "ID가 존재하지 않습니다. 회원가입 페이지로 이동합니다.");
			model.addAttribute("url", "/mid/user/register");
			break;

		case UserService.WRONG_PASSWORD:
			model.addAttribute("msg", "패스워드 입력이 올바르지 않습니다.");
			model.addAttribute("url", "/mid/user/login");
			break;
		}
		return "common/alertMsg";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
	
	@GetMapping("/test")
	public String test() {
		return "testpage";
	}
}