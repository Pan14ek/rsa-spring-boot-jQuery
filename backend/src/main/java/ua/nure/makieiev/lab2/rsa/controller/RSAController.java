package ua.nure.makieiev.lab2.rsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.makieiev.lab2.rsa.service.RSAService;

@RestController
@RequestMapping("/key")
public class RSAController {

    private final RSAService rsaService;

    @Autowired
    public RSAController(RSAService rsaService) {
        this.rsaService = rsaService;
    }

    @GetMapping("/public")
    public ResponseEntity<String> ResponseEntity() {
        return new ResponseEntity<>(rsaService.getPublicKey(), HttpStatus.OK);
    }

}