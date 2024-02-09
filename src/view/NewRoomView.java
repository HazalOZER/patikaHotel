package view;

import business.HotelManager;
import business.PansionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pansion;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.util.ArrayList;

public class NewRoomView extends Layout {
    private JPanel container;
    private JComboBox<Room.Type> cmb_room_type;
    private JTextField fld_stock;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JButton btn_save;
    private JTextField fld_bed;
    private JTextField fld_square_meter;
    private JCheckBox chk_game_console;
    private JCheckBox chk_projection;
    private JCheckBox chk_cash_case;
    private JCheckBox chk_minibar;
    private JCheckBox chk_television;
    private JPanel top_pnl;
    private JPanel botton_pnl;
    private JLabel lbl_otel_info;
    private JComboBox cmb_season;
    private JComboBox cmb_pension;
    RoomManager roomManager;
    SeasonManager seasonManager;
    PansionManager pansionManager;
    Room room;
    Pansion pansion;
    Hotel hotel;
    HotelManager hotelManager;

    public NewRoomView(int selectedHotelId) {
        this.add(container);
        this.guiInitilaze(600, 500);
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.pansionManager = new PansionManager();
        this.hotelManager = new HotelManager();
        this.room = new Room();
        this.hotel = this.hotelManager.getById(selectedHotelId);

        this.lbl_otel_info.setText("Otel: "+this.hotel.getName());
        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.Type.values()));


        for (Season season : this.seasonManager.findAllByHotelId(selectedHotelId)) {
            this.cmb_season.addItem(new ComboItem(season.getId(),(season.getStart_date()+" & "+season.getFinish_date())));
        }
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



        btn_save.addActionListener(e -> {


            if (Helper.isFieldListEmty(new JTextField[]{this.fld_stock, this.fld_bed, this.fld_adult_price, this.fld_child_price, this.fld_square_meter})) {
                Helper.showMsg("fill");
            } else {

                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();

                int seasonId = 0;
                if (selectedSeason != null){
                    seasonId = selectedSeason.getKey();
                }

                this.room.setOtelId(selectedHotelId);
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setBed(Integer.parseInt(fld_bed.getText()));
                this.room.setAdultPrice(Integer.parseInt(fld_adult_price.getText()));
                this.room.setChildPrice(Integer.parseInt(fld_child_price.getText()));
                this.room.setSquareMeter(Integer.parseInt(fld_square_meter.getText()));
                this.room.setType((Room.Type) cmb_room_type.getSelectedItem());
                this.room.setProjection(chk_projection.isSelected());
                this.room.setMinibar(chk_minibar.isSelected());
                this.room.setTelevision(chk_television.isSelected());
                this.room.setGameConsole(chk_game_console.isSelected());
                this.room.setCashCase(chk_cash_case.isSelected());
                this.room.setSeasonId(seasonId);
                this.room.setPension(cmb_pension.getSelectedItem().toString());


                if (this.roomManager.save(room)) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }
}

