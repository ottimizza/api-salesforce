package br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.NoSuchFileException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SFOAuth2JWT {

    private final String header = "{\"alg\":\"RS256\"}";

    private SFOAuth2JWTClaim claims;

    private String password;

    public String buildSFJWTAccessToken(String password) throws Exception {
        return encodedJWT() + "." + signedJWT(password);
    }

    private String encodedJWT() throws UnsupportedEncodingException {
        String encodedJWTHeader = Base64.encodeBase64URLSafeString(header.getBytes("UTF-8"));;
        String encodedJWTClaimsSet = this.claims.base64Encode();
        return encodedJWTHeader + "." + encodedJWTClaimsSet;
    }

    private String signedJWT(String password) throws Exception {
        try {
            PrivateKey privateKey = loadPrivateKey(password);

            // Sign the JWT Header + "." + JWT Claims Object
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(encodedJWT().toString().getBytes("UTF-8"));
            
            return Base64.encodeBase64URLSafeString(signature.sign());

        } catch (Exception ex) {
            System.out.println("\nGot an exception signing the JWT! Check it out:");
            System.out.println(ex.getMessage());
        }
        return "";
    }


    private PrivateKey loadPrivateKey(String password) {
        try {
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new ClassPathResource("classpath:keystore/keystore.jks").getInputStream(), password.toCharArray());
            return (PrivateKey) keystore.getKey("ottimizza_self_signed", password.toCharArray());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(noSuchAlgorithmException.getMessage());
        } catch (CertificateException certificateException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(certificateException.getMessage());
        } catch (SecurityException securityException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(securityException.getMessage());
        } catch (NoSuchFileException noSuchFileException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(noSuchFileException.getMessage());
        } catch (KeyStoreException keyStoreException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(keyStoreException.getMessage());
        } catch (UnrecoverableKeyException unrecoverableKeyException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(unrecoverableKeyException.getMessage());
        } catch (IOException ioException) {
            System.out.println("\nGot an exception loading Private Key! Check it out:");
            System.out.println(ioException.getMessage());
        }

        return null;
    }

}