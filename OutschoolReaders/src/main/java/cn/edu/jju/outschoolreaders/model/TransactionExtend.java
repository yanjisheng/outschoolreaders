package cn.edu.jju.outschoolreaders.model;

import java.math.BigDecimal;

/**
 * 缴（退）费记录扩展，支持缴费页面修改借阅证号和读者类型
 * @author yanjisheng
 *
 */
public class TransactionExtend extends Transaction {

	private static final long serialVersionUID = -6925021163279548812L;
	
	private String cardNo;//借阅证号
	private Byte category;//读者类型
	private BigDecimal amount2;//交纳借阅服务费时同时交纳借阅押金
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Byte getCategory() {
		return category;
	}
	public void setCategory(Byte category) {
		this.category = category;
	}
	public BigDecimal getAmount2() {
		return amount2;
	}
	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}
	
}
