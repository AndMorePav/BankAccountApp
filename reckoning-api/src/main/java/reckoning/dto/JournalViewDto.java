package reckoning.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JournalViewDto {

    Long id;
    Long accountId;
    String operationType;
    String initialAmount;
    String finalAmount;
    String operationTime;
}
