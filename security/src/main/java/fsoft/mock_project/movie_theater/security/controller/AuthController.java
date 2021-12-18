package fsoft.mock_project.movie_theater.security.controller;

import fsoft.mock_project.movie_theater.security.service.UserService;
import fsoft.mock_project.movie_theater.security.dto.AccountLoginReq;
import fsoft.mock_project.movie_theater.security.util.JWTUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private JWTUtility jwtUtility;
    private AuthenticationManager authenticationManager;
    private UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody AccountRegisterReq accountRegisterReq) {
//        AccountInfoRes res = accountService.createAccount(accountRegisterReq);
//        UserDetails userDetails = userDetailService.loadUserByUsername(res.getUsername());
//        String jwtToken = jwtUtility.generateToken(userDetails);
//        res.setJwtToken(jwtToken);
////        return ResponseHandler.generateResponse("Register successfully", HttpStatus.CREATED, res);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AccountLoginReq accountLoginReq) throws Exception {
        log.info("Inside login method of AuthController");
        Authentication authentication = authenticate(accountLoginReq.getUsername(), accountLoginReq.getPassword());
        User principal = (User) authentication.getPrincipal();
        UserDetails userDetails = new User(
                principal.getUsername(),
                "",
                principal.getAuthorities()
        );
        final String token = jwtUtility.generateToken(userDetails);
//        return ResponseHandler.generateResponse("Login successfully", HttpStatus.OK, token);
        log.info("Finish authenticate method of AuthController");
        return ResponseEntity.ok(token);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        log.info("Inside authenticate method of AuthController");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Finish authenticate method of AuthController");
            return authentication;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
