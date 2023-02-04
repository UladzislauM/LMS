package academy.belhard.lms.filter;

import academy.belhard.lms.service.exception.LmsException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class NotAuthorizationFilter extends HttpFilter {

    public static final String MESSAGE = "Please, login! Or add permissions!";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (isRequireAuthorization(req)) { //FixMe Before deploy change to NOT equals!
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.toString().equals("anonymousUser")) {
               throw new LmsException(MESSAGE);
            }
        }
        chain.doFilter(req, res);
    }

    private boolean isRequireAuthorization(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String num = "";
        if(!req.getRequestURI().equals("/")) {
            String[] strings = req.getRequestURI().split("/");
            num = strings[strings.length - 1];
        }
        return uri.equals("/login")
                || uri.equals("/courses/student")
                || uri.equals("/courses/" + num)
                || uri.equals("/courses/createForm")
                || uri.equals("/courses/create")
                || uri.equals("/courses/updateForm/" + num)
                || uri.equals("/courses/update/" + num)
                || uri.equals("/courses/delete/" + num)
                || uri.equals("/homeworks")
                || uri.equals("/homeworks/student/" + num)
                || uri.equals("/homeworks/" + num)
                || uri.equals("/homeworks/create")
                || uri.equals("/homeworks/update/" + num)
                || uri.equals("/homeworks/delete/" + num)
                || uri.equals("/lessons")
                || uri.equals("/lessons/" + num)
                || uri.equals("/lessons/read/" + num)
                || uri.equals("/lessons/updateForm/" + num)
                || uri.equals("/lessons/update/" + num)
                || uri.equals("/lessons/delete/" + num)
                || uri.equals("/lessons/create")
                || uri.equals("/lessons/createForm")
                || uri.equals("/requests/create")
                || uri.equals("/requests")
                || uri.equals("/requests/student" + num)
                || uri.equals("/requests/" + num)
                || uri.equals("/requests/update/" + num)
                || uri.equals("/users/create")
                || uri.equals("/users")
                || uri.equals("/users/" + num)
                || uri.equals("/users/personal")
                || uri.equals("/users/update/" + num)
                || uri.equals("/users/delete/" + num);
    }
}