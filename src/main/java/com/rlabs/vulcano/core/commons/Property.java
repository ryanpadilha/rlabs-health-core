package com.rlabs.vulcano.core.commons;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Expose information of property file of running-service.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class Property {

	// private static final String APP_CONF = ".conf";
	// private static final String APP_PROP = ".properties";

	private final Map<String, Object> details;

	private Property(Builder builder) {
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public Object get(String key) {
		return this.details.get(key);
	}

	public static class Builder {

		private final Map<String, Object> details;

		public Builder() {
			this.details = new LinkedHashMap<>();
		}

		public Builder withDetail(String key, Object value) {
			this.details.put(key, value);
			return this;
		}

		public Builder withDetail(Map<String, Object> details) {
			this.details.putAll(details);
			return this;
		}

		public Property build() {
			return new Property(this);
		}
	}
}
