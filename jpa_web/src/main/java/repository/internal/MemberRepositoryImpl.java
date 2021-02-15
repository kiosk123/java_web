package repository.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import entity.Member;
import entity.QMember;
import repository.MemberRepository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void insertMember(Member member) {
		entityManager.persist(member);
	}

	@Transactional
	@Override
	public List<Member> getAllMemberList() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QMember member = QMember.member;
		return queryFactory.selectFrom(member).fetch();
		
	}

	@Transactional
	@Override
	public Member getMember(String id) {				
		return entityManager.find(Member.class, id);
	}
}
