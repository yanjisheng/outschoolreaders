package cn.edu.jju.outschoolreaders.task;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.edu.jju.outschoolreaders.service.NewsService;

/**
 * 通知公告定时任务
 * @author yanjisheng
 *
 */
@Component
public class NewsTask {
	
	private static final Logger log = LoggerFactory.getLogger(NewsTask.class);
	
	private static final long HOUR = 3600000l;
	
	@Autowired
	private NewsService newsService;
	
	@Scheduled(fixedRate = HOUR)
	public void reloadNews() {
		log.info("定时任务：更新新闻列表");
		try {
			newsService.init();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("新闻列表更新失败", e);
		}
	}

}
