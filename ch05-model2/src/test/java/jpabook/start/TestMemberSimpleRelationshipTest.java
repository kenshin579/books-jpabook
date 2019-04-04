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
public class TestMemberSimpleRelationshipTest extends JpaTestBase {
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

	@Test
	public void 예제_5_8_연관관계를_수정하는_코드() {
		try {
			em.getTransaction().begin();

			//새로운 팀2
			TestTeam team2 = new TestTeam("team2", "팀2");
			em.persist(team2);

			//회원1에 새로운 팀2 설정
			TestMember testMember = em.find(TestMember.class, "member1");
			testMember.setTeam(team2);

			//em.update() 같은 메서드는 존재 하지 않고 단순히 엔티티의 값만 수정해두면 됨

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_9_연관관계를_삭제하는_코드() {
		try {
			em.getTransaction().begin();

			TestMember testMember = em.find(TestMember.class, "member1");
			testMember.setTeam(null); //연관관계 제거 - DB에 null 값이 세팅됨

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Member와 Team의 연관관계가 있으면 엔티티를 삭제할 수 없음.
	 */
	@Test
	public void chap5_2_5_연관된_엔티티_삭제() {
		try {
			em.getTransaction().begin();

			TestMember testMember1 = em.find(TestMember.class, "member1");
			TestMember testMember2 = em.find(TestMember.class, "member2");
			testMember1.setTeam(null); //연관관계 제거 - DB에 null 값이 세팅됨
			testMember2.setTeam(null); //연관관계 제거 - DB에 null 값이 세팅됨

			TestTeam team2 = em.find(TestTeam.class, "team2");
			em.remove(team2);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}