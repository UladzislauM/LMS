package academy.belhard.lms.service.dto.user;

import lombok.Data;

@Data
public class UserDtoForSave {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private ContactPreferencesDto contactPreferences;
    private RoleDto role;
    private String socialMedia;
}
