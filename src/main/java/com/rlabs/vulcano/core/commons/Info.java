package com.rlabs.vulcano.core.commons;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Expose information about service context.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public final class Info {

	private final Map<String, Object> details;
	private static final String POM_FILE = "./pom.xml";

	private Info(Builder builder) {
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public Object get(String key) {
		return this.details.get(key);
	}

	public static Info buildFromPOM() {
		final MavenXpp3Reader reader = new MavenXpp3Reader();
		final Info.Builder builder = new Info.Builder();

		try {
			final Model model = reader.read(new FileReader(POM_FILE));
			builder.withDetail("id", model.getArtifactId());
			builder.withDetail("name", model.getName());
			builder.withDetail("version", model.getVersion());
			builder.withDetail("description", model.getDescription());
		} catch (IOException | XmlPullParserException e) {
			builder.withDetail("version", "unknow");
		}

		return builder.build();
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
