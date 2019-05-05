package cn.edu.jju.outschoolreaders.util;

import java.util.List;

/**
 * 分页查询结果
 * @author yanjisheng
 *
 */
public class PageResult<T> {

	public static final int UNAUTHORIZED = -1;
	
	private int count;
	private List<T> list;
	
	public PageResult(int count, List<T> list) {
		this.count = count;
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public List<T> getList() {
		return list;
	}
	
}
