package cn.edu.jju.outschoolreaders.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Autowired
	private FileController fileController;
	
	/**
	 * 新读者注册
	 */
	@PostMapping("/reader/addReader")
	public void addReader(HttpServletResponse response, Reader reader, MultipartFile file) throws IOException {
		if(file != null && file.getSize() > 0) {
			String image = fileController.upload(file);
			if(!image.equals("File format not supported.")) {
				reader.setImage(image);
			}
		}
		readerService.addReader(reader);
		response.sendRedirect("/reader/new-reader-success.html");
	}
	
	/**
	 * 读者查询入口
	 */
	@PostMapping("/reader/getReader")
	public void turnGetReader(HttpServletRequest request, HttpServletResponse response, 
			String identityNo, String name) throws IOException {
		Reader reader = readerService.getReaderByIdentityNoAndName(null, identityNo, name);
		if(reader == null) {
			response.sendRedirect("/reader/error.html");
		} else {
			String encodedName = URLEncoder.encode(name, "UTF-8");
			response.sendRedirect("/reader/info.html?identityNo="+identityNo+"&name="+encodedName);
		}
	}
	
	/**
	 * 获取读者详细信息
	 */
	@GetMapping("/reader/getReader")
	public Reader getReader(Integer id, String identityNo, String name) {
		return readerService.getReaderByIdentityNoAndName(id, identityNo, name);
	}
	
	/**
	 * 修改读者信息
	 */
	@PostMapping("/reader/modifyReader")
	public void modifyReader(Reader readerTemp) {
		readerService.readerModify(readerTemp);		
	}
}
