package scanner

import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.Location
import org.flywaydb.core.api.configuration.ClassicConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
abstract class WebBaseIT extends Specification {

    @Shared
    static PostgreSQLContainer postgreSQLContainer

    static {
        int containerPort = 5432
        int localPort = 1234
        DockerImageName postgres = DockerImageName.parse("postgres:14.1")
        postgreSQLContainer = new PostgreSQLContainer<>(postgres)
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("secret")
                .withReuse(true)
                .withExposedPorts(containerPort)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
                ));
        postgreSQLContainer.start();

        var configuration = new ClassicConfiguration()
        configuration.setDefaultSchema("scanner_app")
        configuration.setDataSource(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
        configuration.setLocations(new Location("filesystem:db/migration"), new Location("classpath:/test_data"))

        var flyway = new Flyway(configuration)
        flyway.migrate()
    }
}
