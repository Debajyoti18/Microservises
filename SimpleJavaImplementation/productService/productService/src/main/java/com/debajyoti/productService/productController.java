package com.debajyoti.productService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/products")
public class productController {
        @GetMapping("/{id}")
        public String getProduct(@PathVariable String id) {
        return "Product is Fetched With ID : "+id;
        }
}
