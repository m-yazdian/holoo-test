package ir.co.holoo.commons.mapper;

import ir.co.holoo.commons.dto.GeneralDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Interface for generic Mapping operations for a specific type.
 *
 * @param <E> the domain type
 * @param <REQ> the request data transfer object
 * @param <RES> the response data transfer object
 */
public interface GeneralMapper<E extends Persistable<?>, REQ extends GeneralDto, RES extends GeneralDto>
        extends MethodMapper {

    E toModel(REQ requestDto);

    List<E> toModel(List<REQ> requestDto);

    RES toResponse(E model);

    List<RES> toResponse(List<E> model);

    default Page<RES> toPage(Page<E> page) {
        Assert.notNull(page, "Page must not be null.");
        if (page.hasContent()) {
            List<RES> responseDto = toResponse(page.getContent().stream().distinct().toList());
            return new PageImpl<>(responseDto, page.getPageable(), page.getTotalElements());
        }
        return new PageImpl<>(Collections.emptyList());
    }
}
