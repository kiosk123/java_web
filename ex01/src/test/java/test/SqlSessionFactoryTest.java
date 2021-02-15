package test;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application-config.xml"})
public class SqlSessionFactoryTest {
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	private final static Logger logger=LoggerFactory.getLogger(SqlSessionFactoryTest.class);
	
	@Test
	public void connectiontest(){			
		
		logger.info("before SqlSessionFactory Test...");
		
		assertNotNull(sqlSessionFactory);
		
		logger.info("After SqlSessionFactory Test...");
	}
}
