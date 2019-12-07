package cn.edu.jju.outschoolreaders.model;

/**
 * 新闻
 * @author yanjisheng
 *
 */
public class News {
	
	private String title;//标题
	private String url;//路径
	private String date;//日期
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public News(String title, String url, String date) {
		super();
		this.title = title;
		this.url = url;
		this.date = date;
	}

}
