package br.com.ottimizza.salesforceclientapi.domain.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class StringUtils {

    private final static Logger LOGGER = Logger.getLogger(StringUtils.class.getName());

    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException uee) {
            LOGGER.warning("Não foi possível fazer o encoding da string.");
        }
        return value;
    }

}
