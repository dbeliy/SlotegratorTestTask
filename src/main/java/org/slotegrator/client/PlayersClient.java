package org.slotegrator.client;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.slotegrator.config.Config;
import org.slotegrator.dto.UserInfo;
import org.slotegrator.dto.login.LoginResponseDto;
import org.slotegrator.dto.login.UserLoginData;
import org.slotegrator.dto.registration.RegistrationResponseDto;
import org.slotegrator.dto.registration.UserRegistrationData;
import org.slotegrator.spec.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersClient {

    private final Config config = Config.getCONFIG();

    public LoginResponseDto login(UserLoginData userLoginData) {
        Specifications.installSpecification(Specifications.requestSpec(config.getUrl()), Specifications.customStatusCode(201));

        String loginResponse = RestAssured.given()
                .header("Authorization", "Basic dGVzdDkxOm9pd1NNVW1Rbzl1dXV5YzlLSVR3SA==")
                .body(new Gson().toJson(userLoginData))
                .when()
                .post(config.getLogin())
                .then()
                .extract().body().asString();

        return new Gson().fromJson(loginResponse, LoginResponseDto.class);
    }

    public RegistrationResponseDto register(UserRegistrationData userRegistrationData, String accessToken) {
        Specifications.installSpecification(Specifications.requestSpec(config.getUrl()), Specifications.customStatusCode(201));

        String registrationResponse = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .body(new Gson().toJson(userRegistrationData))
                .when()
                .post(config.getRegister())
                .then()
                .extract().body().asString();

        return new Gson().fromJson(registrationResponse, RegistrationResponseDto.class);
    }

    public UserInfo getOneUserByEmail(String email, String accessToken) {
        Specifications.installSpecification(Specifications.requestSpec(config.getUrl()), Specifications.customStatusCode(201));

        Map<String, String> user = new HashMap<>();
        user.put("email", email);

        String userInfo = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .body(user)
                .when()
                .post(config.getGetUser())
                .then()
                .extract().body().asString();

        return new Gson().fromJson(userInfo, UserInfo.class);
    }

    public List<UserInfo> getAllUsers(String accessToken) {
        Specifications.installSpecification(Specifications.requestSpec(config.getUrl()), Specifications.statusCodeIs200());

        return RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(config.getGetAllUsers())
                .then()
                .extract().body().jsonPath().getList("", UserInfo.class);
    }

    public void deleteUserById(String accessToken, Integer id) {
        Specifications.installSpecification(Specifications.requestSpec(config.getUrl()), Specifications.statusCodeIs200());

        RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(config.getDeleteUser() + "/" + id)
                .then()
                .extract();
    }
}
