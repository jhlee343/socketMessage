package shootingstar.socketmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SocketMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketMessageApplication.class, args);
    }

}
