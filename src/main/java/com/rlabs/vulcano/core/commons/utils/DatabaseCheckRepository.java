package com.rlabs.vulcano.core.commons.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * The Database Check Meta Repository.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public final class DatabaseCheckRepository {

	private static final Logger LOGGER = Logger.getLogger(DatabaseCheckRepository.class);

	public String executeSingleQuery(final DataSource datasource, final String query) throws SQLException {
		try (Connection connection = datasource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();) {
			return (rs.next() ? "OK" : "NOK");
		} catch (SQLException e) {
			LOGGER.error(e + " | " + String.format("query: %s", query));
			throw new SQLException(e);
		}
	}
}
