package TeamOne.BW_ENERGIA.controllers;

import TeamOne.BW_ENERGIA.payloads.LoginRequest;
import TeamOne.BW_ENERGIA.payloads.RegisterRequest;
import TeamOne.BW_ENERGIA.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    //Tutti
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    //Tutti
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Object login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}