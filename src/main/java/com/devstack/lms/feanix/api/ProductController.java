package com.devstack.lms.feanix.api;

import com.devstack.lms.feanix.dto.paginate.ResponseProductPaginateDto;
import com.devstack.lms.feanix.dto.request.RequestProductDto;
import com.devstack.lms.feanix.dto.response.ResponseProductDto;
import com.devstack.lms.feanix.service.ProductService;
import com.devstack.lms.feanix.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<StandardResponseDto> save(@RequestBody RequestProductDto dto){
        productService.create(dto);
        return new ResponseEntity<>(
                new StandardResponseDto("product saved", 201, null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<StandardResponseDto> findById(@PathVariable String productId) {
        return new ResponseEntity<>(
                new StandardResponseDto("product selected", 200, productService.findById(productId)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<StandardResponseDto> update(@RequestBody RequestProductDto dto, @PathVariable String productId){
        productService.update(dto,productId);
        return new ResponseEntity<>(
                new StandardResponseDto("product modified", 200, null),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<StandardResponseDto> delete(@PathVariable String productId){
        productService.delete(productId);
        return new ResponseEntity<>(
                new StandardResponseDto("product deleted", 204, null),
                HttpStatus.NOT_FOUND
        );
    }
    @GetMapping("/search")
    public ResponseEntity<StandardResponseDto> search(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponseDto("product list", 200, productService.search(searchText,page,size)),
                HttpStatus.OK
        );
    }
}
