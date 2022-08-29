package ua.hnure.eshop.service;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.hnure.eshop.model.Product;
import ua.hnure.eshop.repository.ProductRepository;

/**
 * Service that expose base functionality for interacting with {@link Product}
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @NonNull
    private final ProductRepository productRepository;

    /**
     * Find all {@link Product}
     *
     * @return list of {@link Product}
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Find {@link Product} for a given {@param id}
     *
     * @param id the product id
     * @return fetched {@link Product}
     */
    public Product findById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Delete {@link Product} by {@param productId}
     *
     * @param productId the product id
     * @return deleted {@link Product}
     */
    public Product deleteById(final Long productId) {
        final Product product = findById(productId);

        productRepository.delete(product);

        return product;
    }

    /**
     * Save {@link Product}
     *
     * @param product that need to save
     * @return saved {@link Product}
     */
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    /**
     * Update {@link Product}
     *
     * @param updated entity to update
     * @return {@link Product}
     */
    public Product update(final Product updated, final Long id) {
        final Product original = findById(id);

        return productRepository.save(updated);
    }
}
