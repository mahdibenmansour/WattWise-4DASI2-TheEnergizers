package tn.esprit.wattwise.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.config.enabled=false"
})
class Ms4ChallengeServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
