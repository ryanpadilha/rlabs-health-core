package br.com.rlabs.commons.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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

	public List<Object> executeSingleQuery(final String query) throws SQLException {
		final List<Object> collection = Collections.emptyList();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				collection.add("1");
			}
		} catch (SQLException e) {
			LOGGER.error(e + " | " + String.format("query: %s", query));
			throw new SQLException(e);
		}

		return collection;
	}
}
