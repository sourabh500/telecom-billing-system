package Billing.service.Billing.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class BillingServiceApplicationTests {

	@MockBean
	private JavaMailSender javaMailSender;

	@Test
	void contextLoads() {
	}

}
