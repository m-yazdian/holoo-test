package ir.co.holoo.commons.data.hibernate.repoistory;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.domain.Persistable;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @param <E> the domain type the repository manage
 * @param <P> the querydsl domain type the repository manages
 * @param <I> the type of the id of the entity the repository manages
 * @author Mohammad Yazdian
 */
@NoRepositoryBean
public interface JpaRepository<E extends Persistable<I>, P extends EntityPath<?>, I extends Serializable> extends
        ListCrudRepository<E, I>, ListPagingAndSortingRepository<E, I>, ListQuerydslPredicateExecutor<E>,
        QuerydslBinderCustomizer<P> {

    @Override
    default void customize(@NonNull QuerydslBindings bindings, @NonNull P root) {
        bindings.bind(String.class).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(Instant.class).all(JpaRepository::instantRangeQuery);
        bindings.bind(LocalDate.class).all(JpaRepository::localDateRangeQuery);
        bindings.bind(LocalDateTime.class).all(JpaRepository::localDateTimeRangeQuery);
        bindings.bind(BigDecimal.class).all(JpaRepository::decimalRangeQuery);
    }

    private static Optional<Predicate> localDateRangeQuery(Path<LocalDate> path,
                                                           Collection<? extends LocalDate> values) {
        DatePath<LocalDate> datePath = (DatePath<LocalDate>) path;
        List<LocalDate> dates = new ArrayList<>(values);
        if (dates.size() == 1) {
            return Optional.of(datePath.eq(dates.get(0)));
        } else if (dates.size() == 2) {
            LocalDate from = dates.get(0);
            LocalDate to = dates.get(1);
            return Optional.of(datePath.between(from, to));
        } else {
            return Optional.of(datePath.in(dates));
        }
    }

    private static Optional<Predicate> localDateTimeRangeQuery(Path<LocalDateTime> path,
                                                               Collection<? extends LocalDateTime> value) {
        DateTimePath<LocalDateTime> dateTimePath = (DateTimePath<LocalDateTime>) path;
        List<LocalDateTime> localDateTimes = new ArrayList<>(value);
        if (localDateTimes.size() == 1) {
            return Optional.of(dateTimePath.eq(localDateTimes.get(0)));
        } else if (localDateTimes.size() == 2) {
            LocalDateTime from = localDateTimes.get(0);
            LocalDateTime to = localDateTimes.get(1);
            return Optional.of(dateTimePath.between(from, to));
        } else {
            return Optional.of(dateTimePath.in(localDateTimes));
        }
    }

    private static Optional<Predicate> instantRangeQuery(Path<Instant> path,
                                                         Collection<? extends Instant> value) {
        DateTimePath<Instant> dateTimePath = (DateTimePath<Instant>) path;
        List<Instant> instants = new ArrayList<>(value);
        if (instants.size() == 1) {
            return Optional.of(dateTimePath.eq(instants.get(0)));
        } else if (instants.size() == 2) {
            Instant from = instants.get(0);
            Instant to = instants.get(1);
            return Optional.of(dateTimePath.between(from, to));
        } else {
            return Optional.of(dateTimePath.in(instants));
        }
    }

    private static Optional<Predicate> decimalRangeQuery(Path<BigDecimal> path,
                                                         Collection<? extends BigDecimal> value) {
        NumberPath<BigDecimal> decimalNumberPath = (NumberPath<BigDecimal>) path;
        List<BigDecimal> decimals = new ArrayList<>(value);
        if (decimals.size() == 1) {
            return Optional.of(decimalNumberPath.eq(decimals.get(0)));
        } else if (decimals.size() == 2) {
            BigDecimal from = decimals.get(0);
            BigDecimal to = decimals.get(1);
            return Optional.of(decimalNumberPath.between(from, to));
        } else {
            return Optional.of(decimalNumberPath.in(decimals));
        }
    }
}
