//package jpabook.model.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.h2.tools.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.SQLException;
//
//@Slf4j
//@Configuration
//public class H2Config {
//		@Bean
//		public DataSource h2DataSource() {
//			return new EmbeddedDatabaseBuilder()
//					.setType(EmbeddedDatabaseType.H2)
//					.addScript("createPersonTable.sql")
//					.build();
//		}
//
//	@Bean
//	public Server server() throws SQLException {
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
//	}
//}
