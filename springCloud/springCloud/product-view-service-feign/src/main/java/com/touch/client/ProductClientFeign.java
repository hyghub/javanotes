package com.touch.client;

import com.touch.pojo.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.List;

@FeignClient(value = "PRODUCT-DATA-SERVICE")
@Component
public interface ProductClientFeign {
	@GetMapping("/listProducts")
	public List<Product> listProdcuts();
}
