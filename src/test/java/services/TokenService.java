package services;

import dto.LoginDTO;
import dto.TokenDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenService {
    public static String getJwtToken(String login, String password ){
        LoginDTO loginDTO = new LoginDTO(login,password);
        RestAssured.baseURI = "http://85.192.34.140:8080";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("api/login")
                .then()
                .extract().response();

        TokenDTO tokenDTO = response.as(TokenDTO.class);
        return tokenDTO.getToken();
    }
}
