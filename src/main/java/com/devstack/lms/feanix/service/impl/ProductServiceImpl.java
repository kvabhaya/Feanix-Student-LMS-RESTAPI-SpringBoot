package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.entity.Product;
import com.devstack.lms.feanix.repository.ProductRepository;
import com.devstack.lms.feanix.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional //table dekakata ekapara value dagann
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public void create(RequestProductDto requestProductDto) {
        repository.save(toProduct(requestProductDto));
    }
    private Product toProduct(RequestProductDto dto){
        if(dto==null) return null;
        return Product.builder()
                .productId(UUID.randomUUID().toString())
                .description(dto.getDescription())
                .fileResource(null)
                .orderDetails(null)
                .qtyOnHand(dto.getQtyOnHand())
                .unitPrice(dto.getUnitPrice())
                .build();
    }
}
