package com.bridgephase.ctf.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
	basePackages={
		"com.bridgephase.ctf.view.controller"
	})
public class ViewConfiguration {

}
