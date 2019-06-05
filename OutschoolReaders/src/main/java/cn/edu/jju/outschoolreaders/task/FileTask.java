package cn.edu.jju.outschoolreaders.task;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.edu.jju.outschoolreaders.service.ReaderService;

/**
 * 文件定时任务
 * @author yanjisheng
 *
 */
@Component
public class FileTask {

	private static final Logger log = LoggerFactory.getLogger(FileTask.class);
	
	private static final long DAY = 86400000l;
	
	@Value("${upload-file-path}")
	private String filePath;
	
	@Autowired
	private ReaderService readerService;
	
	@Scheduled(fixedRate = DAY)
	public void deleteNotUsedFiles() {
		log.info("定时任务：删除未被使用的图片");
		List<String> imageFileNames = readerService.getAllImages();
		File path = new File(filePath);
		File[] files = path.listFiles();
		if (files != null) {
			for (File file : files) {
				if (!imageFileNames.contains(file.getName())) {
					log.info("删除文件" + file.getName());
					file.delete();
				}
			}
		}
	}
}
