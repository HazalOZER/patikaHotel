package view;


import business.BookManager;
import business.HotelManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Book;
import entity.Hotel;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private JPopupMenu book_menu;
    private User user;
    private Book book;
    private Room room;
    private Hotel hotel;
    private HotelManager hotelManager;
    private BookManager bookManager;
    private RoomManager roomManager;
    Object[] col_hotel;

    public EmployeeView(User user) {
        this.user = user;
        this.add(container);
        this.guiInitilaze(1000, 500);
        hotelManager = new HotelManager();
        bookManager = new BookManager();
        roomManager = new RoomManager();

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
        loadBookComponent();


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
        this.fld_strt_date.setText("01/01/2024");
        this.fld_fnsh_date.setText("02/01/2024");
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
                ArrayList<Object[]> hotelList = this.hotelManager.getForTable(this.col_hotel.length, searchedHotelList);
                this.loadHotelTable(hotelList);

            }
        });

        //Popup menü
        tableRowSelect(this.tbl_hotel);
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Odalar").addActionListener(e -> {
            if(Helper.isFieldListEmty(new JTextField[]{fld_strt_date, fld_strt_date })
            || Integer.parseInt(cmb_adult.getSelectedItem().toString()) == 0){
                Helper.showMsg("fill");
            }else{
            RoomView roomView = new RoomView(this.hotelManager.getById(getTableSelectedRow(tbl_hotel, 0)),
                    this.fld_strt_date.getText(),
                    this.fld_fnsh_date.getText(),
                    Integer.parseInt(this.cmb_adult.getSelectedItem().toString()),
                    Integer.parseInt(this.cmb_child.getSelectedItem().toString()));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBookTable();
                }
            });}
        });
        this.hotel_menu.add("Dönemler").addActionListener(e -> {


            this.hotel = hotelManager.getById(getTableSelectedRow(tbl_hotel, 0));
            SeasonView seasonView = new SeasonView(hotel);

        });
      /*  this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selecetHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.roomManager.deleteWithHotel(selecetHotelId)&& this.hotelManager.delete(selecetHotelId)) {
                    Helper.showMsg("done");
                    this.loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });*/

        this.tbl_hotel.setComponentPopupMenu(this.hotel_menu);

    }

    public void loadBookTable() {
        Object[] col_book = {"ID", "Oda ID", "İsim", "E-posta", "Telefon numarası", "T.C.K.N.", "Pansiyon", "Başlangıç Tarihi", "Bitiş Tarihi", "Yetişkin", "Çocuk", "Ücret", "Not"};

        ArrayList<Object[]> bookList = this.bookManager.getForTable(col_book.length, bookManager.findAll());

        this.createTable(this.tmdl_book, this.tbl_book, col_book, bookList);
    }

    public void loadBookComponent() {

        //PopUp menü

        this.tableRowSelect(this.tbl_book);

        this.book_menu = new JPopupMenu();


        this.book_menu.add("Güncelle").addActionListener(e -> {

            this.book = this.bookManager.getById(getTableSelectedRow(this.tbl_book, 0));
            this.room = this.roomManager.getByID(book.getRoomId());

            //String startDate = LocalDate.parse(this.book.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            //String finishDate = LocalDate.parse(book.getFinishDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();


            BookView bookView = new BookView(
                    this.book,
                    this.room,
                   this.book.getStartDate(),
                   this.book.getFinishDate(),
                   // startDate,
                   //finishDate,
                    this.book.getAdult(),
                    this.book.getChild());

            bookView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBookTable();
                }
            });
        });

        this.book_menu.add("Sil").addActionListener(e -> {

            if (Helper.confirm("sure")) {

                int selectedBookId = getTableSelectedRow(tbl_book, 0);
                int roomId = this.bookManager.getRoomIdByBookId(selectedBookId);
                this.room = this.roomManager.getByID(roomId);


                if (this.bookManager.delete(selectedBookId) && this.roomManager.increaseStock(room)) {
                    Helper.showMsg("done");
                    loadBookTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_book.setComponentPopupMenu(this.book_menu);
    }

    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }
}
