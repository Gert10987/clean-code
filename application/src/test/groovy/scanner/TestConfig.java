package scanner;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.DriverManager;
import java.sql.SQLException;

@TestConfiguration
public class TestConfig {

    @Bean
    public static DSLContext dslContext() throws SQLException {
        return DSL.using(DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "secret"), SQLDialect.POSTGRES);
    }
}
