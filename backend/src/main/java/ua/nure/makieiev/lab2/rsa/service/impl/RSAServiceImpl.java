package ua.nure.makieiev.lab2.rsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.makieiev.lab2.rsa.service.RSAService;
import ua.nure.makieiev.lab2.rsa.utils.RSAGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

@Service
public class RSAServiceImpl implements RSAService {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_ECB_PKCS_1_PADDING = "RSA/ECB/PKCS1Padding";

    private final RSAGenerator rsaGenerator;

    @Autowired
    public RSAServiceImpl(RSAGenerator rsaGenerator) {
        this.rsaGenerator = rsaGenerator;
    }

    @Override
    public String decrypt(String information) throws IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] informationBytes = information.getBytes();
        return byteDecrypt(Base64.getDecoder().decode(informationBytes));
    }

    @Override
    public String getPublicKey() {
        return rsaGenerator.getPublicKey();
    }

    private String byteDecrypt(byte[] decodeData) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PrivateKey privateKey = rsaGenerator.getPrivateKey();
        Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] finalBytes = cipher.doFinal(decodeData);
        return new String(finalBytes);
    }

}
