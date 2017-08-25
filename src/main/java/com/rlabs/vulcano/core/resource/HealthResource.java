package com.rlabs.vulcano.core.resource;

import com.rlabs.vulcano.core.commons.ApplicationHealthWrapper;
import com.rlabs.vulcano.core.commons.Info;

/**
 * Health API Resource Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthResource {

	Info info();

	ApplicationHealthWrapper health();

}
