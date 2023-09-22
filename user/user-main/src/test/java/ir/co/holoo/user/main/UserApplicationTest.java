package ir.co.holoo.user.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class UserApplicationTest {

    ConfigurableApplicationContext applicationContext;
    MockedStatic<SpringApplication> mockedStatic;

    @BeforeEach
    void beforeSetup() {
        this.applicationContext = new ServletWebServerApplicationContext();
        this.mockedStatic = mockStatic(SpringApplication.class);
    }

    @AfterEach
    void afterSetup() {
        this.mockedStatic.close();
    }

    @Test
    @DisplayName("Testing UserApplication main method.")
    void hasApplicationContextIsOfTypeServletWebServerApplicationContextWhenApplicationStarts() {
        mockedStatic
                .when((MockedStatic.Verification) SpringApplication.run(UserApplication.class))
                .thenReturn(applicationContext);
        UserApplication.main(new String[]{});
        assertThat(SpringApplication.run(UserApplication.class)).isEqualTo(applicationContext);
    }
}
