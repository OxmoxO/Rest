package ox.mox.Rest.Configurations;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@Component
public class LoginSuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {

            httpServletResponse
                    .sendRedirect("/admin");

        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse
                    .sendRedirect("/user");
        }
    }
}