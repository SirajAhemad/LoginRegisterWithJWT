package com.example.JwtToken.Pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegisterUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}
