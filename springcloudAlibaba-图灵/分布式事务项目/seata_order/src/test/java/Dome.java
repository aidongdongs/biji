import com.show.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Dome {

    @Autowired
    private OrderMapper mapper;

    @Test
    public void test (){
        System.out.println(mapper);

    }
}
