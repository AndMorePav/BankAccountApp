package reckoning.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserViewDto {

    Long id;
    String firstName;
    String lastName;
    String username;
    List<AccountViewDto> accounts;
}
