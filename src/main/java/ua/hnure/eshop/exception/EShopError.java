package ua.hnure.eshop.exception;

public interface EShopError {

    int getErrorCode();

    String getErrorMessage();

    String getFormattedErrorMessage(final Object... args);
}
