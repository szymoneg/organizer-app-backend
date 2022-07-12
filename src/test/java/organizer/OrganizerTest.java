package organizer;

import com.bilinskiosika.organizer.OrganizerApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {OrganizerApplication.class})
public class OrganizerTest {

    @Karate.Test
    Karate serverTest(){
        return Karate.run("classpath:organizer/server/server.feature");
    }
}
