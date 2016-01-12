package play.java.web.config.config;

import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesConfigurationTest {

    private static final String PROPERTY_NAME = "example.url";
    private static final String EXAMPLE_URL = "http://www.example.com/somewhere/something";

    @Test
    public void should_return_string_property_value() {
        // given
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_NAME, EXAMPLE_URL);

        Configuration configuration = new PropertiesConfiguration(properties);

        // when
        String propertyValue = configuration.get(PROPERTY_NAME);

        // then
        assertEquals(EXAMPLE_URL, propertyValue);
    }

    @Test
    public void modifying_Properties_should_not_change_Configuration() throws Exception {
        // given
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_NAME, EXAMPLE_URL);

        Configuration configuration = new PropertiesConfiguration(properties);

        String otherValue = "insignificant/value";
        properties.setProperty(PROPERTY_NAME, otherValue);

        // when
        String propertyValue = configuration.get(PROPERTY_NAME);

        // then
        assertEquals(EXAMPLE_URL, propertyValue);
    }
}