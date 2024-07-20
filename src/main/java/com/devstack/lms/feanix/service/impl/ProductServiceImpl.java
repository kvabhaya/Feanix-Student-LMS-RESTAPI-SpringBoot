package com.devstack.lms.feanix.service.impl;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional //table dekakata ekapara value dagann
public class ProductServiceImpl implements ProductService {
    @Override
    public void create(RequestProductDto requestProductDto) {

    }
}
