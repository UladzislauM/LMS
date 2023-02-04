package academy.belhard.lms.filter;

import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.impl.UserAppDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.IOException;

public class UserPermissionsFilter extends HttpFilter {

    public static final String MESSAGE = "Please, add permissions!";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.toString().equals("anonymousUser") && isRequireAuthorization(req)) {
            UserAppDetails userAppDetails = (UserAppDetails) user;
            String role = userAppDetails.getRole();
            if (!role.equals(RoleDto.MANAGER.toString())) {
                throw new LmsException(MESSAGE);
            }
        }
        chain.doFilter(req, res);
    }
    private boolean isRequireAuthorization(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String num = "";
        if (!req.getRequestURI().equals("/")) {
            String[] strings = req.getRequestURI().split("/");
            num = strings[strings.length - 1];
            String regex = "[0-9]+";
            if(!num.matches(regex)){
                return false;
            }
        }
        return uri.equals("/login")
                || uri.equals("/courses/createForm")
                || uri.equals("/courses/create")
                || uri.equals("/courses/updateForm/" + num)
                || uri.equals("/courses/update/" + num)
                || uri.equals("/courses/delete/" + num)
                || uri.equals("/users/create")
                || uri.equals("/users")
                || uri.equals("/users/" + num)
                || uri.equals("/users/update/" + num)
                || uri.equals("/users/delete/" + num);
    }
}