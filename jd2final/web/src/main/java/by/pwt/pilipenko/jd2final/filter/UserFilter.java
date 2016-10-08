package by.pwt.pilipenko.jd2final.filter;


import by.pwt.pilipenko.jd2final.services.interfaces.IUserRoleService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class UserFilter implements Filter {

    @Autowired
    IUserRoleService userRoleService;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {



        try {
            req.setAttribute("roles", userRoleService.getAllEntities());
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}