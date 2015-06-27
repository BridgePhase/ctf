package com.bridgephase.ctf.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
@EnableJpaRepositories(basePackages = { "com.bridgephase.ctf.model.repository" })
@ComponentScan(basePackages = { 
	"com.bridgephase.ctf.model",
	"com.bridgephase.ctf.backend.fda",
	"com.bridgephase.ctf.backend.notifications",
	"com.bridgephase.ctf.backend.task",
	"com.bridgephase.ctf.backend.cache"
})
@EnableTransactionManagement
public class ModelConfiguration {
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.bridgephase.ctf.model.jpa" });
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/ctf/data");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.H2);
		jpaVendorAdapter.setGenerateDdl(true);

		final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.bridgephase.ctf.model.jpa");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());

		return localContainerEntityManagerFactoryBean;
	}

    @Primary
    @Autowired
	@Bean(name="transactionManager")
    public JpaTransactionManager getTransactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf){
	    JpaTransactionManager jtx = new JpaTransactionManager();
	    jtx.setEntityManagerFactory(emf);
	    jtx.afterPropertiesSet();
	    return jtx;
    }
	
	@Bean(name="ArrayAwareConverter")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}
	
	@Autowired
	@Bean
	public RestOperations getRestOperations(MappingJackson2HttpMessageConverter converter) {
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().clear();
		// we want to use our converter not the default one :)
		template.getMessageConverters().add(converter);
		return template;
	}
	

}
