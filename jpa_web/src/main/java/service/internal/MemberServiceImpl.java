package service.internal;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Member;
import repository.MemberRepository;
import service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	@Override
	public boolean insertNewMember(Member member) {		
		Member dbMember = memberRepository.getMember(member.getId());
		if(dbMember != null)
			return false;
		
		member.setRegistDate(new Date(System.currentTimeMillis()));
		member.setPwUpdate(new Date(System.currentTimeMillis()));
		memberRepository.insertMember(member);		
		return true;
	}

	@Transactional
	@Override
	public List<Member> getAllMemberList() {
		return memberRepository.getAllMemberList();
	}
	
	
}
