package by.pwt.pilipenko.jd2final.services;


import by.pwt.pilipenko.jd2final.dao.entities.User;

public class LoginLogic {

    public static boolean checkAdminLogin(String enterLogin, String enterPass) throws Exception {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User user1 = userService.getEntityByPK(user);

        return user1 != null && user1.getPassword().equals(enterPass);
    }

    public static boolean checkManagerLogin(String enterLogin, String enterPass) throws Exception {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User user1 = userService.getEntityByPK(user);

        return user1 != null && user1.getPassword().equals(enterPass) && user1.getUserRole().getName().equalsIgnoreCase("MANAGER");
    }

    public static boolean checkUserLogin(String enterLogin, String enterPass) throws Exception {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User user1 = userService.getEntityByPK(user);

        return user1 != null && user1.getPassword().equals(enterPass) && user1.getUserRole().getName().equalsIgnoreCase("CLIENT");
    }
}