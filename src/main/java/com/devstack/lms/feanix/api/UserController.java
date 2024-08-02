package com.devstack.lms.feanix.api;

import com.devstack.lms.feanix.dto.request.RequestUserDto;
import com.devstack.lms.feanix.service.ApplicationUserService;
import com.devstack.lms.feanix.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final ApplicationUserService applicationUserService;
    @PostMapping("/signup")
    public ResponseEntity<StandardResponseDto> signup(@RequestBody RequestUserDto dto){
        applicationUserService.create(dto);
        return new ResponseEntity<>(new StandardResponseDto("user created..",201,dto.getUsername()),
                HttpStatus.CREATED);
    }
}
