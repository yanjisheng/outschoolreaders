package cn.edu.jju.outschoolreaders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.jju.outschoolreaders.model.News;
import cn.edu.jju.outschoolreaders.service.NewsService;

@RestController
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news")
	public List<News> getNews() {
		return newsService.getNews();
	}
	
}
