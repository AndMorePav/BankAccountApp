package reckoning.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountCreateDto {

    @NonNull
    Long userId;
}
