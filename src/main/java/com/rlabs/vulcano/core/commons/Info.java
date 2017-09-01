package com.rlabs.vulcano.core.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

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

	private Info(Builder builder) {
		this.details = Collections.unmodifiableMap(builder.details);
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public Object get(String key) {
		return this.details.get(key);
	}

	public static Info buildFromPOM(final Class<?> clazz) {
		final MavenXpp3Reader reader = new MavenXpp3Reader();
		final Info.Builder builder = new Info.Builder();

		try {
			if (null == clazz) {
				throw new IOException("[] clazz null");
			}

			Model model = null;
			final File file = new File("./pom.xml");

			if (file.exists() && !file.isDirectory()) {
				model = reader.read(new FileReader(file));
			} else {
				// read file on jar-structure, example
				// /META-INF/maven/com.rlabs.oss.health/vulcano-health-core/pom.xml
				final String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
				traceJarFolder("pom.xml", new File(path));

				if (null != absolutePath) {
					model = reader.read(new FileReader(new File(absolutePath)));
				} else if (null != baos) {
					model = reader.read(new ByteArrayInputStream(baos.toByteArray()));
				}
			}

			if (null == model) {
				throw new IOException("[] file not found");
			}

			builder.withDetail("id", model.getArtifactId());
			builder.withDetail("name", model.getName());
			builder.withDetail("version", model.getVersion());
			builder.withDetail("description", model.getDescription());
		} catch (IOException | XmlPullParserException e) {
			builder.withDetail("version", "unknow");
		}

		return builder.build();
	}

	private static String absolutePath = null;
	private static ByteArrayOutputStream baos = null;

	/**
	 * Process content file of jar-file without lock io.file[stream].
	 *
	 * @param input
	 * @throws IOException
	 */
	private static void processContent(InputStream input) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int len;
		byte[] buffer = new byte[1024];
		while ((len = input.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}

		baos.flush();
		Info.baos = baos;
	}

	/**
	 * Read files inside jar-file.
	 *
	 * @param name
	 * @param source
	 * @throws IOException
	 */
	private static void traceJarFolder(String name, File source) throws IOException {
		if (source.isFile()) {
			try (JarFile jar = new JarFile(source);) {
				final Enumeration<JarEntry> entries = jar.entries();
				JarEntry entry = null;

				while (entries.hasMoreElements()) {
					entry = entries.nextElement();
					if (entry.getName().endsWith(name)) {
						ZipEntry zipEntry = jar.getEntry(entry.getName());
						if (null != zipEntry) {
							processContent(jar.getInputStream(zipEntry));
						}

						break;
					}
				}
			}
		}
	}

	/**
	 * This code is util for local-execution.
	 *
	 * @param name
	 * @param source
	 */
	@SuppressWarnings("unused")
	private static void traceFolder(String name, File source) {
		final File[] files = source.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isDirectory()) {
					traceFolder(name, file);
				} else if (name.equalsIgnoreCase(file.getName())) {
					absolutePath = file.getPath();
				}
			}
		}
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
