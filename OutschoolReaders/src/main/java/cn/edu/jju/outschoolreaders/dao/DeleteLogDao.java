package cn.edu.jju.outschoolreaders.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.edu.jju.outschoolreaders.model.DeleteLog;

/**
 * 管理员
 * @author yanjisheng
 *
 */
@Mapper
public interface DeleteLogDao {

	public int add(DeleteLog deleteLog);
	
}
