package com.hakim.helper;

import jakarta.enterprise.context.RequestScoped;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakim
 */
@RequestScoped
public class Sha256Encryptor implements Encryptor {

    @Override
    public String encypt(String password) {
        String hash=null;
        try {
            MessageDigest digest=MessageDigest.getInstance("sha-256");
            byte[] bytes=digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hash=hashByte(bytes);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Sha256Encryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    private String hashByte(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for(byte b:bytes){
            String hash=Integer.toHexString(0xff & b);
            if(hash.length()==1){
                sb.append('0');
            }
            sb.append(hash);
        }
        
        return sb.toString();
    }
    
}
