package Demo.service;


import Demo.dao.userDaoImpl;
import Demo.doMain.PageBean;
import Demo.doMain.User;

import java.util.List;
import java.util.Map;

public class userServiceImpl implements userService {
    userDaoImpl userDao = new userDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User varifyLogin(User user) {
        return userDao.varifyLogin(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }

    @Override
    public void deleteAllUser(String[] ids) {
        for (String id : ids) {
            userDao.deleteUser(id);
        }

    }

    @Override
    public User findUser(String id) {
        User user = userDao.findUser(id);
        return user;
    }

    /**
     * @param _currPage 当前第几页
     * @param _rows     每页多少行
     * @param condition
     * @return 执行分页条件查询操作
     */
    @Override
    public PageBean<User> userPage(String _currPage, String _rows, Map<String, String[]> condition) {
        PageBean<User> pb = new PageBean<User>();
        int currPage = Integer.parseInt(_currPage);
        int rows = Integer.parseInt(_rows);
        currPage = currPage < 1 ? 1 : currPage;
        rows = rows < 1 ? 1 : rows;
//        设置当前页数
        pb.setCurrPage(currPage);
//        设置每页行数
        pb.setRows(rows);
//        获取总条目数
        int count = userDao.Count(condition);
//        设置总条目数
        pb.setCount(count);
        //        设置总共多少页
        int pageCount = count % rows == 0 ? count / rows : count / rows + 1;
        pb.setPageCount(pageCount);
//        获取每页用户信息
        int start=(currPage-1)*rows;
        List<User> users = userDao.pageList(start, rows,condition);
        pb.setList(users);
        return pb;
    }
}
