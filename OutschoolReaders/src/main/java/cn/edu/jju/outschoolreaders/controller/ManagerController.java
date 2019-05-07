package cn.edu.jju.outschoolreaders.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.jju.outschoolreaders.model.Manager;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.ReaderQuery;
import cn.edu.jju.outschoolreaders.model.Transaction;
import cn.edu.jju.outschoolreaders.model.TransactionExtend;
import cn.edu.jju.outschoolreaders.model.TransactionQuery;
import cn.edu.jju.outschoolreaders.service.ManagerService;
import cn.edu.jju.outschoolreaders.service.ReaderService;
import cn.edu.jju.outschoolreaders.service.TransactionService;
import cn.edu.jju.outschoolreaders.util.Page;
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
	
	@Autowired
	private TransactionService transactionService;
	
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
	 * 管理员修改密码
	 */
	@PostMapping("/manager/changePassword")
	public String changePassword(String loginName, String oldPassword, String newPassword) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, oldPassword);
		if(manager == null) {
			return "unauthorized";
		}
		managerService.changeManagerPassword(loginName, newPassword);
		return "success";
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
	
	/**
	 * 管理员修改读者信息
	 */
	@PostMapping("/manager/modifyReader")
	public String modifyReader(String loginName, String password, Reader reader) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return "unauthorized";
		}
		reader.setManagerId(manager.getId());
		readerService.modifyReader(reader);
		return "success";
	}
	
	/**
	 * 读者缴费
	 */
	@PostMapping("/manager/pay")
	public String pay(String loginName, String password, TransactionExtend transaction) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return "unauthorized";
		}
		transaction.setManagerId(manager.getId());
		transactionService.addTransaction(transaction);
		return "success";
	}
	
	/**
	 * 查询缴费记录
	 */
	@PostMapping("/manager/getPayments")
	public PageResult<Transaction> getPayments(String loginName, String password, TransactionQuery query) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return new PageResult<>(PageResult.UNAUTHORIZED, new ArrayList<>());
		}
		return transactionService.queryTransactions(query);
	}
	
	/**
	 * 查询某读者的缴费记录
	 */
	@PostMapping("/manager/getPaymentsByReaderId")
	public PageResult<Transaction> getPaymentsByReaderId(String loginName, String password, Integer id) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return new PageResult<>(PageResult.UNAUTHORIZED, new ArrayList<>());
		}
		return transactionService.getTransactionsByReaderId(id);
	}
	
	/**
	 * 查询某读者的押金余额
	 */
	@PostMapping("/manager/getDepositByReaderId")
	public BigDecimal getDepositByReaderId(String loginName, String password, Integer id) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null) {
			return null;
		}
		return transactionService.getDepositByReaderId(id);
	}
	
	/**
	 * 查询全部管理员信息<br>
	 * 本接口仅限超级管理员访问
	 */
	@PostMapping("/manager/getManagers")
	public PageResult<Manager> getManagers(String loginName, String password, Page page) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(loginName, password);
		if(manager == null || !(manager.getSuperAdmin() == Manager.SUPER_ADMIN)) {
			return new PageResult<>(PageResult.UNAUTHORIZED, new ArrayList<>());
		}
		return managerService.getManagers(page);
	}
	
	@PostMapping("/manager/addManager")
	public String addManager(String superAdminLoginName, String superAdminPassword, Manager newManager) {
		Manager manager = managerService.getManagerByLoginNameAndPassword(superAdminLoginName, superAdminPassword);
		if(manager == null || !(manager.getSuperAdmin() == Manager.SUPER_ADMIN)) {
			return "unauthorized";
		}
		managerService.addManager(newManager);
		return "success";
	}
}
