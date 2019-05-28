package cn.edu.jju.outschoolreaders.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.jju.outschoolreaders.dao.ReaderDao;
import cn.edu.jju.outschoolreaders.dao.TransactionDao;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.Transaction;
import cn.edu.jju.outschoolreaders.model.TransactionExtend;
import cn.edu.jju.outschoolreaders.model.TransactionQuery;
import cn.edu.jju.outschoolreaders.util.ExcelExporter;
import cn.edu.jju.outschoolreaders.util.PageResult;

/**
 * 缴费信息
 * @author yanjisheng
 *
 */
@Service
public class TransactionService {

	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private ReaderDao readerDao;
	
	@Autowired
	private ExcelExporter excelExporter;
	
	@Transactional
	public void addTransaction(TransactionExtend transaction) {
		log.info("新增缴费记录，缴费人id["+transaction.getReaderId()+"]");
		if(transaction.getType() == 4) {//退押金的金额应为负数
			transaction.setAmount(transaction.getAmount().abs().negate());
		} else {
			transaction.setAmount(transaction.getAmount().abs());
		}
		transaction.setDate(new Date());
		transactionDao.addOne(transaction);
		Reader reader = readerDao.selectById(transaction.getReaderId());
		if(transaction.getType() == 1 || transaction.getType() == 2) {
			reader.setValidThru(transaction.getValidThru());
		}
		reader.setManagerId(transaction.getManagerId());
		reader.setCardNo(transaction.getCardNo());
		reader.setCategory(transaction.getCategory());
		readerDao.modify(reader);
		if(transaction.getType() == 2) {//交纳借阅服务费时同时交纳借阅押金
			transaction.setType((byte) 3);
			transaction.setAmount(transaction.getAmount2());
			if(transaction.getAmount() != null) {
				transactionDao.addOne(transaction);
			}
		}
	}
	
	public PageResult<Transaction> queryTransactions(TransactionQuery query) {
		log.debug("按条件查询缴费记录");
		int count = transactionDao.count(query);
		List<Transaction> list = transactionDao.query(query);
		return new PageResult<>(count, list);
	}
	
	public PageResult<Transaction> getTransactionsByReaderId(Integer readerId) {
		log.debug("查询读者["+readerId+"]缴费记录");
		TransactionQuery query = new TransactionQuery();
		query.setReaderId(readerId);
		int count = transactionDao.count(query);
		List<Transaction> list = transactionDao.query(query);
		return new PageResult<>(count, list);
	}
	
	public BigDecimal getDepositByReaderId(Integer readerId) {
		log.debug("查询读者["+readerId+"]押金余额");
		BigDecimal deposit = new BigDecimal("0.00");
		TransactionQuery query = new TransactionQuery();
		query.setReaderId(readerId);
		query.setType((byte)3);
		query.setType((byte)4);
		List<Transaction> list = transactionDao.query(query);
		for(Transaction transaction : list) {
			deposit = deposit.add(transaction.getAmount());
		}
		return deposit;
	}
	
	public void exportTransactions(TransactionQuery query, HttpServletResponse response) {
		log.debug("导出缴费记录");
		query.setPage(null);
		List<Transaction> list = transactionDao.query(query);
		try {
			excelExporter.export(Transaction.class, list, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
