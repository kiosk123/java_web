package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.MemberVO;
import repository.MemberDAO;

public class Main {

	
	public static void main(String[] args) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:config/application-config.xml");
		
		MemberDAO dao=context.getBean(MemberDAO.class);
		
		dao.getTime();
		
		MemberVO vo=new MemberVO();
		
		vo.setUserid("USER01");
		vo.setEmail("USER01@naver.com");
		vo.setUsername("USER01");
		vo.setUserpw("USER01");
		
		dao.insertMember(vo);
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
