package ua.nure.makieiev.lab2.rsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.nure.makieiev.lab2.rsa.utils.RSAGenerator;

import java.security.NoSuchAlgorithmException;

@Configuration
public class RSAConfig {

    @Bean
    @Scope("singleton")
    public RSAGenerator getRSAGenerator() throws NoSuchAlgorithmException {
        return new RSAGenerator();
    }

}
