package com.example.HiddenGem.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	@ResponseBody
	@PostMapping("/imageUpload")
	public String imageUpload(MultipartHttpServletRequest req) {
		String callback = req.getParameter("CKEditorFuncNum"); 		// 1
		String error = "";
		String url = null;
		Map<String, MultipartFile> map = req.getFileMap();
		for (Map.Entry<String, MultipartFile> pair: map.entrySet()) {
			MultipartFile file = pair.getValue();
			String filename = file.getOriginalFilename();
			int idx = filename.lastIndexOf(".");
			filename = System.currentTimeMillis() + filename.substring(idx);
			String uploadPath = uploadDir + "image/" + filename;
			try {
				file.transferTo(new File(uploadPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			url = "/hiddengem/file/download/image/" + filename;
		}
		
		String ajaxResponse = "<script>"
				+ "	window.parent.CKEDITOR.tools.callFunction("
				+ 		callback + ", '" + url + "', '" + error + "'"
				+ "	);"
				+ "</script>";
		return ajaxResponse;
	}
	
}