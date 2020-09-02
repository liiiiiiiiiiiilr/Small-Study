package je.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import je.project.interceptor.CostInterceptor;
import je.project.interceptor.CustomInterceptor;
import je.project.interceptor.RepairInterceptor;
import je.project.interceptor.ReportInterceptor;
import je.project.interceptor.SearchInterceptor;

@Configuration

public class MyWebAppConfigurer implements WebMvcConfigurer {
	@Bean
	public HandlerInterceptor getCustomInterceptor() {
		return new CustomInterceptor();
	}

	@Bean
	public HandlerInterceptor getReportInterceptor() {
		return new ReportInterceptor();
	}
	@Bean
	public HandlerInterceptor getRepairInterceptor() {
		return new RepairInterceptor();
	}
	@Bean
	public HandlerInterceptor getCostInterceptor() {
		return new CostInterceptor();
	}
	@Bean
	public HandlerInterceptor getSearchInterceptor() {
		return new SearchInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// // 可以在此定义多个拦截器
		// registry.addInterceptor(getCustomInterceptor()).addPathPatterns("/custom/**");//客户管理
		// registry.addInterceptor(getReportInterceptor()).addPathPatterns("/rep/**");//报修管理
		// registry.addInterceptor(getRepairInterceptor()).addPathPatterns("/repair/**");//维修管理
		// registry.addInterceptor(getCostInterceptor()).addPathPatterns("/cost/**");//结算管理
		// registry.addInterceptor(getSearchInterceptor()).addPathPatterns("/search/**");//运营监督管理
		
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);// 大小写不敏感
		configurer.setPathMatcher(matcher);
	}
}
