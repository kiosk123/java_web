package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("file:${JPA_WEB}")
public class InfraConfig {
	
	
	@Value("${driverClass}") 
	private String driverClass;
	
	@Value("${url}") 	     
	private String url;
	
	@Value("${user}")    
	private String user;	
	
	@Value("${password}")    
	private String password;
	
	@Bean
	public DataSource dataSource() {	    
		BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(driverClass);
	    dataSource.setUrl(url);
	    dataSource.setUsername(user);
	    dataSource.setPassword(password);	    
	    return dataSource;	 
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource());
		return jpaTransactionManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		Properties props = new Properties();
		props.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect"); //데이터베이스 방언 설정
		props.setProperty("hibernate.show_sql","true"); //SQL 보기
		props.setProperty("hibernate.format_sql","true"); //SQL 정렬해서 보기
		props.setProperty("hibernate.use_sql_comments","true"); //SQL 코멘트 보기
		props.setProperty("hibernate.hbm2ddl.auto","update"); //update
		
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//	    emfb.setMappingResources("classpath:mapper/ormMember.xml");
		emfb.setDataSource(dataSource());
		emfb.setPackagesToScan("entity");
		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		return emfb;
	}
	
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
