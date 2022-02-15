package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = UserCreateDto.UserCreateDtoBuilder.class)
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
