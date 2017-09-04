package com.rlabs.vulcano.core.resource;

import com.rlabs.vulcano.core.commons.ApplicationHealthWrapper;
import com.rlabs.vulcano.core.commons.Environment;
import com.rlabs.vulcano.core.commons.Info;
import com.rlabs.vulcano.core.commons.Property;

/**
 * Health API Resource Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthResource {

	/**
	 * Verify the information about the service.<br>
	 * Suggested path [app-context/api/service]/info
	 *
	 * @return {@link Info}
	 */
	Info info();

	/**
	 * Verify the health of service and associated dependencies.<br>
	 * Suggested path [app-context/api/service]/health
	 *
	 * @return {@link ApplicationHealthWrapper}
	 */
	ApplicationHealthWrapper health();

	/**
	 * List all properties of service context.
	 *
	 * @return {@link Property}
	 */
	Property properties();

	/**
	 * List all system environment variables of service context.
	 *
	 * @return {@link Environment}
	 */
	Environment environment();

}
