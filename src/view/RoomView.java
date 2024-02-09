package view;

import business.RoomManager;
import entity.Book;
import entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RoomView extends Layout {
    private JPanel container;
    private JButton btn_new_room;
    private JTable tbl_room;
    private JScrollPane scl_pnl;
    private JTable tbl_all;
    private JLabel lbl_info;
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_room_all = new DefaultTableModel();
    private RoomManager roomManager;
    private JPopupMenu room_menu;
    Hotel hotel;

    public RoomView(Hotel hotel, String startDate, String finishDate, int adult, int child) {

        this.add(container);
        this.guiInitilaze(800, 450);
        this.roomManager = new RoomManager();
        this.hotel = hotel;
        this.loadRoomTable(hotel, startDate, finishDate, adult, child);
        this.loadRoomComponent(hotel, startDate, finishDate, adult, child);

    }

    public void loadRoomTable(Hotel hotel, String startDate, String finishDate, int adult, int child) {


        Object[] col_room = {"ID", "Otel ID", "Pansiyon", "Season ID", "Oda Tipi", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı",
                "Yatak Kapasitesi", "Metrekare", "Televizyon", "Minibar", "Oyun Konsolu", "Kasa", "Projeksiyon"};

        ArrayList<Object[]> availableRoomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAvailableRoomByHotelId(
                hotel.getId(),
                startDate,
                finishDate,
                adult,
                child));

        if (availableRoomList.isEmpty()) {
            this.lbl_info.setText("Aranan özelliklerde müsait oda bulunmamaktadır. Oteldeki diğer odaları tablo-2den görebilirsiniz");
        } else {
            this.lbl_info.setText("Müsait oda sayısı: " + availableRoomList.size());
        }

        this.createTable(tmdl_room, tbl_room, col_room, availableRoomList);
        ArrayList<Object[]> allRoomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAllByHotelId(hotel.getId()));
        this.createTable(tmdl_room_all, tbl_all, col_room, allRoomList);
    }

    public void loadRoomComponent(Hotel hotel, String startDate, String finishDate, int adult, int child) {

        //Buton
        btn_new_room.addActionListener(e -> {
            NewRoomView newRoomView = new NewRoomView(hotel.getId());
            newRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(hotel, startDate, finishDate, adult, child);
                }
            });
        });

        //Popup

        tableRowSelect(this.tbl_room);

        this.room_menu = new JPopupMenu();

        this.room_menu.add("Rezerve Et").addActionListener(e -> {

            int selecetRoomId = this.getTableSelectedRow(tbl_room, 0);
            BookView bookView = new BookView(new Book(),
                    this.roomManager.getByID(selecetRoomId),
                    startDate,
                    finishDate,
                    adult,
                    child);
            bookView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   loadRoomTable(hotel, startDate, finishDate, adult, child);
                }
            });
        });
        this.tbl_room.setComponentPopupMenu(this.room_menu);

    }
}
