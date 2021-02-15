package test;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application-config.xml"})
public class DataSourceTest {

	@Autowired
	DataSource dataSource;
	
	private final static Logger logger=LoggerFactory.getLogger(DataSourceTest.class);
	
	
	@Test
	public void test() {
		
		logger.info("dataSource test...");
		
		assertNotNull(dataSource);
		
		logger.info("dataSource test is Success");
	}

}
