package cn.edu.jju.outschoolreaders.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import cn.edu.jju.outschoolreaders.model.Transaction;
import cn.edu.jju.outschoolreaders.model.TransactionQuery;

/**
 * 校外读者缴（退）费记录
 * @author yanjisheng
 *
 */
@Mapper
public interface TransactionDao {

	public int addOne(Transaction transaction);
	
	public int addList(List<Transaction> list);
	
	public Transaction selectById(Integer transactionId);
	
	public int count(TransactionQuery query);
	
	public List<Transaction> query(TransactionQuery query);
}
