package repository;

import java.util.List;

import entity.Member;

public interface MemberRepository {
	public void insertMember(Member member);
	public List<Member> getAllMemberList();
	public Member getMember(String id);
}
