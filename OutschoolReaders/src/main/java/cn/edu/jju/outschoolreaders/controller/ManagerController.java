package cn.edu.jju.outschoolreaders.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.jju.outschoolreaders.model.Manager;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.ReaderQuery;
import cn.edu.jju.outschoolreaders.service.ManagerService;
import cn.edu.jju.outschoolreaders.service.ReaderService;
import cn.edu.jju.outschoolreaders.util.PageResult;

/**
 * 管理员操作
 * @author yanjisheng
 *
 */
@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ReaderService readerService;
	
	/**
	 * 管理员登录
	 */
	@PostMapping("/manager/login")
	public String login(String loginName, String password) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager != null) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 管理员主页查询读者列表
	 */
	@PostMapping("/manager/getReaders")
	public PageResult<Reader> getReaders(String loginName, String password, ReaderQuery query) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return new PageResult<>(PageResult.UNAUTHORIZED, new ArrayList<>());
		}
		return readerService.queryReaders(query);
	}
	
	/**
	 * 管理员查询读者详细信息
	 */
	@PostMapping("/manager/getReaderById")
	public Reader getReaderById(String loginName, String password, Integer id) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		Reader nullReader = new Reader();
		nullReader.setId(-1);
		if(manager == null) {			
			return nullReader;
		}
		Reader reader = readerService.getReaderById(id);
		if(reader == null) {
			return nullReader;
		}
		return reader;
	}
}
