package in.das.shared.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public interface CommonUtils {

    static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }

    static String base64Encode(final String data){
        byte[] encodedBytes = Base64.getEncoder().encode(data.getBytes());
        return new String(encodedBytes);
    }

    static String base64Decode(final String encodedData) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        return new String(decodedBytes);
    }
}
