package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es3.server.rules.Consts;
import es3.server.service.AuthService;

@RestController
@RequestMapping(Consts.API_PREFIX+"/auths")
public class AuthController {
    @Autowired
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
}
