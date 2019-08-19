import com.eloan.base.util.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-*.xml")
public class Test1 {

    @Test
    public void test1(){
        String admin = MD5.encode("1234");
        System.out.println(admin);
    }
}
