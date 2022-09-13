package ua.hnure.eshop.service;

import java.util.Optional;

import ua.hnure.eshop.model.Category;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
public class TestData {

    public static Long CATEGORY_ID = 1L;
    public static String CATEGORY_NAME = "category name";
    public static String CATEGORY_DESCRIPTION = "category description";

    public static Category CATEGORY = Category.builder()
                                              .categoryId(CATEGORY_ID)
                                              .description(CATEGORY_DESCRIPTION)
                                              .name(CATEGORY_NAME)
                                              .build();

    public static Optional<Category> CATEGORY_OPTIONAL = Optional.ofNullable(CATEGORY);
}
