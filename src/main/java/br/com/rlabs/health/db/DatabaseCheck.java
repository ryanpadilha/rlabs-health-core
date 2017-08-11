package br.com.rlabs.health.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Database Check.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class DatabaseCheck {

	private static final Logger LOGGER = Logger.getLogger(DatabaseCheck.class);

	public List<Object> executeSingleQuery(final String query) {
		final List<Object> collection = Collections.emptyList();

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				collection.add("1");
			}
		} catch (SQLException e) {
			LOGGER.error(e); // log and ignore
		}

		return collection;
	}
}
