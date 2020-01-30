package cn.edu.jju.outschoolreaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 校外读者阅览（借阅）管理系统
 * @author yanjisheng
 *
 */
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class OutschoolReadersApplication {

	public static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(OutschoolReadersApplication.class, args);
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
