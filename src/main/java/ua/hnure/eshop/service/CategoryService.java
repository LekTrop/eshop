package ua.hnure.eshop.service;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.hnure.eshop.model.Category;
import ua.hnure.eshop.model.Product;
import ua.hnure.eshop.repository.CategoryRepository;

/**
 * Service that expose base functionality for interacting with {@link Category}
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    @NonNull
    final CategoryRepository categoryRepository;
    @NonNull
    final ProductService productService;

    /**
     * Find all {@link Category}
     *
     * @return list of {@link Category}
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Find {@link Category} by {@param id}
     *
     * @param id the category id
     * @return {@link Category}
     */
    public Category findById(final Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Update {@link Category}
     *
     * @param updated entity to update
     * @return {@link Category}
     */
    public Category update(final Category updated, final Long id) {
        return update(updated);
    }

    /**
     * Update {@link Category}
     *
     * @return {@link Category}
     */
    public Category update(final Category updated) {
        return categoryRepository.save(updated);
    }

    /**
     * Save {@link Category}
     *
     * @param category that need to save
     * @return {@link Category}
     */
    public Category save(final Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Delete {@link Category} by {@param id}
     *
     * @param id the category id
     * @return {@link Category}
     */
    public Category deleteById(final Long id) {
        final Category category = findById(id);

        categoryRepository.delete(category);

        return category;
    }

    /**
     * Assign {@link Product} to specific {@link Category}
     *
     * @param productId  the product id
     * @param categoryId the category id
     * @return {@link Category}
     */
    public Category assignProductById(final Long productId, final Long categoryId) {
        final Category category = findById(categoryId);
        final Product product = productService.findById(productId);

        category.getProducts().add(product);

        return update(category);
    }

    /**
     * Unassign {@link Product} to specific {@link Category}
     *
     * @param productId  the product id
     * @param categoryId the category id
     * @return {@link Category}
     */
    public Category unassignProductById(final Long productId, final Long categoryId) {
        final Category category = findById(categoryId);
        final Product product = productService.findById(productId);

        category.getProducts().remove(product);

        return update(category);
    }
}