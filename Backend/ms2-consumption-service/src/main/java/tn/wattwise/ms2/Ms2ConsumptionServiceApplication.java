package tn.wattwise.ms2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Ms2ConsumptionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ms2ConsumptionServiceApplication.class, args);
    }
}