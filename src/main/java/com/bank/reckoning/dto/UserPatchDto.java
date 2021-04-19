package com.bank.reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = UserPatchDto.UserPatchDtoBuilder.class)
public class UserPatchDto {

    @NotBlank
    @Size(min = 1, max = 20)
    String username;
    @NotBlank
    @Size(min = 1, max = 20)
    String firstName;
    @NotBlank
    @Size(min = 1, max = 20)
    String lastName;
}
