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
	public void 예제_3_2_엔티티_수정시_update_메서드가_불필요하다() {
		Member member = new Member();
		member.setId("memberA");
		member.setUsername("frank");
		member.setAge(20);

		try {
			em.getTransaction().begin();

			//등록
			em.persist(member);

			Member memberA = em.find(Member.class, "memberA");
			assertThat(memberA.getUsername()).isEqualTo("frank");

			//수정
			memberA.setUsername("hi");
//			memberA.setAge(20);
			assertThat(memberA.getUsername()).isEqualTo("hi");

			//em.update(member); //이런 코드는 불필요하다

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
}