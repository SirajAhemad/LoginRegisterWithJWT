package com.example.JwtToken.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessEnum {

    USER_REGISTERED("user.registered");

    private String code;
}
