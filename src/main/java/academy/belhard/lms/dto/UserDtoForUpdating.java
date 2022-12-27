package academy.belhard.lms.dto;

import academy.belhard.lms.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoForUpdating {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
