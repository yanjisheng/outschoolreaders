package cn.edu.jju.outschoolreaders.util;

/**
 * 分页
 * @author yanjisheng
 *
 */
public class Page {
	
	private int pageSize = 10;
	private int pageNum = 1;
	
	public Page() {		
	}
	public Page(int pageSize, int pageNum) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getOffset() {
		int offset = (pageNum-1)*pageSize;
		if(offset < 0) {
			return 0;
		}
		return offset;
	}
	
}
