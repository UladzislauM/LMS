package academy.belhard.lms.service.dto.user;

import lombok.Data;

@Data
public class UserDtoForUpdate {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private ContactPreferencesDto contactPreferences;
    private String socialMedia;
    private RoleDto role;
    private boolean isActive;
}
