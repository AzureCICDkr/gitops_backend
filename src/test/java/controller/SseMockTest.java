package controller;

import com.example.demo.DemoApplication;
import com.example.demo.controller.SseController;
import com.example.demo.service.SseEmitterService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(SseController.class)
@ContextConfiguration(classes = DemoApplication.class)
@Import(SseEmitterService.class)
public class SseMockTest {

        Logger log = LoggerFactory.getLogger(SseMockTest.class);

        @Autowired
        public MockMvc mockMvc;



        @Test
        public void test(){
               try {
                       ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/sse/subscribe/tsessionid_1")
                               .contentType(MediaType.TEXT_EVENT_STREAM_VALUE));

                       resultActions
                               .andDo(result -> log.debug("=============== test result is :{}",result.getResponse().getStatus()));

               }catch(Exception ex){
                        log.debug("=============== test resut is :{}","failed");
               }


        }



        }


