package com.galaxia.clima.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

	protected static Logger LOG = Logger.getLogger(Config.class);

	private static String CONFIG_FILENAME = "clima-rest.properties";
	private static String VERSION_FILENAME = "version.properties";

	private static Properties properties = new Properties();

	private static boolean desarrollo = false;

	static {
		try {
			load();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void load() throws Exception {

		InputStream propStream = null;

		String paramProperties = System.getProperty(CONFIG_FILENAME);
		String warProperties = "/" + CONFIG_FILENAME;

		if (paramProperties != null) {
			LOG.info("Archivo de propiedades definido en parámetro " + CONFIG_FILENAME + " = " + paramProperties);
			propStream = new FileInputStream(new File(paramProperties));
		} else {
			LOG.info("Archivo de propiedades " + CONFIG_FILENAME + " dentro del WAR:" + Config.class.getResource(warProperties));
			propStream = Config.class.getResourceAsStream(warProperties);
		}

		properties.clear();
		properties.load(propStream);

		String development = (String) properties.get("development");

		if ("true".equals(development))
			desarrollo = true;
		else
			desarrollo = false;

		propStream.close();

		Properties versionProperties = new Properties();
		InputStream versionInputStream = Config.class.getResourceAsStream("/" + VERSION_FILENAME);
		versionProperties.load(versionInputStream);
		properties.putAll(versionProperties);
		versionInputStream.close();

	}

	protected static String get(String property) {
		return (String) properties.get(property);
	}

	public static boolean isDevelopment() {
		return desarrollo;
	}

	public static String getWorkdir() {
		return (String) properties.get("workdir");
	}

	public static String getDatasourceJndiName() {
		return get("datasource.jndi.name");
	}

	public static String getVersion() {
		return properties.getProperty("version");
	}

	public static String getNodeName() {
		return System.getProperty("jboss.node.name");
	}

	public static String getBuildDate() {
		return properties.getProperty("build.date");
	}

	public static String getClientProduct() {
		return properties.getProperty("client.product");
	}

	public static String getDatasourceSchemaName() {
		return get("datasource.schema.name");
	}

	public static String getCORSOrigin() {
		return get("cors.origin");
	}

}
