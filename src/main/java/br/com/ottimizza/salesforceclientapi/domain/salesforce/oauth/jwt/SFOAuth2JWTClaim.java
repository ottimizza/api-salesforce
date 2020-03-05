package br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SFOAuth2JWTClaim {

    /**
     * The issuer must contain the OAuth client_id or the 
     * connected app for which you registered the certificate.
     */
    private String iss;

    /**
     * The audience identifies the authorization server as an 
     * intended audience. The authorization server must verify 
     * that it is an intended audience for the token.
     * 
     * Use the authorization server’s URL for the audience value: 
     * https://login.salesforce.com, https://test.salesforce.com, 
     * or https://community.force.com/customers if implementing 
     * for a community.
     */
    private String aud;

    /**
     * The subject must contain the username of the Salesforce 
     * user or the Salesforce community user if implementing 
     * for a community.
     * 
     * For backward compatibility, you can use principal (prn) 
     * instead of subject (sub). If both are specified, prn is used.
     */
    private String sub;

    /**
     * The validity must be the expiration time of the assertion 
     * within 3 minutes, expressed as the number of seconds 
     * from 1970-01-01T0:0:0Z measured in UTC.
     */
    private Long exp;

    /**
     * Base64 encodes the JWT Claims Set without any line breaks.
     * 
     * @return String representation of the encoded JWT Claim Set.
     */
    public String base64Encode() throws UnsupportedEncodingException {
        return Base64.encodeBase64URLSafeString(payload().getBytes("UTF-8"));
    }

    public String payload() {
        return MessageFormat.format(
            "'{'\"iss\": \"{0}\", \"sub\": \"{1}\", \"aud\": \"{2}\", \"exp\": \"{3}\"'}'", 
            this.iss, this.sub, this.aud, this.exp.toString()
        );
    }

}