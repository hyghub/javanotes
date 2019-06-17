package com.touch.productservice.controller;
import com.touch.productservice.pojo.Product;
import com.touch.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("/listProducts")
    public  Object listProducts(Model m){
        List<Product> ps = productService.listProducts();
        m.addAttribute("ps",ps);
        return  "products";
    }
}






