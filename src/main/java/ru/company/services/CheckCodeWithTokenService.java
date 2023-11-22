package ru.company.services;

import io.restassured.RestAssured;

public class CheckCodeWithTokenService {
    public static void statusCodeRequest(String path,int statusCode, String token){
        RestAssured.given()
                .auth().oauth2(token)
                .get(path)
                .then()
                .statusCode(statusCode);
    }
}
