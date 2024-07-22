package com.devstack.lms.feanix.api;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    public String save(RequestProductDto dto){
        productService.create(dto);
        return "Saved";
    }
}