package cn.edu.jju.outschoolreaders.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.service.ReaderService;

/**
 * 读者操作
 * @author yanjisheng
 *
 */
@RestController
public class ReaderController {

	@Autowired
	private ReaderService readerService;
	
	/**
	 * 新读者注册
	 */
	@PostMapping("/reader/addReader")
	public void addReader(HttpServletResponse response, Reader reader) throws IOException {
		readerService.addReader(reader);
		response.sendRedirect("/reader/new-reader-success.html");
	}
	
	/**
	 * 读者查询入口
	 */
	@PostMapping("/reader/getReader")
	public void turnGetReader(HttpServletRequest request, HttpServletResponse response, 
			String cardNo, String name) throws IOException {
		Reader reader = readerService.getReaderByCardNoAndName(cardNo, name);
		if(reader == null) {
			response.sendRedirect("/reader/error.html");
		} else {
			String encodedName = URLEncoder.encode(name, "UTF-8");
			response.sendRedirect("/reader/info.html?cardNo="+cardNo+"&name="+encodedName);
		}
	}
	
	/**
	 * 获取读者详细信息
	 */
	@GetMapping("/reader/getReader")
	public Reader getReader(String cardNo, String name) {
		return readerService.getReaderByCardNoAndName(cardNo, name);
	}
}
