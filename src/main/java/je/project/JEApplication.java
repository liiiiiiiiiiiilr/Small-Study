package je.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import je.project.utils.LogUtils;

@SpringBootApplication
@EnableScheduling
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class JEApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JEApplication.class, args);
		LogUtils.i(JEApplication.class, "系统启动。");
	}

}
