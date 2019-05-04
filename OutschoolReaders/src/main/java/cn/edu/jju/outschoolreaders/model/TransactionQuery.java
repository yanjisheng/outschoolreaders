package cn.edu.jju.outschoolreaders.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.jju.outschoolreaders.util.Page;

/**
 * 校外读者缴（退）费记录查询条件
 * @author yanjisheng
 *
 */
public class TransactionQuery extends Transaction{

	private List<Integer> readerIds;//缴费人
	private Date startDate;//缴费日期开始
	private Date endDate;//缴费日期截止
	private BigDecimal amountMin;//缴费金额下限
	private BigDecimal amountMax;//缴费金额上限
	private List<Byte> types;//类型
	private List<Integer> managerIds;//经办人
	
	private Page page;
	
	public List<Integer> getReaderIds() {
		return readerIds;
	}
	public void setReaderIds(List<Integer> readerIds) {
		this.readerIds = readerIds;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getAmountMin() {
		return amountMin;
	}
	public void setAmountMin(BigDecimal amountMin) {
		this.amountMin = amountMin;
	}
	public BigDecimal getAmountMax() {
		return amountMax;
	}
	public void setAmountMax(BigDecimal amountMax) {
		this.amountMax = amountMax;
	}
	public List<Byte> getTypes() {
		return types;
	}
	public void setTypes(List<Byte> types) {
		this.types = types;
	}
	public List<Integer> getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(List<Integer> managerIds) {
		this.managerIds = managerIds;
	}
	
	@Override
	public void setReaderId(Integer readerId) {
		super.setReaderId(readerId);
		if(this.readerIds == null) {
			this.readerIds = new ArrayList<>();
		}
		this.readerIds.add(readerId);
	}
	@Override
	public void setDate(Date date) {
		super.setDate(date);
		if(date == null) {
			startDate = null;
			endDate = null;
		}else if(startDate == null || date.before(startDate)) {
			startDate = date;
		}else if(endDate == null || date.after(endDate)) {
			endDate = date;
		}
	}
	@Override
	public void setAmount(BigDecimal amount) {
		super.setAmount(amount);
		if(amount == null) {
			amountMin = null;
			amountMax = null;
		}else if(amountMin == null || amount.compareTo(amountMin) < 0) {
			amountMin = amount;
		}else if(amountMax == null || amount.compareTo(amountMax) > 0) {
			amountMax = amount;
		}
	}
	@Override
	public void setType(Byte type) {
		super.setType(type);
		if(this.types == null) {
			this.types = new ArrayList<>();
		}
		this.types.add(type);
	}
	@Override
	public void setManagerId(Integer managerId) {
		super.setManagerId(managerId);
		if(this.managerIds == null) {
			this.managerIds = new ArrayList<>();
		}
		this.managerIds.add(managerId);
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}	
	
}
