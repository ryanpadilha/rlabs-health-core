package com.rlabs.vulcano.core.health;

import java.sql.Connection;

import javax.sql.DataSource;

import com.rlabs.vulcano.core.commons.Health;
import com.rlabs.vulcano.core.commons.Health.Builder;
import com.rlabs.vulcano.core.health.impl.DatabaseCheckRepository;

/**
 * The DataSource Health Indicator.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class DataSourceHealthIndicator extends AbstractHealthIndicator {

	private static final String DEFAULT_QUERY = "SELECT 1 as _value";

	private String identification;
	private DataSource dataSource;
	private DatabaseCheckRepository databaseCheck = new DatabaseCheckRepository();

	public DataSourceHealthIndicator(String identification, DataSource dataSource) {
		this.identification = identification;
		this.dataSource = dataSource;
	}

	@Override
	protected void doHealthCheck(Builder builder) {
		if (null == this.dataSource) {
			builder.down().withDetail(this.identification != null ? this.identification : "database", "unknown");
		} else {
			check(builder);
		}
	}

	private void check(Health.Builder builder) {
		try (Connection connection = this.dataSource.getConnection()) {
			String product = connection.getMetaData().getDatabaseProductName();
			builder.up().withDetail("database", product);

			String version = connection.getMetaData().getDatabaseProductVersion();
			builder.up().withDetail("database.version", version);

			final String result = databaseCheck.executeSingleQuery(connection, DEFAULT_QUERY);
			builder.up().withDetail("database.statement", result);
		} catch (Exception e) {
			builder.down(e);
		}
	}

}
