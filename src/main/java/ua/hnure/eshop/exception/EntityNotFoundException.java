package ua.hnure.eshop.exception;

/**
 * Class that represent entity not found exception
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
public class EntityNotFoundException extends EShopException {

    public EntityNotFoundException(final EShopError eShopError, final Object... args) {
        super(eShopError.getFormattedErrorMessage(args), eShopError.getErrorCode());
    }
}
