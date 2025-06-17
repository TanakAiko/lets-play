package sn.zone.bakcup_api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sn.zone.bakcup_api.web.dto.UserLoginDTO;
import sn.zone.bakcup_api.web.dto.UserLoginResponseDTO;
import sn.zone.bakcup_api.web.dto.UserBasicDTO;
import sn.zone.bakcup_api.web.dto.UserResponseDTO;

@RequestMapping("api/auth")
public interface AuthController {
    
    @PostMapping("/login")
    ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO);

    @PostMapping("/register")
    ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserBasicDTO userBasicDTO);
}
