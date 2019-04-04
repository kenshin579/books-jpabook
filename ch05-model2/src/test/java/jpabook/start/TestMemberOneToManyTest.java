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
public class TestMemberOneToManyTest extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	@Test
	public void 예제5_13_양방향_연관관계_저장() {
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
	public void 예제_5_12_일대다_방향으로_객체_그래프_탐색() {
		try {
			em.getTransaction().begin();

			TestTeam team = em.find(TestTeam.class, "team1");
			List<TestMember> members = team.getMembers(); //팀 -> 회원 (객체 그래프 탐색)

			for (TestMember testMember : members) {
				log.info("username: {}", testMember.getUsername());
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * 큰 실수는 연관관계의 주인에는 값을 입력하지 않고 주인이 아닌 곳에만 값을 입력하면 null로 세팅됨
	 */
	@Test
	public void 예제_5_14_양방향_연관관계_주의점() {
		try {
			em.getTransaction().begin();

			//회원1 저장
			TestMember member1 = new TestMember("member1", "회원1");
			em.persist(member1);

			//회원2 저장
			TestMember member2 = new TestMember("member2", "회원2");
			em.persist(member2);

			TestTeam team1 = new TestTeam("team1", "팀1");

			//주인이 아닌 곳만 연관관계 설정 - 연관관계의 주인만이 외래 키의 값을 변경할 수 있다
			team1.getMembers().add(member1);
			team1.getMembers().add(member2);

			em.persist(team1);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_15_순수한_객체_연관관계() {
		try {
			em.getTransaction().begin();

			TestTeam team1 = new TestTeam("team1", "팀1");
			TestMember member1 = new TestMember("member1", "회원1");
			TestMember member2 = new TestMember("member2", "회원2");

			member1.setTeam(team1); //연관관계 설정 members1 -> team1
			member2.setTeam(team1); //연관관계 설정 members2 -> team1

			List<TestMember> members = team1.getMembers();
			log.info("size: {}", members.size()); //size: 0이다.
			//객체에서 members에 아무것도 저장이 안되어서 코딩시 문제가 될 수 있음.
			//객체까지 고려하면 양쪽 다 관계를 맺어야 함

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_16_순수한_객체_연관관계() {
		try {
			em.getTransaction().begin();

			TestTeam team1 = new TestTeam("team1", "팀1");
			TestMember member1 = new TestMember("member1", "회원1");
			TestMember member2 = new TestMember("member2", "회원2");

			member1.setTeam(team1);          //연관관계 설정 members1 -> team1
			team1.getMembers().add(member1); //연관관계 설정 team1 -> members1

			member2.setTeam(team1);          //연관관계 설정 members2 -> team1
			team1.getMembers().add(member2); //연관관계 설정 team1 -> members2

			List<TestMember> members = team1.getMembers();
			log.info("size: {}", members.size()); //size: 2이다.

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_17_양방향_설정_JPA로_코드_완성() {
		try {
			em.getTransaction().begin();

			TestTeam team1 = new TestTeam("team1", "팀1");
			em.persist(team1);

			TestMember member1 = new TestMember("member1", "회원1");

			//양방향 연관관계 설정
			member1.setTeam(team1);          //연관관계 설정 members1 -> team1
			team1.getMembers().add(member1); //연관관계 설정 team1 -> members1 (저장시에는 사용되지 않음)
			em.persist(member1);

			TestMember member2 = new TestMember("member2", "회원2");

			member2.setTeam(team1);          //연관관계 설정 members2 -> team1
			team1.getMembers().add(member2); //연관관계 설정 team1 -> members2 (저장시에는 사용되지 않음)
			em.persist(member2);

			List<TestMember> members = team1.getMembers();
			log.info("size: {}", members.size()); //size: 2이다.

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Test
	public void 예제_5_18_양방향_리팩토링_전체코드_편의_메서드() {
		try {
			em.getTransaction().begin();

			TestTeam team1 = new TestTeam("team1", "팀1");
			em.persist(team1);

			TestMember member1 = new TestMember("member1", "회원1");

			//양방향 연관관계 설정
			member1.setTeam(team1);          //연관관계 설정 members1 -> team1
//			team1.getMembers().add(member1); //연관관계 설정 team1 -> members1 (저장시에는 사용되지 않음)
			em.persist(member1);

			TestMember member2 = new TestMember("member2", "회원2");

			member2.setTeam(team1);          //연관관계 설정 members2 -> team1
//			team1.getMembers().add(member2); //연관관계 설정 team1 -> members2 (저장시에는 사용되지 않음)
			em.persist(member2);

			List<TestMember> members = team1.getMembers();
			log.info("size: {}", members.size()); //size: 2이다.

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}