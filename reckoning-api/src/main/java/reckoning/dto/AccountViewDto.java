package reckoning.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountViewDto {

    Long id;
    Long userId;
    String amount;
    String enabled;
}
