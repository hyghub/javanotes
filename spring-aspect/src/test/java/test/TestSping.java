package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Product;
import entity.User;
import service.ProductService;

public class TestSping {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});

        User user = (User) context.getBean("user");
        Product pro = (Product) context.getBean("product");
        //System.out.println(user.getName());
        System.err.println(pro.getName() + ",\r\n" + pro.getUser().getName() + "\r\n");
		
		/*ProductService productService= (ProductService)context.getBean("productService");
		productService.dosomeService();*/
    }
}
