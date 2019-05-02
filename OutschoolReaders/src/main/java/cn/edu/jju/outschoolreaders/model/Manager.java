package cn.edu.jju.outschoolreaders.model;

/**
 * 管理员
 * @author yanjisheng
 *
 */
public class Manager {

	private Integer id;
	private String loginName;//登录名
	private String password;//密码
	private String name;//姓名
	private Byte superAdmin;//是否超级管理员（0不是，1是）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getSuperAdmin() {
		return superAdmin;
	}
	public void setSuperAdmin(Byte superAdmin) {
		this.superAdmin = superAdmin;
	}
}
