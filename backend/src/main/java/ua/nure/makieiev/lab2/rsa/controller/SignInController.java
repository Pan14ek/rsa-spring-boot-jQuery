package ua.nure.makieiev.lab2.rsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.makieiev.lab2.rsa.dto.UserSignIn;
import ua.nure.makieiev.lab2.rsa.exception.BadRequestException;
import ua.nure.makieiev.lab2.rsa.service.RSAService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class SignInController {

    private final RSAService rsaService;

    @Autowired
    public SignInController(RSAService rsaService) {
        this.rsaService = rsaService;
    }

    @PostMapping(value = "/signIn", produces = "application/json")
    public ResponseEntity<?> signIn(@RequestBody @Valid UserSignIn userSignIn, BindingResult bindingResult) {
        try {
            return signInHandler(userSignIn, bindingResult);
        } catch (IllegalBlockSizeException | NoSuchPaddingException |
                NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private ResponseEntity<?> signInHandler(UserSignIn userSignIn, BindingResult bindingResult) throws InvalidKeyException, BadPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult, HttpStatus.BAD_REQUEST);
        } else {
            String decryptPassword = rsaService.decrypt(userSignIn.getPassword());
            return new ResponseEntity<>(decryptPassword, HttpStatus.OK);
        }
    }

}