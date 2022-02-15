package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = AccountCreateDto.AccountCreateDtoBuilder.class)
public class AccountCreateDto {

    @NonNull
    Long userId;
}
