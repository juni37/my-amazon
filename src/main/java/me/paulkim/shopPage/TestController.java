package me.paulkim.shopPage;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class TestController {

	@Resource(name="uploadPath")
	private String uploadPath;

	@RequestMapping(value = "/fileUpload.do", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest req) {
		String filename = "";
		int filesize = 0;
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile file = mr.getFile("filename");
		File target = new File(uploadPath, file.getOriginalFilename());
		if (file.getSize()>0) {
			try {
				file.transferTo(target);
			}catch(IOException e) {}
				filename = file.getOriginalFilename();
		  filesize = (int)file.getSize();
		}
		req.setAttribute("msg", "�����̸� : " + filename +" , ���ϻ����� : " + filesize +"bytes");
		return "message";
	}
}









