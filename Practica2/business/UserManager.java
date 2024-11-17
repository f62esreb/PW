package business;

import data.dao.UserDAO;
import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void printAllUsers() {
        try {
            List<UserDTO> users = userDAO.getAllUsers();
            users.forEach(user -> System.out.println(user.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
