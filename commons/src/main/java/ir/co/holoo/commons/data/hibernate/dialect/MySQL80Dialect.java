package ir.co.holoo.commons.data.hibernate.dialect;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.MySQLDialect;

/**
 * A SQL dialect for MySQL 8.0.X.
 *
 * @author Mohammad Yazdian
 */
public class MySQL80Dialect extends MySQLDialect {

    private static final DatabaseVersion MINIMUM_VERSION = DatabaseVersion.make( 8, 0 );

    public MySQL80Dialect() {
        super(DatabaseVersion.make(8, 0));
    }

    @Override
    protected DatabaseVersion getMinimumSupportedVersion() {
        return MINIMUM_VERSION;
    }
}
