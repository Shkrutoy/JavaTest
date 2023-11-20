package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginDTO {
    @JsonProperty("username")
    private String login;
    @JsonProperty("password")
    private String password;

    public LoginDTO(){}

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
