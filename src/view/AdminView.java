package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JTable tbl_user;
    private JComboBox cmb_role;
    private JButton btn_search;
    private JButton btn_cncl;
    private JButton btn_new_user;
    private JLabel lbl_welcome;
    private JButton btn_employee;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    Object[] col_user;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;

        if (user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Hoşgeldin: " + this.user.getUsername());

        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();

    }

    public void loadUserTable(ArrayList<Object[]> userList) {

       col_user = new Object[] {"ID", "Kullanıcı Adı", "Şifre", "Rol"};
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);

    }

    public void loadUserComponent() {

        //yeni kullanıcı ekleme butonu
        btn_new_user.addActionListener(e -> {

            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });

        //popup menü

        tableRowSelect(this.tbl_user);//satır seçimi için

        this.user_menu = new JPopupMenu();

        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selecetUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selecetUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selecetUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selecetUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(this.user_menu);

        this.btn_search.addActionListener(e -> {
            String query = "SELECT * FROM public.user WHERE role = '" + this.cmb_role.getSelectedItem().toString() + "'";
            ArrayList<User> userList = userManager.selectByQuery(query);
            loadUserTable(userManager.getForTable(col_user.length, userList));
        });

        this.btn_employee.addActionListener(e -> {
            EmployeeView employeeView = new EmployeeView(user);
            dispose();
        });
        this.btn_cncl.addActionListener(e -> {
            loadUserTable(null);
        });
    }

    public void loadUserFilter() {
        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_role.getSelectedItem().toString();




    }
}
