package br.com.controlefinanceiro.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "{must.not.blank}")
    @Size(max = 100, message = "{must.be.between}")
    @Email(message = "{must.be.email}")
    private String email;

    @NotBlank(message = "{must.not.blank}")
    @Size(min = 6, max = 16, message = "{must.be.between}")
    private String username;

    @NotBlank(message = "{must.not.blank}")
    @Size(min = 8, max = 32, message = "{must.be.between}")
    private String password;
}
