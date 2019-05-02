package cn.edu.jju.outschoolreaders.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.edu.jju.outschoolreaders.model.Manager;

/**
 * 管理员
 * @author yanjisheng
 *
 */
@Mapper
public interface ManagerDao {

	public int add(Manager manager);
	
	public int modify(Manager manager);
	
	public Manager selectById(Integer managerId);
	
	public Manager selectByLoginName(String loginName);
	
	public int delete(Integer managerId);
}
