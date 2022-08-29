package ua.hnure.eshop.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.hnure.eshop.model.Category;

/**
 * Represent DTO entity for {@link Category}
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;
    private String name;
    private String description;
    private CategoryDto subCategory;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("products")
    @Builder.Default
    private Set<ProductDto> products = new HashSet<>();
}
