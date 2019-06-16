package com.touch.controller;

import com.touch.pojo.Product;
import com.touch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	@RequestMapping("/listProducts")
	public  Object products(){
		List<Product> ps =productService.listProducts();
		return  ps;
	}

}
