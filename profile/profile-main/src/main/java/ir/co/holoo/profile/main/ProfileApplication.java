package ir.co.holoo.profile.main;

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
                "ir.co.holoo.profile.controller",
                "ir.co.holoo.profile.persistence",
                "ir.co.holoo.profile.service",
                "ir.co.holoo.profile.main"
        })
@EntityScan(basePackages = {"ir.co.holoo.profile.model"})
@ConfigurationPropertiesScan(basePackages = {"ir.co.holoo.profile.service.properties"})
public class ProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }
}
