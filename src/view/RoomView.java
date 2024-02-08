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
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private RoomManager roomManager;
    private JPopupMenu room_menu;
    Hotel hotel;

    public RoomView(Hotel hotel, String startDate, String finishDate, int adult, int child) {

        add(container);
        guiInitilaze(800, 450);
        roomManager = new RoomManager();
        hotel = hotel;
        loadRoomTable(hotel);

        loadRoomComponent(hotel, startDate, finishDate, adult, child);

    }

    public void loadRoomTable(Hotel hotel) {


        Object[] col_room = {"ID", "Otel ID", "Pansiyon ID", "Season ID", "Oda Tipi", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı",
                "Yatak Kapasitesi", "Metrekare", "Televizyon", "Minibar", "Oyun Konsolu", "Kasa", "Projeksiyon"};

        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAllByHotelId(hotel.getId()));

        this.createTable(tmdl_room, tbl_room, col_room, roomList);

    }

    public void loadRoomComponent(Hotel hotel, String startDate, String finishDate, int adult, int child) {

        //Buton
        btn_new_room.addActionListener(e -> {
            NewRoomView newRoomView = new NewRoomView(hotel.getId());
            newRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(hotel);
                }
            });
        });

        //Popup

        tableRowSelect(this.tbl_room);

        this.room_menu = new JPopupMenu();

        this.room_menu.add("Rezerve Et").addActionListener(e -> {

            int selecetRoomId = this.getTableSelectedRow(tbl_room, 0);
            BookView bookView = new BookView( new Book(),
                    this.roomManager.getByID(selecetRoomId),
                    startDate,
                    finishDate,
                    adult,
                    child);
bookView.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosed(WindowEvent e) {
        loadRoomTable(hotel);
    }
});
        });
        this.tbl_room.setComponentPopupMenu(this.room_menu);

    }
}
