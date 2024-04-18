import org.junit.jupiter.api.Test;

import static com.example.taste_of_tomcat.service.EmailService.sendConfirmationEmail;

public class EmailServiceTest {


    @Test
    public void testSendConfirmationEmail() {
        sendConfirmationEmail("anishchanka.daniil@student.ehu.lt");
    }
}