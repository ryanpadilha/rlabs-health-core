package com.rlabs.vulcano.core.info;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Information about service context.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public final class Info {

	private final Map<String, Object> details;

	private Info(Builder builder) {
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public Object get(String key) {
		return this.details.get(key);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Info other = (Info) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Info [details=" + details + "]";
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

		public Info build() {
			return new Info(this);
		}

	}
}
