package com.mrunmayi.expensetracker.service;

import com.mrunmayi.expensetracker.dto.LoginRequest;
import com.mrunmayi.expensetracker.dto.SignUpRequest;
import com.mrunmayi.expensetracker.entity.User;
import com.mrunmayi.expensetracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequest request){
        User user = userRepo.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(passwordEncoder.matches(request.password(), user.getPassword())){
            return jwtService.generateToken(user);
        }else{
            throw new RuntimeException("Invalid password");
        }
    }

    public String signUpUser(SignUpRequest request){
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setFirstName(request.firstname());
        if(request.lastname() != null) user.setLastName(request.lastname());

        userRepo.save(user);

        return "Account Created Successfully, Please Login";
    }
}
