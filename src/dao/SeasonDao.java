package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;
    public SeasonDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAllByHotelId(int hotelId) {
        String sql = "SELECT * FROM public.season WHERE season.hotel_id = "+hotelId;
        return this.selectByQuery(sql);
    }
    public Season match(ResultSet rs) throws SQLException {

        Season obj = new Season ();

        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStart_date(rs.getString("start_date"));
        obj.setFinish_date(rs.getString("finish_date"));
        obj.setCost(rs.getInt("cost"));


        return obj;
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }
    public boolean save(Season season) {

       // season.setStart_date(LocalDate.parse(season.getStart_date(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
 //      season.setFinish_date(LocalDate.parse(season.getFinish_date(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());


        String query = "INSERT INTO public.season " +
                "(" +
                "hotel_id, " +
                "start_date, " +
                "finish_date, " +
                "cost " +
                ")" +
                " VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            pr.setInt(4,season.getCost());


            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
