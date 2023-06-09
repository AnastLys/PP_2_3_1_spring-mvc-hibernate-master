package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public List<User> listUsers();
    public User findUser(long id);

    public void deleteUser(long id);

    public void updeteUser(User user);
}
