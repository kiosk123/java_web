package test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import entity.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestInfraConfig.class)
public class JPQLTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Test
	public void test() {
		assertNotNull(em);
		
		Member member = new Member();
		member = new Member();
		member.setId("niceguy123");
		member.setDepartment("ºÎ¼­");
		member.setPhoneEx("010-6483-7446");
		member.setPhoneIn("031-456-9014");
		member.setPw("newpassword");
		member.setPwUpdate(new Date(System.currentTimeMillis()));
		member.setRegistDate(new Date(System.currentTimeMillis()));
		
		em.persist(member);
		
	  /*TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
		assertEquals(query.getResultList().size(), 1);*/
		
		
		assertEquals(em.createQuery("select m from Member m", Member.class).getResultList().size(), 1);
	}
}
