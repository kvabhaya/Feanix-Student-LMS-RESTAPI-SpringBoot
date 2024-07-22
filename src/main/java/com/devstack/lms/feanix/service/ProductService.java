package com.devstack.lms.feanix.service;

import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.dto.response.ResponseProductDto;

public interface ProductService {
    public void create(RequestProductDto requestProductDto);
    public ResponseProductDto findById(String productId);
    public void update(RequestProductDto requestProductDto, String productId);
    public void delete(String productId);
}
