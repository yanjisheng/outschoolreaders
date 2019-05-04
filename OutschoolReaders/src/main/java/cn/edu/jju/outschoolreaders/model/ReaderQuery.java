package cn.edu.jju.outschoolreaders.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.jju.outschoolreaders.util.Page;

/**
 * 校外读者查询条件
 * @author yanjisheng
 *
 */
public class ReaderQuery extends Reader {

	private Date startValid;//有效期查询开始
	private Date endValid;//有效期查询截止
	private List<Integer> managerIds;//经办人查询列表
	private Date startCreate;//创建时间开始
	private Date endCreate;//创建时间截止
	private Date startModify;//修改时间开始
	private Date endModify;//修改时间截止
	
	private Page page;

	public Date getStartValid() {
		return startValid;
	}

	public void setStartValid(Date startValid) {
		this.startValid = startValid;
	}

	public Date getEndValid() {
		return endValid;
	}

	public void setEndValid(Date endValid) {
		this.endValid = endValid;
	}

	public List<Integer> getManagerIds() {
		return managerIds;
	}

	public void setManagerIds(List<Integer> managerIds) {
		this.managerIds = managerIds;
	}

	public Date getStartCreate() {
		return startCreate;
	}

	public void setStartCreate(Date startCreate) {
		this.startCreate = startCreate;
	}

	public Date getEndCreate() {
		return endCreate;
	}

	public void setEndCreate(Date endCreate) {
		this.endCreate = endCreate;
	}

	public Date getStartModify() {
		return startModify;
	}

	public void setStartModify(Date startModify) {
		this.startModify = startModify;
	}

	public Date getEndModify() {
		return endModify;
	}

	public void setEndModify(Date endModify) {
		this.endModify = endModify;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	@Override
	public void setManagerId(Integer managerId) {
		super.setManagerId(managerId);
		if(this.managerIds == null) {
			this.managerIds = new ArrayList<>();
		}
		this.managerIds.add(managerId);
	}
	
	@Override
	public void setValidThru(Date validThru) {
		super.setValidThru(validThru);
		if(validThru == null) {
			startValid = null;
			endValid = null;
		}else if(startValid == null || validThru.before(startValid)) {
			startValid = validThru;
		}else if(endValid == null || validThru.after(endValid)) {
			endValid = validThru;
		}
	}
}
