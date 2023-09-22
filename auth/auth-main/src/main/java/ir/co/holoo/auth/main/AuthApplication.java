package ir.co.holoo.auth.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(
        exclude = {
                MessageSourceAutoConfiguration.class,
        },
        proxyBeanMethods = false,
        scanBasePackages = {
                "ir.co.holoo.auth.controller",
                "ir.co.holoo.auth.persistence",
                "ir.co.holoo.auth.service",
                "ir.co.holoo.auth.main"
        })
@EntityScan(basePackages = {"ir.co.holoo.auth.model"})
@ConfigurationPropertiesScan(basePackages = {"ir.co.holoo.auth.service.properties"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
