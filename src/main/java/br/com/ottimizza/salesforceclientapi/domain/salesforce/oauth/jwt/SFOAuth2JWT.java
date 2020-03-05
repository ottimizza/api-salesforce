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

    public String buildSFJWTAccessToken() throws Exception {
        return encodedJWT() + "." + signedJWT();
    }

    private String encodedJWT() throws UnsupportedEncodingException {
        String encodedJWTHeader = Base64.encodeBase64URLSafeString(header.getBytes("UTF-8"));;
        String encodedJWTClaimsSet = this.claims.base64Encode();
        return encodedJWTHeader + "." + encodedJWTClaimsSet;
    }

    private String signedJWT() throws Exception {
        try {
            PrivateKey privateKey = loadPrivateKey();

            // Sign the JWT Header + "." + JWT Claims Object
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(encodedJWT().toString().getBytes("UTF-8"));
            
            return Base64.encodeBase64URLSafeString(signature.sign());

        } catch (Exception ex) {
        }
        return "";
    }


    private PrivateKey loadPrivateKey() {
        try {
            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream("classpath:keystore/keystore.jks"), this.password.toCharArray());
            return (PrivateKey) keystore.getKey("ottimizza_self_signed", this.password.toCharArray());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {

        } catch (CertificateException certificateException) {

        } catch (SecurityException securityException) {

        } catch (NoSuchFileException noSuchFileException) {

        } catch (KeyStoreException keyStoreException) {

        } catch (UnrecoverableKeyException unrecoverableKeyException) {

        } catch (IOException ioException) {

        }

        return null;
    }

}