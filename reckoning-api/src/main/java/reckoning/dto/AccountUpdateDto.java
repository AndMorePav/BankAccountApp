package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = AccountUpdateDto.AccountUpdateDtoBuilder.class)
public class AccountUpdateDto {

    @NonNull
    Long accountId;
    @NotBlank
    String amount;
}
