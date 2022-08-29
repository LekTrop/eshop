package ua.hnure.eshop.util;

import org.springframework.http.ResponseEntity;

/**
 * Class that consist endpoint utils
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
public class EndpointUtils {

    public static int getHttpStatusCode(final ResponseEntity<?> responseEntity) {
        return responseEntity.getStatusCode().value();
    }

}
