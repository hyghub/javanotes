package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import entity.User;
import service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class TestSpringJunit4 {
    @Autowired
    ProductService productService;
    @Autowired
    private User user;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("测试开始");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    public void test() {
        //System.out.println(user.getName());
        productService.dosomeService();
    }

}
