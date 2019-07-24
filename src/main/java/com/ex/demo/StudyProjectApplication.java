package com.ex.demo;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;



@SpringBootApplication
@MapperScan(value={"com.ex.demo.dao"})
public class StudyProjectApplication {
	
//	Logger logger = LoggerFactory.getLogger(StudyProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StudyProjectApplication.class, args);
	}
	
	DataSourceTransactionManager transactionManager;

//	@Bean("txManager")
//	public DataSourceTransactionManager transactionManager() {
//		
//		System.out.println("TRACE transactionManager : " + transactionManager.getDataSource());
//		
//		return transactionManager;
//	}
	
//	@Bean("txDef")
//	public DefaultTransactionDefinition transactionDefinition() {
//		System.out.println();
//		return new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
//	}


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
//		transactionManager = new DataSourceTransactionManager(dataSource);
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*.xml");
		sessionFactory.setMapperLocations(res);
		System.out.println("TRACE sqlSessionFactory : " + dataSource);
		
		return sessionFactory.getObject();
	}
	
	
//	@Bean
//	public PlatformTransactionManager transactionManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
}
