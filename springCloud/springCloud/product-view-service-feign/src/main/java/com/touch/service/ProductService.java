package com.touch.service;

import java.util.List;

import com.touch.client.ProductClientFeign;
import com.touch.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductClientFeign productClientFeign;
	public List<Product> listProducts(){
		return productClientFeign.listProdcuts();

	}
}
