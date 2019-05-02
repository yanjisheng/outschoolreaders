package cn.edu.jju.outschoolreaders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.dao.ManagerDao;

/**
 * 管理员信息
 * @author yanjisheng
 *
 */
@Service
public class ManagerService {

	@Autowired
	private ManagerDao managerDao;
}
