package org.slotegrator.dto.registration;

import org.apache.commons.lang3.RandomStringUtils;

public class UserRegistrationBuilder {

    UserRegistrationData userRegistrationData;

    public UserRegistrationBuilder() {
        this.userRegistrationData = new UserRegistrationData();
    }

    private String generateIdentifyId() {
        return String.valueOf(System.currentTimeMillis());
    }

    public UserRegistrationData buildUserRegistrationData() {
        String id = generateIdentifyId();
        userRegistrationData.setCurrencyCode("c" + id);
        userRegistrationData.setEmail(String.format("testemail%s@test.com", id));
        userRegistrationData.setName("TestName" + id);
        userRegistrationData.setPasswordChange("password" + id);
        userRegistrationData.setPasswordRepeat("password" + id);
        userRegistrationData.setSurname("Testsurname");
        userRegistrationData.setUsername("Testname");
        return userRegistrationData;
    }
}
