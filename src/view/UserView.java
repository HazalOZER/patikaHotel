package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends Layout {
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JComboBox<User.Role> cmb_role;
    private JButton btn_save;
    private User user;
    private UserManager userManager;

    public UserView(User user) {
        this.user = user;
        userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(300, 300);

        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        if (this.user.getId() != 0) {
            this.fld_username.setText(this.user.getUsername());
            this.fld_pass.setText(this.user.getPassword());
            this.cmb_role.getModel().setSelectedItem(this.user.getRole());
        }

        btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmty(new JTextField[]{this.fld_username, this.fld_pass})) {
                Helper.showMsg("fill");
            } else {

                boolean result;

                this.user.setUsername(fld_username.getText());
                this.user.setPassword(fld_pass.getText());
                this.user.setRole((User.Role) cmb_role.getSelectedItem());

                if (this.user.getId() !=0){
                result = this.userManager.update(user);
                }else {
                    result =this.userManager.save(user);
                }

                if(result){
                    Helper.showMsg("done");
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}

