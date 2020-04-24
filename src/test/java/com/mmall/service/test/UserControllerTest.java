package com.mmall.service.test;

import com.mmall.controller.portal.UserController;
import com.mmall.pojo.User;
import com.mmall.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Alex Cheng
 * 4/23/2020 11:50 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)

@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@SpringJUnitWebConfig(locations = "classpath:applicationContext.xml")
public class UserControllerTest {

    // test Controller
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private MockMvc mockMvc;
    //private IUserService iUserService;

    // 发现Mock不起作用
    //@Mock
    //@Autowired
    //private IUserService userService;
    //@InjectMocks //注入mock的controller
    @Autowired
    private UserController userController;


    //@Autowired //注入实现类
    //private UserServiceImpl userService;

    /*
    @BeforeEach // junit 5
    public void setUp(WebApplicationContext wac) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }*/


    @Before
    public void setUp() throws Exception{
        //MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        //mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#spring-mvc-test-framework
    @Test // test a controller
    public void userControllerTest() throws Exception{
        User user = createUser();
        //ServerResponse x = userService.register(user);
        //logger.info(ToStringBuilder.reflectionToString(x.getMsg()));
        //this.mockMvc.perform(get("/user/register.do")
        //this.mockMvc.perform(MockMvcRequestBuilders.get("/user/register.do")
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/register.do", user)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //.andExpect(jsonPath("$.msg").value("Input is not compliance"))
                //.andExpect(model().attributeHasErrors("person"))
                ;


    }

    public User createUser(){
        String testPassword = "123abcABC#";
        //userService.checkPassword(testPassword);
        User user = new User();
        user.setUsername("tanknavy");
        user.setPassword(testPassword);
        user.setEmail("cheng_alex@qqq.com");
        user.setPhone("123456789");
        return user;
    }


}
