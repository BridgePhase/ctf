package com.bridgephase.foo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
	basePackages={
		"com.bridgephase.foo.view.controller"
	})
public class ViewConfiguration {

}
