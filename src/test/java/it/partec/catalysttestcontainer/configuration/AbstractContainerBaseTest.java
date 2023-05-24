package it.partec.catalysttestcontainer.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.MountableFile;

public abstract class AbstractContainerBaseTest {

  static final MySQLContainer MY_SQL_CONTAINER;

  static {
    MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0")
            .withInitScript("init.sql")
            .withDatabaseName("catalyst")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(3306);
    MY_SQL_CONTAINER.start();
  }

  @DynamicPropertySource
  private static void setupProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
  }
}
