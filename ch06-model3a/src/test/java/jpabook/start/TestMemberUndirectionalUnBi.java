package jpabook.start;

import jpabook.model.test.Unidirectional.TestMember;
import jpabook.model.test.Unidirectional.UnBiTeam;
import jpabook.start.jpa.EMF;
import jpabook.start.util.JpaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

@Slf4j
public class TestMemberUndirectionalUnBi extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	/**
	 * 일대다 단뱡향 매핑보다는 다대일 양방향 매핑을 사용하자
	 * - 일대다 단방향 매핑을 사용하면 엔티티를 매핑한 테이블이 아닌 다른 테이블의 외래 키를 관리해야 함
	 */
	@Test
	public void 예제_6_7_일대다_단뱡향_매핑의_단점() {
		try {
			em.getTransaction().begin();

			TestMember member1 = new TestMember("member1");
			TestMember member2 = new TestMember("member1");

			UnBiTeam team1 = new UnBiTeam();
			team1.getMembers().add(member1);
			team1.getMembers().add(member2);

			em.persist(member1); //INSERT-member1
			em.persist(member2); //INSERT-member2
			em.persist(team1); //INSERT-team1, UPDATE-member1.fk, UPDATE-member2.fk

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}