package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.dto.response.ResponseProductDto;
import com.devstack.lms.feanix.entity.Product;
import com.devstack.lms.feanix.repository.ProductRepository;
import com.devstack.lms.feanix.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    @Override
    public ResponseProductDto findById(String productId) {
        Optional<Product> selectedProduct = repository.findById(productId);
        if(selectedProduct.isEmpty()){
            return null;
        }
        return toResponseProductDto(selectedProduct.get());
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
    private ResponseProductDto toResponseProductDto(Product p){
        if(p==null) return null;
        return ResponseProductDto.builder()
                .productId(p.getProductId())
                .description(p.getDescription())
                .resourceUrl("")
                .qtyOnHand(p.getQtyOnHand())
                .unitPrice(p.getUnitPrice())
                .build();
    }
}
