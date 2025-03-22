package org.knit.solutions.semestr1.lab11;

import java.util.List;

public interface UserService {
    void registerUser(String name, String email);
    List<User> listAllUsers();
    void deleteUser(int id);
    void updateUser(int id, String newName, String newEmail);
}
