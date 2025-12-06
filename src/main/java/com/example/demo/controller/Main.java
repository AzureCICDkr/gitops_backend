import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.data.redis.core.RedisTemplate;


@CrossOrigin(origins = "*",
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
             allowCredentials = "false" )

@RestController
public class Main{


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


  
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String  main(HttpServletRequest req  ){
              Map<String, String> map = new HashMap<>();
             
            save("data","1");
            System.out.println (findByKey("data"));      
   return "";

    }

        public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value); // Key-Value 저장
    }

    public String findByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key); // Key로 값 조회
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }



}
