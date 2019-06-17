package com.touch.productservice.service;
import com.touch.productservice.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    public List<Product> listProducts(){
        List<Product> ps = new ArrayList<>();
        ps.add(new Product(1,"product a", 50));
        ps.add(new Product(2,"product b", 100));
        ps.add(new Product(3,"product c", 150));
        return ps;
    };
}
