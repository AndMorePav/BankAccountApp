package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = AccountViewDto.AccountViewDtoBuilder.class)
public class AccountViewDto {

    Long id;
    Long userId;
    String amount;
    String enabled;
}
