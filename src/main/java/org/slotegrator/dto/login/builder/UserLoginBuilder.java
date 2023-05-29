package org.slotegrator.dto.login.builder;

import org.slotegrator.config.CredentialsConfig;
import org.slotegrator.dto.login.UserLoginData;

public class UserLoginBuilder {

    UserLoginData userLoginData;

    public UserLoginBuilder() {
        this.userLoginData = new UserLoginData();
    }

    public UserLoginData buildUserData() {
        CredentialsConfig config = CredentialsConfig.getCredentialsConfig();
        userLoginData.setEmail(config.getEmail());
        userLoginData.setPassword(config.getPassword());
        return userLoginData;
    }
}
