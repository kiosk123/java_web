package service;

import java.util.List;

import entity.Member;

public interface MemberService {
	public boolean insertNewMember(Member member);
	public List<Member> getAllMemberList();
}
