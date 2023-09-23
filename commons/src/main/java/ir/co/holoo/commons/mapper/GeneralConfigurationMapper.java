package ir.co.holoo.commons.mapper;

import org.mapstruct.*;

/**
 * Default configuration for the mapstruct library.
 *
 * @author Mohammad Yazdian
 */
@MapperConfig(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        typeConversionPolicy = ReportingPolicy.WARN,
        implementationName = "Simple<CLASS_NAME>",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GeneralConfigurationMapper {
}
