package Demo.service;

import Demo.doMain.PageBean;
import Demo.doMain.User;

import java.util.List;
import java.util.Map;

public interface userService {
    /**
     * 查找所有，不分页
     * @return
     */
    abstract List<User> findAll();

    /**
     * 登陆验证
     * @param user
     * @return
     */
    abstract User varifyLogin(User user);

    /**
     * 添加用户
     * @param user
     */
    abstract void addUser(User user);

    /**
     * 更新用户
     * @param user
     */
    abstract void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    abstract void deleteUser(String id);

    /**
     * 删除选中
     * @param ids
     */
    abstract void deleteAllUser(String[] ids);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    abstract User findUser(String id);

    /**
     * 分页按条件查询
     * @param _currPage
     * @param _rows
     * @param condition
     * @return
     */
    abstract PageBean<User> userPage(String _currPage, String _rows, Map<String, String[]> condition);
}
