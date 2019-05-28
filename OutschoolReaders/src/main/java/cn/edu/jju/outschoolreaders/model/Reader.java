package cn.edu.jju.outschoolreaders.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 校外读者基本信息
 * @author yanjisheng
 *
 */
public class Reader implements Serializable {

	private static final long serialVersionUID = 1010307227000440302L;

	private Integer id;
	
	private String cardNo;//借阅证卡号
	private String name;//姓名
	private String gender;//性别
	private String identityNo;//身份证号
	private String address;//住址（或工作单位）
	private String phone;//联系电话
	private String email;//电子邮件
	
	private Date validThru;//有效期至
	private Byte category;//读者类型（1A类原价，2B类半价，3C类免费）
	private Integer managerId;//经办人id
	
	private String image;//图片路径
	private String remark;//备注
	private Date createdAt;//创建时间
	private Date modifiedAt;//修改时间
	
	private Manager manager;//经办人（关联查询）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getValidThru() {
		return validThru;
	}
	public void setValidThru(Date validThru) {
		this.validThru = validThru;
	}
	public Byte getCategory() {
		return category;
	}
	public void setCategory(Byte category) {
		this.category = category;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public String getCategoryName() {
		String categoryName = null;
		if(category == null) {
			return categoryName;
		}
		switch(category) {
		case 1:
			categoryName = "A类（九江市民，原价）";
			break;
		case 2:
			categoryName = "B类（九江学院毕业生及职工家属，服务费半价）";
			break;
		case 3:
			categoryName = "C类（单位用户，免服务费）";
			break;
		}
		return categoryName;
	}
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
		
}
