package org.knit.solutions.semestr1.lab11;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registerUser(String name, String email) {
        User user = new User(-1, name, email);
        try {
            userDAO.create(user);
        }
        catch (Exception e) {
            System.out.println("Случилась фигня: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<User> listAllUsers() {
        try {
            return userDAO.findAll();
        }
        catch (Exception e) {
            System.out.println("Случилась фигня: " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        try {
            userDAO.delete(id);
        }
        catch (Exception e) {
            System.out.println("Случилась фигня: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void updateUser(int id, String newName, String newEmail) {
        try {
            User userToUpdate = new User(id, newName, newEmail);
            userDAO.update(userToUpdate);
        }
        catch (Exception e) {
            System.out.println("Случилась фигня: " + e.getLocalizedMessage());
        }
    }
}
