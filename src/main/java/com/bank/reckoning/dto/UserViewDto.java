package com.bank.reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = UserViewDto.UserViewDtoBuilder.class)
public class UserViewDto {

    Long id;
    String firstName;
    String lastName;
    String username;
    List<AccountViewDto> accounts;
}
