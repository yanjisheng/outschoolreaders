package cn.edu.jju.outschoolreaders.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 校外读者缴（退）费记录
 * @author yanjisheng
 *
 */
public class Transaction {

	private Integer id;
	
	private Integer readerId;//缴费人id
	private Date date;//缴费日期
	private BigDecimal amount;//金额
	private Byte type;//类型（1阅览服务费，2借阅服务费，3借阅押金，4退押金）
	private Date validThru;//有效期至
	private Integer managerId;//经办人id
	private Date createdAt;//创建时间
	
	private Reader reader;//缴费人（关联查询）
	private Manager manager;//经办人（关联查询）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReaderId() {
		return readerId;
	}
	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(2, RoundingMode.DOWN);
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Date getValidThru() {
		return validThru;
	}
	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getTypeName() {
		String typeName = null;
		if(type == null) {
			return typeName;
		}
		switch(type) {
		case 1:
			typeName = "阅览服务费";
			break;
		case 2:
			typeName = "借阅服务费";
			break;
		case 3:
			typeName = "借阅押金";
			break;
		case 4:
			typeName = "退押金";
			break;
		}
		return typeName;
	}
	
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
