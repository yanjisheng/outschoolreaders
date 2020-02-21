package cn.edu.jju.outschoolreaders.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.model.News;

/**
 * 通知公告Service层<br>
 * 通过查询九江学院图书馆原通知公告列表页获取通知标题和链接。<br>
 * 由NewsTask类定时调用，刷新通知列表。
 * @author yanjisheng
 * @see cn.edu.jju.outschoolreaders.task.NewsTask
 */
@Service
public class NewsService {
	
	private static final Logger log = LoggerFactory.getLogger(NewsService.class);
	
	@Value("${news-source}")
	private String newsSource;
	
	private List<News> news = null;

	public void init() throws IOException {
		log.info("刷新通知列表");
		news = new ArrayList<>();
		String content = null;
		URL u = new URL(newsSource);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		c.setRequestMethod("GET");
		c.setConnectTimeout(10000);
		c.setReadTimeout(20000);
		c.connect();
		if(c.getResponseCode() == 200) {
			InputStream is = c.getInputStream();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			do {
				sb.append(reader.readLine() + "\n\r");
			} while(reader.ready());
			content = sb.toString();
		}
		if(content != null) {
			int endIndexDate = 0;
			for(int i=0; i<3; i++) {
				int beginIndexUrl = content.indexOf("c43021", endIndexDate);
				int endIndexUrl = content.indexOf("target", beginIndexUrl);
				String urlRaw = content.substring(beginIndexUrl+14, endIndexUrl-2);
				String url = newsSource.substring(0, newsSource.lastIndexOf("/")) + "/" + urlRaw;
				int beginIndexTitle = content.indexOf("title", endIndexUrl);
				int endIndexTitle = content.indexOf("\">", beginIndexTitle);
				String title = content.substring(beginIndexTitle+7, endIndexTitle);
				int beginIndexDate = content.indexOf("timestyle43021", endIndexTitle);
				endIndexDate = content.indexOf("&nbsp;", beginIndexDate);
				String date = content.substring(beginIndexDate+16, endIndexDate);
				news.add(new News(title, url, date));
			}
		}
	}
	
	public List<News> getNews() {
		if(news == null) {
			try {
				init();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("通知列表更新失败", e);
			}
		}
		return news;
	}
	
}
