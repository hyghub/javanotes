package com.touch.client;

import com.touch.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "PRODUCT-DATA-SERVICE",fallback = ProductClientFeignHystrix.class)
public interface ProductClientFeign {

	@GetMapping("/listProducts")
	public List<Product> listProdcuts();
}
