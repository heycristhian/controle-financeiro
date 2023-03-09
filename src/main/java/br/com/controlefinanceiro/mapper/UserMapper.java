package br.com.controlefinanceiro.mapper;

import br.com.controlefinanceiro.controller.dto.request.UserRequest;
import br.com.controlefinanceiro.controller.dto.response.UserResponse;
import br.com.controlefinanceiro.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User user);

    User toUser(UserRequest userRequest);
}
