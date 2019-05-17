package cn.edu.jju.outschoolreaders.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.ReaderQuery;

/**
 * 校外读者基本信息
 * @author yanjisheng
 *
 */
@Mapper
public interface ReaderDao {
	
	public int addOne(Reader reader);
	
	public int addList(List<Reader> list);
	
	public int modify(Reader reader);
	
	public Reader selectById(Integer readerId);
	
	public Reader selectByCardNo(String cardNo);
	
	public Reader selectByIdentityNoAndName(@Param("identityNo") String identityNo, @Param("name") String name);
	
	public int count(ReaderQuery query);
	
	public List<Reader> query(ReaderQuery query);

}
