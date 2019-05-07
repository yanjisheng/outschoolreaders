package cn.edu.jju.outschoolreaders.model;

/**
 * 缴（退）费记录扩展，支持缴费页面修改借阅证号和读者类型
 * @author yanjisheng
 *
 */
public class TransactionExtend extends Transaction {

	private String cardNo;//借阅证号
	private Byte category;//读者类型
	
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
	
}
