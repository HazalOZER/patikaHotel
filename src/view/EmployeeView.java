package view;


import business.BookManager;
import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_top;
    private JTable tbl_hotel;
    private JTextField fld_name_city;
    private JButton btn_search;
    private JTable tbl_book;
    private JButton btn_admin;
    private JScrollPane scl_hotel;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_fnsh_date;
    private JComboBox cmb_adult;
    private JComboBox cmb_child;
    private JButton btn_new_hotel;
    private JLabel lbl_welcome;
    private JScrollPane scl_book;
    private JButton btn_all_hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_book = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private User user;
    private HotelManager hotelManager;
    private BookManager bookManager;
    Object[] col_hotel;

    public EmployeeView(User user) {
        this.user = user;
        this.add(container);
        this.guiInitilaze(1000, 500);
        hotelManager = new HotelManager();
        bookManager = new BookManager();

        this.lbl_welcome.setText("Hoşgeldin: " + this.user.getUsername());

        btn_admin.addActionListener(e -> {

            if (user.getRole().toString().equals("ADMIN")) {
                AdminView adminView = new AdminView(user);
                dispose();
            } else {
                Helper.showMsg("admin");
            }
        });

        loadHotelTable(null);
        loadHotelComponent();

        loadBookTable();


    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"ID", "Otel Adı", "Adres", "Yıldız", "Telefon numarası", "E-posta",
                "Ücretsiz Wifi", "Ücretsiz Otopark", "7/24 Oda Servisi", "Yüzme Havuzu", "SPA", "Fitness Center", "Hotel Concierge"};

        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, hotelManager.findAll());
        }

        this.createTable(tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadHotelComponent() {
//Butonlar

        btn_new_hotel.addActionListener(e -> {
            HotelView hotelView = new HotelView();
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });
        btn_all_hotel.addActionListener(e -> loadHotelTable(null));
        this.btn_search.addActionListener(e -> {

            if (Helper.isFieldListEmty(new JTextField[]{fld_strt_date, fld_strt_date})
                    || Integer.parseInt(cmb_adult.getSelectedItem().toString()) == 0) {
                Helper.showMsg("fill");
            } else {
                String nameCity = this.fld_name_city.getText();
                String startDate = this.fld_strt_date.getText();
                String finish_date = this.fld_fnsh_date.getText();
                int adult = Integer.parseInt(this.cmb_adult.getSelectedItem().toString());
                int child = Integer.parseInt(this.cmb_child.getSelectedItem().toString());

                ArrayList<Hotel> searchedHotelList = this.hotelManager.searchForBook(nameCity, startDate, finish_date, adult, child);
                ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, searchedHotelList);
                loadHotelTable(hotelList);

            }
        });

        //Popup menü
        tableRowSelect(this.tbl_hotel);
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Odalar").addActionListener(e -> {
            RoomView roomView = new RoomView(this.hotelManager.getById(getTableSelectedRow(tbl_hotel, 0)),
                    fld_strt_date.getText(),
                    fld_fnsh_date.getText(),
                    Integer.parseInt(cmb_adult.getSelectedItem().toString()),
                    Integer.parseInt(cmb_child.getSelectedItem().toString()));
           roomView.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosed(WindowEvent e) {
                   loadBookTable();
               }
           });
        });
        this.hotel_menu.add("Dönemler").addActionListener(e -> {

            Hotel hotel = new Hotel();
            hotel = hotelManager.getById(getTableSelectedRow(tbl_hotel, 0));
            SeasonView seasonView = new SeasonView(hotel);

        });
        this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selecetHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selecetHotelId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(this.hotel_menu);

    }

    public void loadBookTable() {
        Object[] col_book = {"ID", "Oda ID", "İsim", "E-posta", "Telefon numarası", "T.C.K.N.","Pansiyon","Başlangıç Tarihi","Bitiş Tarihi","Ücret","Not"};

        ArrayList<Object[]> bookList = this.bookManager.getForTable(col_book.length, bookManager.findAll());

        this.createTable(tmdl_book, this.tbl_book, col_book, bookList);
    }

    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }
}
