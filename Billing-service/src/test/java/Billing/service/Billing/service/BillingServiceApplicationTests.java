package Billing.service.Billing.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class BillingServiceApplicationTests {

	// Mock the mail sender to avoid real dependency during test
	@MockBean
	private JavaMailSender mailSender;

	@Test
	void contextLoads() {
	}

}
