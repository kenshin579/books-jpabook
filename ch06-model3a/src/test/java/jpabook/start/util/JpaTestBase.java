package jpabook.start.util;

import org.junit.Rule;

public class JpaTestBase {
	@Rule
	public DBTestResource resource = new DBTestResource();

}
