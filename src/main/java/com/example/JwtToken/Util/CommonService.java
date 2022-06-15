package com.example.JwtToken.Util;

import com.example.JwtToken.Constants.ApplicationResponseConstants;
import com.example.JwtToken.Pojo.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CommonService {

    @Autowired
    private MessageSource messageSource;

    public GenericResponse generateGenericSuccessResponse(final Object object) {
        return new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE_CODE,
                ApplicationResponseConstants.SUCCESS_RESPONSE, object);

    }
    public GenericResponse generateSuccessResponseWithMessageKey(final String code) {
        return new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE_CODE, getMessageByCode(code));
    }

    public GenericResponse generateSuccessResponseWithMessageKeyAndData(final String message, final Object data) {
        return new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE_CODE, getMessageByCode(message), data);
    }

    public String getMessageByCode(final String string) {

        return getMessageSource().getMessage(string, null, LocaleContextHolder.getLocale());
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

}
