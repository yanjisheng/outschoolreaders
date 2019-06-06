package cn.edu.jju.outschoolreaders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 校外读者阅览（借阅）管理系统
 * @author yanjisheng
 *
 */
@EnableScheduling
@SpringBootApplication
public class OutschoolReadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutschoolReadersApplication.class, args);
	}

}
