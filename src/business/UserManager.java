package business;

import core.Db;
import core.Helper;
import dao.UserDao;
import entity.User;


import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();///////////
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> selectByQuery(String query) {

        return this.userDao.selectByQuery(query);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }


    //Yeni kullanıcı kaydetme
    public boolean save(User user) {

        if (this.hasUser(user)) {
            return false;
        }

        return userDao.save(user);
    }

    public boolean update(User user,boolean isDifferent) {

        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + " ID kayıtlı kullanıcı bulunamadı");
            return false;
        }

        if (this.hasUser(user)&&isDifferent) {
            return false;
        }

        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        return this.userDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {

        ArrayList<Object[]> userObjList = new ArrayList<>();
        for (User obj : userList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();

            userObjList.add(rowObject);
        }
        return userObjList;
    }

    public ArrayList<User> findAll() {

        return this.userDao.findAll();
    }

    public boolean hasUser(User user) {
        return this.userDao.hasUser(user);
    }

public ArrayList<User> findByRole(String userSearchRole){
        return userDao.findByRole(userSearchRole);
}
}
