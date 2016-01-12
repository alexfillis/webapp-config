package play.java.web.config.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertiesConfigurationLoaderTest {

    @Test
    public void should_load_valid_properties_file() throws Exception {
        // given
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();

        String configFilePath = "data/test/config/test.properties";

        // when
        Configuration config = configurationLoader.load(configFilePath);

        // then
        assertEquals("http://www.example.com", config.get("test.system.url"));
        assertEquals("testing", config.get("test.system.user"));
    }

    @Test
    public void when_loading_multiple_files_last_file_should_take_precedence_where_properties_clash() throws Exception {
        // given
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();

        String configFilePaths = "data/test/config/test.properties,data/test/config/test2.properties";

        // when
        Configuration config = configurationLoader.load(configFilePaths);

        // then
        assertEquals("http://www.example.com", config.get("test.system.url"));
        assertEquals("smith", config.get("test.system.user"));
        assertEquals("/var/log", config.get("log.path"));
    }

    @Test(expected = ConfigurationException.class)
    public void throw_ConfigurationException_when_Loading_problem() throws Exception {
        // given
        ConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();

        String configFilePath = "some/nonsense/path/to/a/file.properties";

        // when
        configurationLoader.load(configFilePath);

        // then
    }
}