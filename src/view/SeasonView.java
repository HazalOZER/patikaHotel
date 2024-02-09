package view;

import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_season_info;
    private JTable tbl_season;
    private JButton btn_new_season;
    private Season season;
    private SeasonManager seasonManager;
    Hotel hotel;
    DefaultTableModel tmdl_season = new DefaultTableModel();

    public SeasonView(Hotel hotel) {
        this.season = new Season();
        this.seasonManager = new SeasonManager();
        this.hotel = new Hotel();
        this.add(container);
        this.guiInitilaze(500, 700);

        this.loadSeasonTable(hotel);
        this.loadSeasonComponent(hotel);
    }

    public void loadSeasonTable(Hotel hotel) {
        Object[] col_season = {"ID", "Otel ID", "Başlancıç Tarihi", "Bitiş tarihi"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAllByHotelId(hotel.getId()));
        this.createTable(tmdl_season, tbl_season, col_season, seasonList);
    }

    public void loadSeasonComponent(Hotel hotel) {
        btn_new_season.addActionListener(e -> {
            NewSeasonView newSeasonView = new NewSeasonView(hotel);
            newSeasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(hotel);
                }
            });
        });
    }

}
