package by.pwt.pilipenko.jd2final.services;

import by.pwt.pilipenko.jd2final.dao.entities.User;
import by.pwt.pilipenko.jd2final.dao.interfaces.IUserDAO;
import by.pwt.pilipenko.jd2final.services.interfaces.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends AbstractEntityService<User> implements IUserService, UserDetailsService {

    private static final Logger log = Logger
            .getLogger(UserService.class);

    @Autowired
    IUserDAO userDAO;

    public List<User> searchEntityByName(String name) {

        User entity = new User();
        if (name != null && !name.equals("")) {
            entity.setLogin(name);
            entity.setFirstName(name);
            entity.setLastName(name);
            entity.setPersonalNumber(name);
        }
        return userDAO.findEntityByEntity(entity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = new User();
        user.setLogin(username);
        User user1 = userDAO.findEntityByPK(user);

        String login = user1.getLogin();
        String password = user1.getPassword();

        if (user1!=null) {

            GrantedAuthority auth = new GrantedAuthority() {
                private static final long serialVersionUID = 1L;

                public String getAuthority() {
                    return user1.getUserRole().getName();
                }
            };
            Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
            set.add(auth);

            return new org.springframework.security.core.userdetails.User(username, password, true,
                    true, true, true, set);
        }
        else
            throw new UsernameNotFoundException("User not found");

    }
}
