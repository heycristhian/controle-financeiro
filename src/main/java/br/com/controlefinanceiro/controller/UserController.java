package br.com.controlefinanceiro.controller;

import br.com.controlefinanceiro.controller.dto.request.UserRequest;
import br.com.controlefinanceiro.controller.dto.response.UserResponse;
import br.com.controlefinanceiro.domain.User;
import br.com.controlefinanceiro.mapper.UserMapper;
import br.com.controlefinanceiro.usecases.CreateUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.controlefinanceiro.util.MessageUtil.HTTP_RESPONSE;
import static br.com.controlefinanceiro.util.MessageUtil.INITIALIZING_CREATION;
import static br.com.controlefinanceiro.util.MessageUtil.MAP_FROM_TO;
import static br.com.controlefinanceiro.util.MessageUtil.USER_CLASS_NAME;
import static br.com.controlefinanceiro.util.MessageUtil.USER_RESPONSE_CLASS_NAME;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest) {
        log.info(INITIALIZING_CREATION, USER_CLASS_NAME);
        User user = createUser.execute(userRequest);

        log.info(MAP_FROM_TO, USER_CLASS_NAME, USER_RESPONSE_CLASS_NAME);
        UserResponse response = UserMapper.INSTANCE.toUserResponse(user);

        log.info(HTTP_RESPONSE);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
