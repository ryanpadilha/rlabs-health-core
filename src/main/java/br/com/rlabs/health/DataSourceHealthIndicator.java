package br.com.rlabs.health;

import javax.sql.DataSource;

import br.com.rlabs.health.Health.Builder;
import br.com.rlabs.health.db.ConnectionFactory;

/**
 * The DataSource Health Indicator.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class DataSourceHealthIndicator extends AbstractHealthIndicator {

	private static final String DEFAULT_QUERY = "SELECT 1";
	private DataSource dataSource;

	public DataSourceHealthIndicator() {

	}

	public DataSourceHealthIndicator(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		if (null == this.dataSource) {
			builder.up().withDetail("database", "unknown");
		} else {
			doDataSourceHealthCheck(builder);
		}
	}

	private void doDataSourceHealthCheck(Health.Builder builder) throws Exception {
		String product = ConnectionFactory.getConnection().getMetaData().getDatabaseProductName();
		builder.up().withDetail("database", product);

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
