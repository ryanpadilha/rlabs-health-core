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

	// TODO read-config manually, in future process automatically

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

	public static Property readProperties(Map<String, Object> properties) {
		final Property.Builder builder = new Property.Builder();

		if (null != properties && !properties.isEmpty()) {
			builder.withDetail(properties);
		} else {
			builder.withDetail("properties", "unknow");
		}

		return builder.build();
	}

	@Override
	public String toString() {
		return "Property [details=" + details + "]";
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
