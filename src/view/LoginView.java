package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JTextField fld_username;
    private JButton btn_login;
    private JPanel w_botton;
    private JPasswordField fld_pass;
    private final UserManager userManager;

    public LoginView() {

        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400, 400);

        //Login butonunun yapacağı işlem atandı

        btn_login.addActionListener(e -> {

            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};

            //fldların boş olup olmadığını kontrol edildi. Boş ise kullanıcı ekranına doldurması için uyarı verildi.

            if (Helper.isFieldListEmty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {

                //User nesnesi oluşturuldu. Girilen kullanıcı adı ve şifre findByLogin metoduna parametre olarak atandı
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());

                //user nesnesinin  null olup olmadığı kontorl edildi
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                } else {
                    //Kullanıcının rolüle göre ilgili ekrana yönlendirildi
                    if(loginUser.getRole().toString().equals("ADMIN")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    }else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }

                }
            }

        });
    }


}
