package by.pwt.pilipenko.jd2final.filter;


import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {

    @Autowired
    IUserRoleService userRoleService;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {


        req.setAttribute("roles", userRoleService.getAllEntities());

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}