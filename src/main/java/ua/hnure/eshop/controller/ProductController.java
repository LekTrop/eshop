package ua.hnure.eshop.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hnure.eshop.model.Product;
import ua.hnure.eshop.model.dto.ProductDto;
import ua.hnure.eshop.model.mapper.ProductMapper;
import ua.hnure.eshop.service.ProductService;

/**
 * Class that received http requests and send http response`
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @NonNull
    private final ProductService productService;
    @NonNull
    private final ProductMapper productMapper;

    /**
     * Find response entity list of {@link ProductDto}
     *
     * @return {@link ResponseEntity<List<ProductDto>>}
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        log.info("findAll.E: ");
        final List<ProductDto> products = productService.findAll().stream()
                                                        .map(productMapper::toDto)
                                                        .collect(Collectors.toList());

        final ResponseEntity<List<ProductDto>> responseEntity = new ResponseEntity<>(products, OK);

        log.info("findAll.X: response with entity: {} and status code: {}",
                 responseEntity.getBody(),
                 responseEntity.getStatusCode());
        return responseEntity;
    }

    /**
     * Find {@link ProductDto} by product id {@param id}
     *
     * @param id of specific product
     * @return {@link ResponseEntity<ProductDto>}
     */
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findById(final @PathVariable Long id) {
        log.info("findById.E: product id: {}", id);

        final ProductDto productDto = productMapper.toDto(productService.findById(id));

        //TODO: Throw exception if productDTO is null

        final ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(productDto, OK);

        log.info("findById.X response with entity : {} and status code: {} ",
                 responseEntity.getBody(),
                 responseEntity.getStatusCode());
        return responseEntity;
    }

    /**
     * Map {@param productDto} with {@link ProductMapper} to {@link Product} and save it
     *
     * @param productDto that product to be saved
     * @return {@link ResponseEntity<ProductDto>}
     */
    @PostMapping
    public ResponseEntity<ProductDto> save(final @RequestBody ProductDto productDto) {
        log.info("save.E productDto: {}", productDto);

        final Product product = productMapper.toDomain(productDto);
        final ProductDto savedProduct = productMapper.toDto(productService.save(product));
        final ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

        log.info("save.X response with entity: {} and status code: {}",
                 responseEntity.getBody(),
                 responseEntity.getStatusCode());

        return responseEntity;
    }

    /**
     * Delete {@link Product} with {@param id}
     *
     * @param id the specific product id
     * @return {@link ResponseEntity} with {@link HttpStatus#NO_CONTENT}
     * if {@link Product} successfully deleted
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(final @PathVariable Long id) {
        log.info("delete.E product id: {}", id);

        productService.deleteById(id);
        final ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT)
                                                               .build();

        log.info("delete.X response status: {}", responseEntity);
        return responseEntity;
    }

    /**
     * Update {@link Product} with {@param id} and {@param productDto}
     *
     * @param id         the specific product id
     * @param productDto the {@link ProductDto} to be updated
     * @return {@link ResponseEntity<ProductDto>}
     */
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> update(final @PathVariable Long id, final @RequestBody ProductDto productDto) {
        log.info("update.E Update product with id: {} and updated product: {}", id, productDto);

        final Product original = productMapper.toDomain(productDto);
        final ProductDto updatedProduct = productMapper.toDto(productService.update(original));
        final ResponseEntity<ProductDto> responseEntity = new ResponseEntity<>(updatedProduct, OK);

        log.info("update.X Response with entity: {} and status code: {}",
                 responseEntity.getBody(),
                 responseEntity.getStatusCode());

        return responseEntity;
    }
}
