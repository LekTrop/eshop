package ua.hnure.eshop.exception;

import lombok.Getter;

/**
 * Represent base EShop exception
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Getter
public class EShopException extends RuntimeException {

    private int errorCode;

    public EShopException(final String message, final int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
