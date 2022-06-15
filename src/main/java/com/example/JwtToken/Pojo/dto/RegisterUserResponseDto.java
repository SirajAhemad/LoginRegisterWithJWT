package com.example.JwtToken.Pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserResponseDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("token")
    private String token;

}
