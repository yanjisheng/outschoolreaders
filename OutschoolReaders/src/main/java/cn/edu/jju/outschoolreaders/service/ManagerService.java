package cn.edu.jju.outschoolreaders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.dao.ManagerDao;
import cn.edu.jju.outschoolreaders.model.Manager;

/**
 * 管理员信息
 * @author yanjisheng
 *
 */
@Service
public class ManagerService {

	private static final Logger log = LoggerFactory.getLogger(ManagerService.class);
	
	@Autowired
	private ManagerDao managerDao;
	
	public Manager getManagerByLoginNameAndPassword(String loginName, String password) {
		log.info("管理员["+loginName+"]登录");
		Manager manager = managerDao.selectByLoginName(loginName);
		if(manager == null || !manager.getPassword().equals(password) 
				|| manager.getSuperAdmin() == Manager.DELETED) {
			return null;
		}
		return manager;
	}
}
