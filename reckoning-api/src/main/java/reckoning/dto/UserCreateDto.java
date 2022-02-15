package reckoning.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class UserCreateDto {

    @NotBlank
    @Size(min = 1, max = 20)
    String firstName;
    @NotBlank
    @Size(min = 1, max = 20)
    String lastName;
    @NotBlank
    @Size(min = 1, max = 20)
    String username;
    @NotBlank
    @Size(min = 8, max = 32)
    String password;
    @NotBlank
    @Size(min = 8, max = 32)
    String repeatPassword;
}
