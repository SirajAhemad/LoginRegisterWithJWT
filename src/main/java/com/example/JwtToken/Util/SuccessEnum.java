package com.example.JwtToken.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessEnum {

    USER_REGISTERED("user.registered"),INVALID_PASSWORD("invalid.password");

    private String code;
}
