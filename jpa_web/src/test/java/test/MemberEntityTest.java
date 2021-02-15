package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import entity.Member;
import repository.MemberRepository;
import repository.internal.MemberRepositoryImpl;
import service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestInfraConfig.class)
public class MemberEntityTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberService memberService;
	
	@Transactional
	@Test
	public void test() {
		Member member = new Member();
		member = new Member();
		member.setId("niceguy123");
		member.setDepartment("ºÎ¼­");
		member.setPhoneEx("010-6483-7446");
		member.setPhoneIn("031-456-9014");
		member.setPw("newpassword");
		member.setPwUpdate(new Date(System.currentTimeMillis()));
		member.setRegistDate(new Date(System.currentTimeMillis()));
		
		memberRepository.insertMember(member);
		
		Member memberEntity = memberRepository.getMember(member.getId());
		assertNotNull(memberEntity);
		
		List<Member> memberList = memberRepository.getAllMemberList();
		assertEquals(memberList.size(), 1);
		
		assertNotNull(memberService);
		memberList = memberRepository.getAllMemberList();
		assertEquals(memberList.size(), 1);
		
	}

}
