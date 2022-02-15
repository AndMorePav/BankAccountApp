package reckoning.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
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
