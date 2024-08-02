package com.devstack.lms.feanix.api;

import com.devstack.lms.feanix.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    public ResponseEntity<StandardResponseDto> signup(){}
}
