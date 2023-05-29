package org.slotegrator.config;

import lombok.Getter;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("accountCredentials.properties")
@Getter
public class CredentialsConfig {
    private static final CredentialsConfig CredentialsConfig = new CredentialsConfig();

    @Property("email")
    private String email;
    @Property("password")
    private String password;

    private CredentialsConfig() {
        PropertyLoader.populate(this);
    }

    public static CredentialsConfig getCredentialsConfig() {
        return CredentialsConfig;
    }
}
