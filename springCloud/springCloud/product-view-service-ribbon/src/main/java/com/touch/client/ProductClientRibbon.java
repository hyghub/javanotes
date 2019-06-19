package com.touch.client;

import com.touch.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClientRibbon {
	@Autowired
	RestTemplate restTemplate;
	public List<Product> listProdcuts(){
		return restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/listProducts",List.class);
	}
}
