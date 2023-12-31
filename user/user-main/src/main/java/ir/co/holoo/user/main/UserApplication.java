package ir.co.holoo.user.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
        exclude = {
                MessageSourceAutoConfiguration.class,
        },
        proxyBeanMethods = false,
        scanBasePackages = {
                "ir.co.holoo.user.controller",
                "ir.co.holoo.user.persistence",
                "ir.co.holoo.user.service",
                "ir.co.holoo.user.main"
        })
@EntityScan(basePackages = {"ir.co.holoo.user.model"})
@ConfigurationPropertiesScan(basePackages = {"ir.co.holoo.user.service.properties"})
@EnableJpaRepositories(basePackages = {"ir.co.holoo.user.persistence"})
@EnableTransactionManagement
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
