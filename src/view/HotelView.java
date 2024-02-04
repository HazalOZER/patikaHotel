package view;

import business.HotelManager;
import business.PansionManager;
import core.Helper;
import entity.Hotel;
import entity.Pansion;

import javax.swing.*;

public class HotelView extends Layout {
    private JPanel container;
    private JTextField fld_name;
    private JTextField fld_adress;
    private JTextField fld_mpno;
    private JTextField fld_mail;
    private JCheckBox chk_parking;
    private JCheckBox chk_room_service;
    private JCheckBox chk_wifi;
    private JCheckBox chk_pool;
    private JCheckBox chk_fitness;
    private JCheckBox chk_concierge;
    private JCheckBox chk_spa;
    private JButton btn_save;
    private JComboBox cmb_star;

    private JCheckBox chk_ultra_inc;
    private JCheckBox chk_all_inc;
    private JCheckBox chk_full_board;
    private JCheckBox chk_haf_board;
    private JCheckBox chk_full_e_alcohol;
    private JCheckBox chk_bed_breakfast;
    private JCheckBox chk_only_room;
    private HotelManager hotelManager;
    private Hotel hotel;
    private Pansion pansion;
    private PansionManager pansionManager;

    public HotelView() {

        this.pansion = new Pansion();

        this.hotelManager = new HotelManager();
        this.pansionManager = new PansionManager();
        this.add(container);
        this.guiInitilaze(700, 700);

        btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmty(new JTextField[]{this.fld_name, this.fld_adress, this.fld_mail, this.fld_mpno})) {
                Helper.showMsg("fill");
            } else {

                //OTEL TABLOSU KAYIT
                this.hotel = new Hotel();

                this.hotel.setName(fld_name.getText());
                this.hotel.setAdress(fld_adress.getText());
                this.hotel.setMail(fld_mail.getText());
                this.hotel.setMpno(fld_mpno.getText());
                this.hotel.setStars(Integer.parseInt(cmb_star.getSelectedItem().toString()));
                this.hotel.setConcierge(chk_concierge.isSelected());
                this.hotel.setRoomService(chk_room_service.isSelected());
                this.hotel.setSpa(chk_spa.isSelected());
                this.hotel.setPool(chk_pool.isSelected());
                this.hotel.setWifi(chk_wifi.isSelected());
                this.hotel.setFitnessCenter(chk_fitness.isSelected());
                this.hotel.setParking(chk_parking.isSelected());


                //PANSİYON TABLOSU KAYIT


                if (this.hotelManager.save(hotel)) {

                    this.pansion.setHotelId(this.hotelManager.newHotelId());
                    this.pansion.setUltraAllInc(chk_ultra_inc.isSelected());
                    this.pansion.setAllInc(chk_all_inc.isSelected());
                    this.pansion.setFullBoard(chk_full_board.isSelected());
                    this.pansion.setHalfBoard(chk_haf_board.isSelected());
                    this.pansion.setFullBoardEAlcohol(chk_full_e_alcohol.isSelected());
                    this.pansion.setBedBreakfast(chk_bed_breakfast.isSelected());
                    this.pansion.setOnlyRoom(chk_only_room.isSelected());

                    if (this.pansionManager.save(pansion)) {
                        Helper.showMsg("done");
                    }
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }


}
