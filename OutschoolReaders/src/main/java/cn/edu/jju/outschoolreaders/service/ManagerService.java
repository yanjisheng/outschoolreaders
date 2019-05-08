package cn.edu.jju.outschoolreaders.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.dao.ManagerDao;
import cn.edu.jju.outschoolreaders.model.Manager;
import cn.edu.jju.outschoolreaders.util.Page;
import cn.edu.jju.outschoolreaders.util.PageResult;

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
	
	@Value("${default-password}")
	private String password;
	
	public Manager getManagerByLoginNameAndPassword(String loginName, String password) {
		log.info("管理员["+loginName+"]登录");
		Manager manager = managerDao.selectByLoginName(loginName);
		if(manager == null || !manager.getPassword().equals(password) 
				|| manager.getSuperAdmin() == Manager.DELETED) {
			return null;
		}
		return manager;
	}
	
	public void changeManagerPassword(String loginName, String newPassword) {
		log.info("管理员["+loginName+"]修改密码");
		Manager manager = managerDao.selectByLoginName(loginName);
		manager.setPassword(newPassword);
		managerDao.modify(manager);
	}
	
	public PageResult<Manager> getManagers(Page page) {
		log.debug("查询管理员列表");
		int count = managerDao.count();
		List<Manager> list = managerDao.query(page);
		return new PageResult<>(count, list);
	}
	
	public void addManager(Manager manager) {
		log.info("添加管理员["+manager.getLoginName()+"]");
		manager.setPassword(this.password);
		managerDao.add(manager);
	}
	
	public void deleteManager(Integer managerId) {
		log.info("删除管理员["+managerId+"]");
		managerDao.delete(managerId);
	}
	
	public void resetManagerPassword(Integer managerId) {
		log.info("重置管理员["+managerId+"]密码");
		Manager manager = new Manager();
		manager.setId(managerId);
		manager.setPassword(this.password);
		managerDao.modify(manager);
	}
}
