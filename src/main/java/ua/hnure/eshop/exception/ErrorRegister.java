package ua.hnure.eshop.exception;

/**
 * Represent register for exceptions
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
public enum ErrorRegister implements EShopError {
    ENTITY_NOT_FOUND_EXCEPTION("Entity with id: %s was not found", 1);

    private String message;
    private int errorCode;

    ErrorRegister(final String message, final int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

    @Override
    public String getFormattedErrorMessage(final Object... args) {
        return String.format(getErrorMessage(), args);
    }


}
