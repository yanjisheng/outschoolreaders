package cn.edu.jju.outschoolreaders.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.jju.outschoolreaders.model.Reader;

/**
 * 管理员操作
 * @author yanjisheng
 *
 */
@RestController
public class ManagerController {

	@PostMapping("/manager/getReaders")
	public List<Reader> getReaders() {
		//TODO
		return null;
	}
}
