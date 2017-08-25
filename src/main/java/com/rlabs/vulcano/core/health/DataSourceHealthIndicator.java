package com.rlabs.vulcano.core.health;

import javax.sql.DataSource;

import com.rlabs.vulcano.core.commons.utils.DatabaseCheckRepository;
import com.rlabs.vulcano.core.health.Health.Builder;

/**
 * The DataSource Health Indicator.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class DataSourceHealthIndicator extends AbstractHealthIndicator {

	private static final String DEFAULT_QUERY = "SELECT 1 as _value";

	private DataSource dataSource;
	private DatabaseCheckRepository databaseCheck = new DatabaseCheckRepository();

	public DataSourceHealthIndicator(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void doHealthCheck(Builder builder) {
		if (null == this.dataSource) {
			builder.down().withDetail("database", "unknown");
		} else {
			check(builder);
		}
	}

	private void check(Health.Builder builder) {
		try {
			String product = this.dataSource.getConnection().getMetaData().getDatabaseProductName();
			builder.up().withDetail("database", product);

			String version = this.dataSource.getConnection().getMetaData().getDatabaseProductVersion();
			builder.up().withDetail("database.version", version);

			final String result = databaseCheck.executeSingleQuery(this.dataSource, DEFAULT_QUERY);
			builder.up().withDetail("database.statement", result);
		} catch (Exception e) {
			builder.down(e);
		}
	}

}
