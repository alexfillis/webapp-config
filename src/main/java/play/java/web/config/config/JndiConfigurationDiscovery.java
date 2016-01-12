package play.java.web.config.config;

import javax.naming.Context;
import javax.naming.NamingException;

public class JndiConfigurationDiscovery implements ConfigurationDiscovery {
    private final String jndiName;
    private final ConfigurationLoader configurationLoader;
    private final Context context;

    public JndiConfigurationDiscovery(Context context, String jndiName, ConfigurationLoader configurationLoader) {
        this.context = context;
        this.jndiName = jndiName;
        this.configurationLoader = configurationLoader;
    }

    @Override
    public Configuration discover() {
        try {
            String configFile = (String)context.lookup(jndiName);

            return configurationLoader.load(configFile);
        } catch (NamingException e) {
            throw new ConfigurationException(e);
        }
    }
}
