package com.touch.service;

import com.touch.client.ProductClientRibbon;
import com.touch.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
	@Autowired
	ProductClientRibbon productClientRibbon;
	public List<Product> listProducts(){
		return productClientRibbon.listProdcuts();
	}
}
