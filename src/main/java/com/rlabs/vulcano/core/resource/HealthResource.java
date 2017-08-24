package com.rlabs.vulcano.core.resource;

import com.rlabs.vulcano.entity.vo.HealthEntity;
import com.rlabs.vulcano.entity.vo.InfoEntity;

/**
 * Health API Resource Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface HealthResource {

	InfoEntity info();

	HealthEntity health();

}
