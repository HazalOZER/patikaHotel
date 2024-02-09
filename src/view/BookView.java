package view;

import business.BookManager;
import business.PansionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Book;
import entity.Pansion;
import entity.Room;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class BookView extends Layout {
    private JPanel container;
    private JLabel lbl_hotel_info;
    private JLabel lbl_room_info;
    private JTextField fld_name;
    private JTextField fld_tcno;
    private JTextField fld_mpno;
    private JTextField fld_mail;
    private JTextField fld_note;
    private JButton btn_book;
    private JLabel lbl_book_info;
    private JLabel lbl_price_info;
    private Room room;
    private BookManager bookManager;
    Pansion pansion;
    PansionManager pansionManager;
    private Book book;
    double total;
    RoomManager roomManager;

    public BookView(Book book, Room room, String startDate, String finishDate, int adult, int child) {


        add(container);
        guiInitilaze(1000, 650);
        this.room = room;
        this.bookManager = new BookManager();
        this.book = book;
        this.roomManager = new RoomManager();
        System.out.println("////////////////////////");


        loadBookComponent(book, room, startDate, finishDate, adult, child);

    }

    public void loadBookComponent(Book book, Room room, String startDate, String finishDate, int adult, int child) {


///////////////////////////////////////////////////////////
        if (this.book.getId() != 0) {
            this.fld_name.setText(this.book.getName());
            this.fld_tcno.setText(this.book.getTcno());
            this.fld_mail.setText(this.book.getMail());
            this.fld_mpno.setText(this.book.getMpno());
            this.fld_note.setText(this.book.getNote());
        }
/////////////////////////////////////////////////////////////


        //Oda Bilgileri

        String lblRoomInfo = "Oda :" + room.getType().toString() + " Metrekare :" + room.getSquareMeter() + "Pansiyon Tipi: " + room.getPension() + "\n";

        ArrayList<String> roomInfo = new ArrayList<>();

        if (room.isProjection()) {
            roomInfo.add(" Projeksiyon");
        }
        if (room.isTelevision()) {
            roomInfo.add(" Televizyon");
        }
        if (room.isMinibar()) {
            roomInfo.add(" Minibar");
        }
        if (room.isGameConsole()) {
            roomInfo.add(" Oyun Konsolu");
        }
        if (room.isCashCase()) {
            roomInfo.add(" Kasa");
        }


        if (!roomInfo.isEmpty()) {
            String roomAllInfo = String.join(",", roomInfo);
            lblRoomInfo += roomAllInfo;
        }
        lbl_room_info.setText(lblRoomInfo);

        //Otel Bilgileri

        String lblHotelInfo = "Otel: " + room.getHotel().getName() +
                "\nAdres: " + room.getHotel().getAdress() + "/    ";


        ArrayList<String> hotelInfo = new ArrayList<>();

        if (room.getHotel().isRoomService()) {
            hotelInfo.add("7/24 Oda Servisi");
        }
        if (room.getHotel().isParking()) {
            hotelInfo.add("Ücretsiz Otopark");
        }
        if (room.getHotel().isWifi()) {
            hotelInfo.add("Ücretsiz Wifi");
        }
        if (room.getHotel().isPool()) {
            hotelInfo.add("Yüzme Havuzu");
        }
        if (room.getHotel().isSpa()) {
            hotelInfo.add("Spa");
        }
        if (room.getHotel().isFitnessCenter()) {
            hotelInfo.add("Fitness Salonu");
        }
        if (room.getHotel().isConcierge()) {
            hotelInfo.add("Otel Concierge");
        }
        if (!hotelInfo.isEmpty()) {
            String allHotelInfo = String.join(", ", hotelInfo);
            lblHotelInfo += allHotelInfo;
        }
        lbl_hotel_info.setText(lblHotelInfo);

        //Rezervasyon Bilgisi
        lbl_book_info.setText("Giriş tarihi: " + startDate +
                " Çıkış tarihi: " + finishDate +
                " Yetişkin: " + adult +
                " Çocuk: " + child);

/*
        this.pansionManager = new PansionManager();

        ArrayList<Pansion> pansions = pansionManager.findAllByHotelId(room.getOtelId());

        this.pansion = pansions.get(0);


        if (pansion.isUltraAllInc()) {
            cmb_pension.addItem("Ultra Her Şey Dahil");
        }
        if (pansion.isAllInc()) {
            cmb_pension.addItem("Her Şey Dahil");
        }
        if (pansion.isFullBoard()) {
            cmb_pension.addItem("Tam Pansiyon");
        }
        if (pansion.isHalfBoard()) {
            cmb_pension.addItem("Yarım Pansiyon");
        }
        if (pansion.isFullBoardEAlcohol()) {
            cmb_pension.addItem("Alkolsüz Her Şey Dahil");
        }
        if (pansion.isBedBreakfast()) {
            cmb_pension.addItem("Oda Kahvaltı");
        }
        if (pansion.isOnlyRoom()) {
            cmb_pension.addItem("Sadece Oda");
        }
*/


        //Kaydetme
        int dateNum = 1;
        //FARK
        if (book.getId() == 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate first = LocalDate.parse(startDate, formatter);
            LocalDate last = LocalDate.parse(finishDate, formatter);
            dateNum = (int) ChronoUnit.DAYS.between(first, last);

            this.total = ((room.getAdultPrice() * adult) +
                    (room.getChildPrice() * child)) * dateNum;
            lbl_price_info.setText("Fiyat: " + total);

        }else {
            lbl_price_info.setText("Fiyat: " + this.bookManager.getPriceByBookId(book.getId()));
        }




        //Fiyat bilgisi
       /* ArrayList<String> priceInfo = new ArrayList<>();
        if (pansion.isUltraAllInc()) {
            priceInfo.add("Ultra Her Şey Dahil: " + (total * 1.6));
        }
        if (pansion.isAllInc()) {
            priceInfo.add("Her Şey Dahil: " + (total * 1.5));
        }
        if (pansion.isFullBoard()) {
            priceInfo.add("Tam Pansiyon: " + (total * 1.3));
        }
        if (pansion.isHalfBoard()) {
            priceInfo.add("Yarım Pansiyon: " + (total * 1.2));
        }
        if (pansion.isFullBoardEAlcohol()) {
            priceInfo.add("Alkolsüz Her Şey Dahil: " + (total * 1.4));
        }
        if (pansion.isBedBreakfast()) {
            priceInfo.add("Oda Kahvaltı: " + (total * 1.1));
        }
        if (pansion.isOnlyRoom()) {
            priceInfo.add("Sadece Oda: " + (total));
        }
        String priceAllInfo = String.join(", ", priceInfo);

        lbl_price_info.setText(priceAllInfo);*/
/*
        if (book.getId() != 0) {
            lbl_price_info.setText("Fiyat: " + this.bookManager.getPriceByBookId(book.getId()));
        } else {
            lbl_price_info.setText("Fiyat: " + total);
        }*/

        btn_book.addActionListener(e -> {

            if (Helper.isFieldListEmty(new JTextField[]{this.fld_name, this.fld_tcno})) {
                Helper.showMsg("fill");
            } else {

                boolean result;

             /*   if (cmb_pension.getSelectedItem().equals("Sadece Oda")) {
                    total *= 1;
                } else if (cmb_pension.getSelectedItem().equals("Oda Kahvaltı")) {
                    total *= 1.1;
                } else if (cmb_pension.getSelectedItem().equals("Yarım Pansiyon")) {
                    total *= 1.2;
                } else if (cmb_pension.getSelectedItem().equals("Tam Pansiyon")) {
                    total *= 1.3;
                } else if (cmb_pension.getSelectedItem().equals("Alkolsüz Her Şey Dahil")) {
                    total *= 1.4;
                } else if (cmb_pension.getSelectedItem().equals("Her Şey Dahil")) {
                    total *= 1.5;
                } else if (cmb_pension.getSelectedItem().equals("Ultra Her Şey Dahil")) {
                    total *= 1.6;
                }*/

                //  System.out.println(total);/////

                //  System.out.println(cmb_pension.getSelectedItem().toString());

      /*       //  if (this.book.getId() !=0){
                   this.fld_name.setText(this.book.getName());
                   this.fld_tcno.setText(this.book.getTcno());
                   this.fld_mail.setText(this.book.getMail());
                   this.fld_mpno.setText(this.book.getMpno());
                   this.fld_note.setText(this.book.getNote());
              // }*/

                book.setRoomId(room.getId());
                book.setName(fld_name.getText());
                book.setMail(fld_mail.getText());
                book.setMpno(fld_mpno.getText());
                book.setTcno(fld_tcno.getText());
                book.setPension(room.getPension());
                book.setStartDate(startDate);
                book.setFinishDate(finishDate);
                book.setPrice(total);
                book.setAdult(adult);
                book.setChild(child);
                book.setNote(fld_note.getText());


                if (this.book.getId() != 0) {
                    result = this.bookManager.update(book);
                } else {
                    result = this.bookManager.save(book) && this.roomManager.decreaseStock(room);
                }

                if (result) {
                    Helper.showMsg("done");

                } else {
                    Helper.showMsg("error");
                }
            }
        });


    }


}
