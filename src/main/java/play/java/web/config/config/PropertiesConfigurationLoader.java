package play.java.web.config.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfigurationLoader implements ConfigurationLoader {
    @Override
    public Configuration load(String configFilePaths) {
        String[] configFiles = configFilePaths.split(",");
        Properties properties = new Properties();
        for (String configPath : configFiles) {
            try {
                try (BufferedReader in = new BufferedReader(new FileReader(configPath))) {
                    properties.load(in);
                }
            } catch (IOException e) {
                throw new ConfigurationException(e);
            }
        }
        return new PropertiesConfiguration(properties);
    }
}
