package ua.hnure.eshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.hnure.eshop.model.Product;
import ua.hnure.eshop.model.dto.ProductDto;

@Mapper(config = MapperConfiguration.class)
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    Product toDomain(final ProductDto productDto);

    ProductDto toDto(final Product product);

}
