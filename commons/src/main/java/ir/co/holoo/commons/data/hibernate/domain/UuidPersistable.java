package ir.co.holoo.commons.data.hibernate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Abstract base class for jpa entities.
 *
 * @author Mohammad Yazdian
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UuidPersistable implements Persistable<String>, Serializable {
    @Serial
    private static final long serialVersionUID = 8291127036921976436L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false, updatable = false, length = 36)
    private String id;

    protected UuidPersistable(String id) {
        this.setId(id);
    }

    @Override
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        Assert.hasText(id, "ID cannot be empty or null");
        this.id = UUID.fromString(id).toString();
    }

    @Transient
    @Override
    public boolean isNew() {
        return !StringUtils.hasText(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UuidPersistable that = (UuidPersistable) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .toString();
    }
}
