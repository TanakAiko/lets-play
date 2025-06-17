package sn.zone.bakcup_api.web.controllers.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sn.zone.bakcup_api.data.entities.User;
import sn.zone.bakcup_api.security.JwtUtils;
import sn.zone.bakcup_api.services.UserService;
import sn.zone.bakcup_api.web.controllers.AuthController;
import sn.zone.bakcup_api.web.dto.UserLoginDTO;
import sn.zone.bakcup_api.web.dto.UserLoginResponseDTO;
import sn.zone.bakcup_api.web.dto.UserBasicDTO;
import sn.zone.bakcup_api.web.dto.UserResponseDTO;

@RestController
public class AuthControllerImpl implements AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthControllerImpl(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<UserLoginResponseDTO> login(UserLoginDTO userLoginDTO) {
        System.out.println("\n*****************START login");
        User checkUser = userService.getByEmail(userLoginDTO.getEmail());

        if (checkUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email");
        }

        System.out.println("userLoginDTO: " + userLoginDTO);
        System.out.println("user password: '" + checkUser.getPassword() + "'");
        System.out.println("dto password: '" + userLoginDTO.getPassword() + "'");

        if (passwordEncoder.matches(userLoginDTO.getPassword(), checkUser.getPassword())) {
            String token = jwtUtils.generateToken(checkUser.getId(), checkUser.getRole());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CACHE_CONTROL, "no-store")
                    .body(new UserLoginResponseDTO(token));
        } else {
            System.out.println("FAILED !!!");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> register(UserBasicDTO userBasicDTO) {
        System.out.println("\n********************START register");
        UserResponseDTO response = new UserResponseDTO(userService.create(userBasicDTO.toUser()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .body(response);
    }
}
