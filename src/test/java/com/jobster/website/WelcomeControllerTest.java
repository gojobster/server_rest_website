package com.jobster.website;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@RunWith(SpringRunner.class)
////@WebMvcTest(controllers = WelcomeController.class)
//public class WelcomeControllerTest {
//
////    @Autowired
////    private MockMvc mockMvc;
//
////    List<String> expectedList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
//
//    @Test
//    public void main() throws Exception {
////        ResultActions resultActions = mockMvc.perform(get("/"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("welcome"))
////                .andExpect(model().attribute("message", equalTo("Mkyong")))
////                .andExpect(model().attribute("tasks", is(expectedList)))
////                .andExpect(content().string(containsString("Hello, Mkyong")));
////
////        MvcResult mvcResult = resultActions.andReturn();
////        ModelAndView mv = mvcResult.getModelAndView();
//        //
//    }
//
//    // Get request with Param
//    @Test
//    public void hello() throws Exception {
////        mockMvc.perform(get("/hello").param("name", "I Love Kotlin!"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("welcome"))
////                .andExpect(model().attribute("message", equalTo("I Love Kotlin!")))
////                .andExpect(content().string(containsString("Hello, I Love Kotlin!")));
//    }
//}
