package Demo.dao;

import Demo.doMain.User;

import java.util.List;
import java.util.Map;

public interface userDao {
    abstract List<User> findAll();
    abstract User varifyLogin(User user);
    abstract void addUser(User user);
    abstract void updateUser(User user);
    abstract void deleteUser(String id);
    abstract User findUser(String id);
    abstract int Count(Map<String, String[]> condition);
    abstract List<User> pageList(int start, int rows, Map<String, String[]> condition);
}
