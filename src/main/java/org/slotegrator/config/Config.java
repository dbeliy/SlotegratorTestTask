package org.slotegrator.config;

import lombok.Getter;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("endpoint.properties")
@Getter
public class Config {

    private static final Config CONFIG = new Config();

    @Property("url")
    private String url;
    @Property("login")
    private String login;
    @Property("register")
    private String register;
    @Property("getUser")
    private String getUser;
    @Property("getAllUsers")
    private String getAllUsers;
    @Property("deleteUser")
    private String deleteUser;

    private Config() {
        PropertyLoader.populate(this);
    }

    public static Config getCONFIG() {
        return CONFIG;
    }
}
