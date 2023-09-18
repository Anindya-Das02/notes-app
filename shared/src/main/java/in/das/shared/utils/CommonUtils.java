package in.das.shared.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CommonUtils {

    static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
