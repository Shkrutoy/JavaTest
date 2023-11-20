package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import dto.games.GameDTO;
import services.*;
import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.*;


public class RestAssuredTests {

    private final static String URL = "http://85.192.34.140:8080/";
    private String token;
    private final String login = "RTK-test20";
    private final String pass = "PAROL";

    //Тестирование активностей, связанных с пользователем
    @Test
    public void checkUserActivities() {

        RestAssured.baseURI = URL;

        //регистрация
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"login\":\"" + login + "\", \"pass\":\"" + pass + "\"}")
                .when()
                .post("api/signup")
                .then().log().body()
                .statusCode(201)
                .assertThat().body("register_data.login", equalTo(login))
                             .body("register_data.pass", equalTo(pass))
                             .body("register_data.pass", notNullValue());

        token = TokenService.getJwtToken(login,pass);

        GameDTO gameDTO = InitService.gameDTOInit();
        List<GameDTO> games;

        //добавление игры
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(gameDTO)
                .when()
                .post("api/user/games")
                .then()
                .statusCode(201);

        //просмотр списка игр
        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("api/user/games")
                .then().log().body()
                .statusCode(200)
                .extract().response();

        games = response.jsonPath().getList(".",GameDTO.class);

        int gamesCount = games.size();

        //добавление еще 1 игры
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(gameDTO)
                .when()
                .post("api/user/games")
                .then()
                .statusCode(201);

        //проверка счетчика
        response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("api/user/games")
                .then().log().body()
                .statusCode(200)
                .extract().response();

        games = response.jsonPath().getList(".", GameDTO.class);

        //неожидаемое значеник массивов
        if (games.size() - gamesCount != 1) {
            throw new IllegalArgumentException("Invalid count");
        }

        //удаление игры
        RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .delete("api/user/games/"+games.get(0).gameId)
                .then().log().body()
                .statusCode(200)
                .assertThat().body("info.status", equalTo("success"))
                .assertThat().body("info.id", nullValue());

        //смена пароля
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body("{\"password\":\"" + "pass" + "\"}")
                .when()
                .put("api/user")
                .then().log().body()
                .statusCode(200)
                .assertThat().body("info.status", equalTo("success"));

        //проверка смены пароля
        RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .get("api/user")
                .then().log().body()
                .statusCode(200)
                .assertThat().body("pass", equalTo("pass"));

        //удаление пользователя
        RestAssured.given()
                .auth().oauth2(token)
                .when()
                .delete("api/user")
                .then().log().body()
                .statusCode(200)
                .assertThat().body("info.status", equalTo("success"));
    }

    //Тестирование GET запросов на возвращение кода 200
    @Test
    public void checkGetRequestsReturn200(){

        RestAssured.baseURI = URL;

        CheckCodeWithoutTokenService.statusCodeRequest("api/users",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/easy/carBrands",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/easy/nums",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/easy/redirect",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/easy/version",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/files/download",200,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/files/downloadLastUploaded",200,true);
    }

    //Тестирование GET запросов на возвращение любуго другого кода
    @Test
    public void checkGetRequestsReturnNot200(){

        RestAssured.baseURI = URL;
        token = TokenService.getJwtToken("admin", "admin");

        CheckCodeWithoutTokenService.statusCodeRequest("api/bad-request",400,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/created",201,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/forbidden",403,true);
        CheckCodeWithTokenService.statusCodeRequest("api/invalid-uri",404,token);
        CheckCodeWithoutTokenService.statusCodeRequest("api/moved",301,false);
        CheckCodeWithoutTokenService.statusCodeRequest("api/no-content",204,true);
        CheckCodeWithoutTokenService.statusCodeRequest("api/unauthorized",401,true);
    }

    //Тестирование взаимодействия с файлами
    @Test
    public void checkFilesRequests() {

        RestAssured.baseURI = URL;

        //скачивание файла
        Response response = RestAssured.given()
                .contentType(ContentType.BINARY)
                .when()
                .get("/api/files/download")
                .then()
                .statusCode(200)
                .extract().response();

        FileSaveService.saveFile(response, "C:/Users/slaye/MyBusiness/dfile.JPEG");

        //скачивание последнего загруженного файла
        response = RestAssured.given()
                .contentType(ContentType.BINARY)
                .when()
                .get("/api/files/downloadLastUploaded")
                .then()
                .statusCode(200)
                .extract().response();

        FileSaveService.saveFile(response, "C:/Users/slaye/MyBusiness/dLastfile.JPEG");

        //загрузка файла на сервер
        File fileToUpload = new File("C:/Users/slaye/MyBusiness/BYBICK.JPEG");

        RestAssured.given()
                .multiPart(fileToUpload)
                .when()
                .post("/api/files/upload")
                .then().log().body()
                .statusCode(200)
                .assertThat().body("info.status", equalTo("success"));
    }
}
