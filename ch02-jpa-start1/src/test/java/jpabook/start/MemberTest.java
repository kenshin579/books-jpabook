package jpabook.start;

import jpabook.start.jpa.EMF;
import jpabook.start.util.JpaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MemberTest extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	@Test
	public void test_create_member() {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("지한");
		member.setAge(2);

		try {
			em.getTransaction().begin();

			//등록
			em.persist(member);

			//수정
			member.setAge(20);

			//한건 조회
			Member findMember = em.find(Member.class, id);
			log.info("username: {} age: {}", findMember.getUsername(), findMember.getAge());
			assertThat(findMember.getAge()).isEqualTo(20);

			//목록 조회

			List<Member> members = em.createQuery("FROM Member m", Member.class).getResultList();
			log.info("members size : {}", members.size());
			assertThat(members.size()).isEqualTo(1);

			//삭제
			em.remove(member);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}