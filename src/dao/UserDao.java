package dao;

import core.Db;
import core.Helper;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        String sql = "SELECT * FROM public.user";
        return selectByQuery(sql);
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE username = ? AND pass = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();

        obj.setId(rs.getInt("id"));
        obj.setUsername(rs.getString("username"));
        obj.setPassword(rs.getString("pass"));
        obj.setRole(User.Role.valueOf(rs.getString("role")));

        return obj;
    }

    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public boolean save(User user) {

                String query = "INSERT INTO public.user " +
                        "(" +
                        "username, " +
                        "pass, " +
                        "role " +
                        ")" +
                        " VALUES (?,?,?)";
                try {
                    PreparedStatement pr = this.con.prepareStatement(query);
                    pr.setString(1, user.getUsername());
                    pr.setString(2, user.getPassword());
                    pr.setString(3, user.getRole().toString());


                    return pr.executeUpdate() != -1;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "username = ? , " +
                "pass = ? , " +
                "role = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
                     pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id) {

        String query = "DELETE FROM public.user WHERE id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean hasUser(User user){
        String query = "SELECT * FROM public.user WHERE username = '" + user.getUsername() + "'";
        if (!this.selectByQuery(query).isEmpty()) {
            Helper.showMsg("has");
            return true;
        }
        return false;
    }
public ArrayList<User> findByRole(String userSearchRole){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user WHERE role = ? '"+userSearchRole+"'";

        try{

            ResultSet rs = this.con.createStatement().executeQuery(query);

            while(rs.next()){
                userList.add(this.match(rs));
            }

                    }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
}

}
