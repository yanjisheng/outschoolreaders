package cn.edu.jju.outschoolreaders.model;

import java.util.Date;

/**
 * 删除记录
 * @author yanjisheng
 *
 */
public class DeleteLog {
	
	private Integer id;
	private Integer managerId;//删除操作人
	private Date deletedAt;//删除时间
	private String data;//被删除的数据
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
