package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오전 1:08
 */
@Repository
public class MemberRepository {

	//컨테이너가 관리하는 엔티티 메니저를 주입해준다
	@PersistenceContext
	EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	//JPQL을 사용해서 이름으로 회원 엔티티를 조회함
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
