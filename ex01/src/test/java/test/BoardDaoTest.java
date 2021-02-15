package test;

import static org.junit.Assert.*;

import java.lang.reflect.Member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.BoardVO;
import repository.BoardDAOImpl;
import repository.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application-config.xml"})
public class BoardDaoTest {
	
	@Autowired
	private BoardDao dao;
	
	private final static Logger logger=LoggerFactory.getLogger(BoardDaoTest.class);
	
	
//	@Test
//	public void testCreate()throws Exception {
//		
//		BoardVO board=new BoardVO();
//		board.setTitle("새로운 글을 넣습니다.");
//		board.setContent("새로운 글을 넣습니다.");
//		board.setWriter("user00");
//		int affected=dao.create(board);
//		assertEquals(affected, 1);
//
//	}
	
	
	@Test
	public void testRead()throws Exception {
		BoardVO vo=dao.read(1);
		assertNotNull(vo);
		logger.info(vo.getContent());
		logger.info(vo.getTitle());
		logger.info(vo.getWriter());
	}
	
//	@Test
//	public void update()throws Exception{
//		BoardVO board=new BoardVO();
//		board.setBno(1);
//		board.setTitle("수정된 글입니다.");
//		board.setContent("수정 테스트");		
//		int affected=dao.update(board);
//		assertEquals(affected, 1);
//	}
//	
//	@Test
//	public void testDelete()throws Exception{
//		dao.delete(1);
//	}

}
