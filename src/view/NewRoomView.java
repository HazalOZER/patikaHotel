package view;

import business.RoomManager;
import core.Helper;
import entity.Room;

import javax.swing.*;

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
    RoomManager roomManager;
    Room room;

    public NewRoomView(int selectedHotelId) {
        add(container);
        guiInitilaze(600, 500);
        this.roomManager = new RoomManager();
        this.room = new Room();

        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.Type.values()));

        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmty(new JTextField[]{this.fld_stock, this.fld_bed, this.fld_adult_price, this.fld_child_price, this.fld_square_meter})) {
                Helper.showMsg("fill");
            } else {


                this.room.setOtelId(selectedHotelId);
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setBed(Integer.parseInt(fld_bed.getText()));
                this.room.setAdultPrice(Integer.parseInt(fld_adult_price.getText()));
                this.room.setChildPrice(Integer.parseInt(fld_child_price.getText()));
                this.room.setSquareMeter(Integer.parseInt(fld_square_meter.getText()));
                this.room.setType((Room.Type) cmb_room_type.getSelectedItem());////////////////////////////
                this.room.setProjection(chk_projection.isSelected());
                this.room.setMinibar(chk_minibar.isSelected());
                this.room.setTelevision(chk_television.isSelected());
                this.room.setGameConsole(chk_game_console.isSelected());
                this.room.setCashCase(chk_cash_case.isSelected());


                if (this.roomManager.save(room)) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }
}

