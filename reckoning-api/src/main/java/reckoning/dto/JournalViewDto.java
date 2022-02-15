package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = JournalViewDto.JournalViewDtoBuilder.class)
public class JournalViewDto {

    Long id;
    Long accountId;
    String operationType;
    String initialAmount;
    String finalAmount;
    String operationTime;
}
