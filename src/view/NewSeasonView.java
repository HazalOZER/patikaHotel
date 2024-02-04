package view;

import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewSeasonView extends Layout{
    private JPanel container;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_finish_date;
    private JButton btn_save;
    private JTextField fld_cost;
    private Season season;
    private SeasonManager seasonManager;

    public NewSeasonView(Hotel hotel){
        add(container);
        guiInitilaze(300,400);
        seasonManager = new SeasonManager();
        season = new Season();
        loadSeasonComponent(hotel);
    }
    public void loadSeasonComponent(Hotel hotel){
        btn_save.addActionListener(e -> {

            if(Helper.isFieldListEmty(new JTextField[]{fld_finish_date,fld_start_date,fld_cost})){
                Helper.showMsg("fill");
            }else {
                this.season.setHotel_id(hotel.getId());
                this.season.setStart_date((LocalDate.parse(fld_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()));
                this.season.setFinish_date((LocalDate.parse(fld_finish_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()));
                this.season.setCost(Integer.parseInt(fld_cost.getText()));

                if(seasonManager.save(season)){
                    Helper.showMsg("done");
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
    private void createUIComponents() throws ParseException {
        this.fld_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
    }
}
