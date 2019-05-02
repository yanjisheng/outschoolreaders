package cn.edu.jju.outschoolreaders.controller;

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
	
	@PostMapping("/reader/addReader")
	public void addReader(Reader reader) {
		readerService.addReader(reader);
	}
	
	@GetMapping("/reader/getReader")
	public Reader getReader(String cardNo, String name) {
		return readerService.getReaderByCardNoAndName(cardNo, name);
	}
}
