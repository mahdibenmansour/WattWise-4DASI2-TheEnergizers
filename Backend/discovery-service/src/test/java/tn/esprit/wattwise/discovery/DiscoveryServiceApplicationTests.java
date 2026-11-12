package tn.esprit.wattwise.discovery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "eureka.client.enabled=false")
class DiscoveryServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
