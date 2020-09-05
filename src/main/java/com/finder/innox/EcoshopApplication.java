package com.finder.innox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.finder.innox.filters.JwtSecurityFilter;

@SpringBootApplication
@ComponentScan(basePackages = { "com.finder.innox" })
public class EcoshopApplication extends SpringBootServletInitializer {
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtSecurityFilter());
        //registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/api/**", "/logout");

        return registrationBean;
    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EcoshopApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EcoshopApplication.class, args);
	}

}
