package ru.company.services;

import io.restassured.RestAssured;

public class CheckCodeWithoutTokenService {

    public static void statusCodeRequest(String path, int statusCode, boolean mode){
        RestAssured.given()
                .redirects()
                .follow(mode)
                .get(path)
                .then()
                .statusCode(statusCode);
    }
}
