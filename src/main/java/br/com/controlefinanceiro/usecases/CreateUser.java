package br.com.controlefinanceiro.usecases;

import br.com.controlefinanceiro.controller.dto.request.UserRequest;
import br.com.controlefinanceiro.domain.User;
import br.com.controlefinanceiro.mapper.UserMapper;
import br.com.controlefinanceiro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static br.com.controlefinanceiro.util.MessageUtil.INSERTING_OBEJECT_BD;
import static br.com.controlefinanceiro.util.MessageUtil.MAP_FROM_TO;
import static br.com.controlefinanceiro.util.MessageUtil.USER_CLASS_NAME;
import static br.com.controlefinanceiro.util.MessageUtil.USER_REQUEST_CLASS_NAME;

@Slf4j
@Component
public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UserRequest userRequest) {
        log.info(MAP_FROM_TO, USER_REQUEST_CLASS_NAME, USER_CLASS_NAME);
        User user = UserMapper.INSTANCE.toUser(userRequest);

        log.info(INSERTING_OBEJECT_BD, USER_CLASS_NAME);
        return userRepository.save(user);
    }
}
