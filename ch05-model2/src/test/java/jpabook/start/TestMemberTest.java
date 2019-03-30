package jpabook.start;

import jpabook.model.test.TestMember;
import jpabook.model.test.TestTeam;
import jpabook.start.jpa.EMF;
import jpabook.start.util.JpaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class TestMemberTest extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	/**
	 *
	 */
	@Test
	public void 예제_5_6_회원과_팀을_저장하는_코드() {
		try {
			em.getTransaction().begin();

			TestTeam team1 = new TestTeam("team1", "팀1");
			em.persist(team1);

			TestMember member1 = new TestMember("member1", "회원1");
			member1.setTeam(team1); //연관관계 설정 member1 -> team1
			em.persist(member1);

			TestMember member2 = new TestMember("member2", "회원2");
			member2.setTeam(team1); //연관관계 설정 member2 -> team1
			em.persist(member2);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_7_JPQL_조인_검색() {
		try {
			em.getTransaction().begin();

			String jpql = "select m from TestMember m join m.team t where t.name =:teamName";

			List<TestMember> resultList = em.createQuery(jpql, TestMember.class)
					.setParameter("teamName", "팀1")
					.getResultList();

			for (TestMember testMember : resultList) {
				log.info("[query] member.username: {}", testMember.getUsername());
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}