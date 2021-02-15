package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.MemberVO;
import repository.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/application-config.xml" })
public class MemberDAOTest {

	@Autowired
	MemberDAO dao;

	private final static Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);

	@Test
	public void test() {

		assertNotNull(dao);

		assertNotNull(dao.getTime());

		logger.info("getTime() values is " + dao.getTime());

		MemberVO vo = new MemberVO();
		vo.setEmail("user00@naver.com");
		vo.setUserid("user00");
		vo.setUsername("USER00");
		vo.setUserpw("user00");

		assertEquals(dao.insertMember(vo), 1);

	}

}
