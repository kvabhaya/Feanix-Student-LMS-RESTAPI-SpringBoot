package com.devstack.lms.feanix.api;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.dto.response.ResponseProductDto;
import com.devstack.lms.feanix.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public String save(@RequestBody RequestProductDto dto){
        productService.create(dto);
        return "Saved";
    }

    @GetMapping("/{productId}")
    public ResponseProductDto findById(@PathVariable String productId){
        return productService.findById(productId);
    }
}
