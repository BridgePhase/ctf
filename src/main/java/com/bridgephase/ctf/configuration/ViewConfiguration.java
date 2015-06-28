package com.bridgephase.ctf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@ComponentScan(basePackages = { "com.bridgephase.ctf.view.controller" })
public class ViewConfiguration {

	@Value(value = "${spring.view.prefix}")
	private String viewPrefix;

	@Value(value = "${spring.view.suffix}")
	private String viewSuffix;

	@Bean
	public TemplateResolver defaultTemplateResolver() {
		TemplateResolver templateResolver = new TemplateResolver();
		templateResolver.setCacheable(false);
		templateResolver.setResourceResolver(thymeleafResourceResolver());
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setPrefix(viewPrefix);
		templateResolver.setSuffix(viewSuffix);
		return templateResolver;
	}

	@Bean
	public IResourceResolver thymeleafResourceResolver() {
		return new ClassLoaderResourceResolver();
	}

	@Bean
	// Magic entry
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}
}
