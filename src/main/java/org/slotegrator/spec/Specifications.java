package org.slotegrator.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder().setBaseUri(url)
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .build();
    }

    public static ResponseSpecification statusCodeIs200() {
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }

    public static ResponseSpecification customStatusCode(int code) {
        return new ResponseSpecBuilder().expectStatusCode(code).build();
    }

    public static void installSpecification(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
