package academy.belhard.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoForSaving {
    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
