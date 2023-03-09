package br.com.controlefinanceiro.util;

import br.com.controlefinanceiro.controller.dto.request.UserRequest;
import br.com.controlefinanceiro.controller.dto.response.UserResponse;
import br.com.controlefinanceiro.domain.User;

public abstract class MessageUtil {

    private MessageUtil() {
    }

    public static final String MAP_FROM_TO = "Mapping '{}' to '{}'";

    public static final String INITIALIZING_CREATION = "Initializing '{}' creation request";

    public static final String HTTP_RESPONSE = "Returning response";

    public static final String INSERTING_OBEJECT_BD = "Inserting '{}' in the database";

    public static final String USER_CLASS_NAME = User.class.getSimpleName();
    public static final String USER_RESPONSE_CLASS_NAME = UserResponse.class.getSimpleName();
    public static final String USER_REQUEST_CLASS_NAME = UserRequest.class.getSimpleName();
}
