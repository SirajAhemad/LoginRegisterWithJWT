package com.example.JwtToken.Pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class LoginUserRequest implements Serializable {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
