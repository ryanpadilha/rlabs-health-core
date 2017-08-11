package br.com.rlabs.health.commons;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * The Constants class.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public final class Constants {

	/**
	 * Main Datasource
	 */
	public static final Config CONFIG;
	public static final String JDBC_URL;
	public static final String USERNAME;
	public static final String PASSWORD;
	public static final String SQL_CACHE_SIZE;
	public static final String PS_CACHE_SIZE;
	public static final String CLASS_NAME;
	public static final int POOL_SIZE;
	public static final long CONNECTION_TIMEOUT;

	static {
		CONFIG = ConfigFactory.load().getConfig("datasource");
		JDBC_URL = CONFIG.getString("jdbc.connection");
		USERNAME = CONFIG.getString("jdbc.username");
		PASSWORD = CONFIG.getString("jdbc.password");
		SQL_CACHE_SIZE = CONFIG.getString("cache.size.sql");
		PS_CACHE_SIZE = CONFIG.getString("cache.size.ps");
		CLASS_NAME = CONFIG.getString("driver.classname");
		POOL_SIZE = CONFIG.getInt("maximum.pool.size");
		CONNECTION_TIMEOUT = CONFIG.getLong("connection.timeout");
	}
}
