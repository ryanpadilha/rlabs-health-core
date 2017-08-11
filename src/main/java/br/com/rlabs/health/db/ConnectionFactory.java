package br.com.rlabs.health.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.rlabs.health.commons.Constants;

/**
 * The connection factory class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class ConnectionFactory {

	private static ConnectionFactory connection;
	private final DataSource dataSource;

	private ConnectionFactory() {
		final HikariConfig config = new HikariConfig();
		config.setJdbcUrl(Constants.JDBC_URL);
		config.setUsername(Constants.USERNAME);
		config.setPassword(Constants.PASSWORD);
		config.setConnectionTimeout(Constants.CONNECTION_TIMEOUT);
		config.setDriverClassName(Constants.CLASS_NAME);
		config.setMaximumPoolSize(Constants.POOL_SIZE);

		config.addDataSourceProperty("parsedSqlCacheSize", Constants.SQL_CACHE_SIZE);
		config.addDataSourceProperty("preparedStatementCacheSize", Constants.PS_CACHE_SIZE);

		this.dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null)
			connection = new ConnectionFactory();

		return connection.dataSource.getConnection();
	}
}
