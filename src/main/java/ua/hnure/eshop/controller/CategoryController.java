package ua.hnure.eshop.controller;

import static org.springframework.http.HttpStatus.OK;
import static ua.hnure.eshop.util.EndpointUtils.getHttpStatusCode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hnure.eshop.exception.EntityNotFoundException;
import ua.hnure.eshop.exception.ErrorRegister;
import ua.hnure.eshop.model.Category;
import ua.hnure.eshop.model.Product;
import ua.hnure.eshop.model.dto.CategoryDto;
import ua.hnure.eshop.model.mapper.CategoryMapper;
import ua.hnure.eshop.service.CategoryService;

/**
 * Class that received http requests and send http response
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    @NonNull
    final CategoryService categoryService;
    @NonNull
    final CategoryMapper categoryMapper;

    /**
     * Find all {@link Category}
     *
     * @return list of {@link Category}
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("findAll.E");

        final List<CategoryDto> categories = categoryService.findAll()
                                                            .stream()
                                                            .map(categoryMapper::toDto)
                                                            .collect(Collectors.toList());

        final ResponseEntity<List<CategoryDto>> responseEntity = new ResponseEntity<>(categories, OK);

        log.info("findAll.X response entity body: {} and statuc code: {}",
                 responseEntity.getBody(),
                 getHttpStatusCode(responseEntity));

        return responseEntity;
    }

    /**
     * Find {@link Category} by {@param id}
     *
     * @param id the category id
     * @return {@link Category}
     */
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> findById(final @PathVariable Long id) {
        log.info("findById.E Category id: {}", id);

        final CategoryDto categoryDto = Optional.ofNullable(categoryService.findById(id))
                                                .map(categoryMapper::toDto)
                                                .orElseThrow(() -> new EntityNotFoundException(
                                                        ErrorRegister.ENTITY_NOT_FOUND_EXCEPTION, id));

        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(categoryDto, OK);

        log.info("findById.X categoryDto: {} and status code: {}",
                 responseEntity.getBody(),
                 getHttpStatusCode(responseEntity));
        return responseEntity;
    }

    /**
     * Update {@link Category}
     *
     * @param id          the category id
     * @param categoryDto the updated Category
     * @return updated {@link Category}
     */
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> update(final @PathVariable Long id,
                                              final @RequestBody CategoryDto categoryDto) {
        log.info("update.E Category id: {} and categoryDto: {}", id, categoryDto);

        final Category category = categoryMapper.toDomain(categoryDto);
        final CategoryDto updatedCategory = categoryMapper.toDto(categoryService.update(category, id));
        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(updatedCategory, OK);

        log.info("update.X CategoryDto: {} and response status: {}", categoryDto, getHttpStatusCode(responseEntity));
        return responseEntity;
    }

    /**
     * Save {@link Category}
     *
     * @param categoryDto that need to save
     * @return saved {@link Category}
     */
    @PostMapping
    public ResponseEntity<CategoryDto> save(final @RequestBody CategoryDto categoryDto) {
        log.info("save.E CategoryDto: {}", categoryDto);

        final Category category = categoryMapper.toDomain(categoryDto);
        final CategoryDto savedCategory = categoryMapper.toDto(categoryService.save(category));
        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(savedCategory, OK);

        log.info("save.X CategoryDto: {} and response status: {}",
                 responseEntity.getBody(),
                 getHttpStatusCode(responseEntity));
        return responseEntity;
    }

    /**
     * Delete {@link Category}
     *
     * @param id the specific category id
     * @return {@link Category}
     */
    @DeleteMapping("{id}")
    public ResponseEntity<CategoryDto> delete(final @PathVariable Long id) {
        log.info("delete.E Category id: {}", id);

        final CategoryDto categoryDto = categoryMapper.toDto(categoryService.deleteById(id));
        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(categoryDto, OK);

        log.info("delete.X CategoryDto: {} and response status: {}",
                 responseEntity.getBody(),
                 getHttpStatusCode(responseEntity));

        return responseEntity;
    }

    /**
     * Assign {@link Product} to {@link Category}
     *
     * @param categoryId the specific category id
     * @param productId  the specific product id
     * @return {@link Category}
     */
    @PutMapping("{categoryId}/products/{productId}")
    public ResponseEntity<CategoryDto> assignProduct(final @PathVariable Long categoryId,
                                                     final @PathVariable Long productId) {
        log.info("assignProduct.E category id: {} and product id: {}", categoryId, productId);

        final CategoryDto categoryDto = categoryMapper.toDto(categoryService.assignProductById(productId, categoryId));
        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(categoryDto, OK);

        log.info("assignProduct.X categoryDto: {} and response status : {}",
                 categoryDto,
                 getHttpStatusCode(responseEntity));
        return responseEntity;
    }

    /**
     * Unassign {@link Product} from {@link Category}
     *
     * @param categoryId the specific category id
     * @param productId  the specific product id
     * @return {@link Category}
     */
    @DeleteMapping("{categoryId}/products/{productId}")
    public ResponseEntity<CategoryDto> unassignProduct(final @PathVariable Long categoryId,
                                                       final @PathVariable Long productId) {
        log.info("unassignProduct.E category id: {} and product id: {}", categoryId, productId);

        final CategoryDto category = categoryMapper.toDto(categoryService.unassignProductById(productId, categoryId));
        final ResponseEntity<CategoryDto> responseEntity = new ResponseEntity<>(category, OK);

        log.info("unassignProduct.X category id: {} and product id: {}", categoryId, productId);
        return responseEntity;
    }
}