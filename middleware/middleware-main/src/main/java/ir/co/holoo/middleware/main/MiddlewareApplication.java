package ir.co.holoo.middleware.main;

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
                "ir.co.holoo.middleware.controller",
                "ir.co.holoo.middleware.persistence",
                "ir.co.holoo.middleware.service",
                "ir.co.holoo.middleware.main"
        })
@EntityScan(basePackages = {"ir.co.holoo.middleware.model"})
@ConfigurationPropertiesScan(basePackages = {"ir.co.holoo.middleware.service.properties"})
public class MiddlewareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareApplication.class, args);
    }
}
