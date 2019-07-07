package com.touch.client;

import java.util.List;

import com.touch.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "PRODUCT-DATA-SERVICE")
public interface ProductClientFeign {

	@GetMapping("/listProducts")
	public List<Product> listProdcuts();
}
