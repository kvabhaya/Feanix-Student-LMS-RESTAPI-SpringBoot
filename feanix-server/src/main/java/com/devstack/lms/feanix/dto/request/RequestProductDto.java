package com.devstack.lms.feanix.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDto {
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
