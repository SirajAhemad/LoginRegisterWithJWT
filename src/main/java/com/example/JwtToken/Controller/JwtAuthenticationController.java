package com.example.JwtToken.Controller;

import com.example.JwtToken.Constants.ApplicationURLConstants;
import com.example.JwtToken.Entity.User;
import com.example.JwtToken.Pojo.dto.RegisterUserResponseDto;
import com.example.JwtToken.Pojo.dto.UserLoginDto;
import com.example.JwtToken.Pojo.request.LoginUserRequest;
import com.example.JwtToken.Pojo.request.RegisterUserRequest;
import com.example.JwtToken.Services.CustomUserDetailsService;
import com.example.JwtToken.Services.Impl.UserServiceImpl;
import com.example.JwtToken.Util.CommonService;
import com.example.JwtToken.Util.JwtTokenUtil;
import com.example.JwtToken.Util.SuccessEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApplicationURLConstants.USER)
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @PostMapping(ApplicationURLConstants.REGISTER_USER)
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest){

        System.out.println(registerUserRequest);
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(bcryptEncoder.encode(registerUserRequest.getPassword()));

        userServiceImpl.saveUser(user);

        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        registerUserResponseDto.setUsername(user.getUsername());

        return ResponseEntity.ok(commonService.generateSuccessResponseWithMessageKeyAndData(SuccessEnum.USER_REGISTERED.getCode(), registerUserResponseDto));

    }

    @PostMapping(ApplicationURLConstants.LOGIN_USER)
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest){

        User user = userServiceImpl.findByUsername(loginUserRequest.getUsername());

        if (user!=null && user.getUsername().equals(loginUserRequest.getUsername()) ){
            if (bcryptEncoder.matches(loginUserRequest.getPassword(), user.getPassword())){
                UserDetails userDetails =customUserDetailsService.loadUserByUsername(loginUserRequest.getUsername());
                String token = jwtTokenUtil.generateToken(userDetails);

                UserLoginDto userLoginDto = new UserLoginDto();
                userLoginDto.setUsername(user.getUsername());
                userLoginDto.setToken(token);
                return ResponseEntity.ok(commonService.generateGenericSuccessResponse(userLoginDto));
            }
            else {
                return ResponseEntity.ok(commonService.generateSuccessResponseWithMessageKey(SuccessEnum.INVALID_PASSWORD.getCode()));
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
