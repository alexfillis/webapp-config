package play.java.web.config.config;

import java.util.Properties;

public class PropertiesConfiguration implements Configuration {
    private final Properties properties;

    public PropertiesConfiguration(Properties properties) {
        this.properties = (Properties) properties.clone();
    }

    @Override
    public String get(String name) {
        return properties.getProperty(name);
    }
}
