package play.java.web.config.config;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.NamingException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JndiConfigurationDiscoveryTest {

    private static final String CONFIG_FILE_PROPERTY = "config.file";
    private static final String CONFIG_FILE_PATHS = "data/test/config/test.properties,data/test/config/password.properties,";

    @Test
    public void load_multiple_config_files() throws NamingException {
        // given
        Context context = mock(Context.class);
        ConfigurationLoader configurationLoader = mock(ConfigurationLoader.class);
        ConfigurationDiscovery configurationDiscovery = new JndiConfigurationDiscovery(context, CONFIG_FILE_PROPERTY, configurationLoader);

        when(context.lookup(CONFIG_FILE_PROPERTY)).thenReturn(toPaths(CONFIG_FILE_PATHS));

        Configuration mockConfig = mock(Configuration.class);
        when(configurationLoader.load(anyString())).thenReturn(mockConfig);

        when(mockConfig.get("test.system.url")).thenReturn("http://www.example.com");

        // when
        Configuration config = configurationDiscovery.discover();

        // then
        assertEquals("http://www.example.com", config.get("test.system.url"));
    }

    @Test(expected = ConfigurationException.class)
    public void throw_ConfigurationException_when_JNDI_problem() throws Exception {
        // given
        Context context = mock(Context.class);
        ConfigurationLoader configurationLoader = mock(ConfigurationLoader.class);
        ConfigurationDiscovery configurationDiscovery = new JndiConfigurationDiscovery(context, CONFIG_FILE_PROPERTY, configurationLoader);

        when(context.lookup(CONFIG_FILE_PROPERTY)).thenThrow(new NamingException("testing"));

        // when
        configurationDiscovery.discover();

        // then
    }

    private String toPaths(String configFilePaths) {
        String result = "";
        String[] relativePaths = configFilePaths.split(",");
        for (String relativePath : relativePaths) {
            Path filePath = Paths.get(relativePath);
            if (!result.isEmpty()) {
                result += ",";
            }
            result += filePath.toAbsolutePath().toString();
        }
        System.out.println(result);
        return result;
    }
}
