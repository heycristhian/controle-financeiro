package br.com.controlefinanceiro.mother;

import br.com.controlefinanceiro.controller.dto.request.UserRequest;

public class UserRequestMother {
    public static UserRequest getUserRequest() {
        return UserRequest.builder()
                .build();
    }
}
