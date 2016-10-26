package org.cpiatframework.config;

import org.cpiatframework.util.ConfigProperties;

public class Constants {
	public static final String OBJ_PROP_FILE = Configurations.getInstance().getProperty(ConfigProperties.OBJ_PROP_FILE);
	public static final String CHROME_DRIVER = Configurations.getInstance().getProperty(ConfigProperties.CHROME_DRIVER);
	public static final String FIREFOX_DRIVER = Configurations.getInstance().getProperty(ConfigProperties.FIREFOX_DRIVER);
	public static final String PATH_UPLOAD = Configurations.getInstance().getProperty(ConfigProperties.PATH_UPLOAD);
	public static final String BROWSER_PROPERTY_CHROME = Configurations.getInstance().getProperty(ConfigProperties.BROWSER_PROPERTY_CHROME);
	public static final String BROWSER_PROPERTY_FIREFOX= Configurations.getInstance().getProperty(ConfigProperties.BROWSER_PROPERTY_FIREFOX);
	
	public static final String PATH_CONFFILE = "config.properties";
	public static final int MYCONSTANT_ONE = 1;
}
