package reckoning.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class AccountUpdateDto {

    @NonNull
    Long accountId;
    @NotBlank
    String amount;
}
