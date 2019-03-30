package jpabook.start;

import jpabook.start.jpa.EMF;
import jpabook.start.util.JpaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

@Slf4j
public class MemberTest extends JpaTestBase {
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
	}

	@Test
	public void 실전예제() {
	}
}