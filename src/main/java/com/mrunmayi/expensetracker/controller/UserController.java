package com.mrunmayi.expensetracker.controller;

import com.mrunmayi.expensetracker.dto.LoginRequest;
import com.mrunmayi.expensetracker.dto.SignUpRequest;
import com.mrunmayi.expensetracker.service.JwtService;
import com.mrunmayi.expensetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody @Valid SignUpRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(userService.signUpUser(request));
    }

//    @GetMapping("/profile")
//    public ResponseEntity<UserResponse> getDetails(HttpServletRequest request){
//        String email = (String)request.getAttribute("email");
//        Long id = (Long)request.getAttribute("studentId");
//        System.out.println("in controller, email: "+email);
//        System.out.println("in controller, id: "+id);
//
//        if(email == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        return ResponseEntity.ok(service.getDetails(email, id));
//    }

}
