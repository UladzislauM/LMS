package academy.belhard.lms.service.dto;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.RoleUser;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUser role;
    private Boolean is_active;
    private List<Course> course_list;
    private Request request;
}
