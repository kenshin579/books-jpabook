package jpabook.start;

import jpabook.model.test.TestMember;
import jpabook.model.test.TestTeam;
import jpabook.start.jpa.EMF;
import jpabook.start.util.JpaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

@Slf4j
public class TestMemberBidirectionalBi extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	@Test
	public void 예제_6_8_일대다_양방향_팀_엔티티() {
		try {
			em.getTransaction().begin();

			TestMember member1 = new TestMember("member1");
			TestMember member2 = new TestMember("member1");

			TestTeam team1 = new TestTeam();
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