package dao;

import core.Db;
import entity.Pansion;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PansionDao {
    private final Connection con;
    public PansionDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Pansion> findAllByHotelId(int hotelId) {
        String sql = "SELECT * FROM public.pension WHERE pension.hotel_id = "+hotelId;

        return this.selectByQuery(sql);
    }
    public Pansion match(ResultSet rs) throws SQLException {

        Pansion obj =new Pansion();

        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setUltraAllInc(rs.getBoolean("ultra_all_inc"));
        obj.setAllInc(rs.getBoolean("all_inc"));
        obj.setFullBoard(rs.getBoolean("full_board"));
        obj.setHalfBoard(rs.getBoolean("half_board"));
        obj.setFullBoardEAlcohol(rs.getBoolean("full_board_e_alcohol"));
        obj.setBedBreakfast(rs.getBoolean("bed_breakfast"));
        obj.setOnlyRoom(rs.getBoolean("room_only"));


        return obj;


    }
    public ArrayList<Pansion> selectByQuery(String query) {
        ArrayList<Pansion> PansionLst = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                PansionLst.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PansionLst;
    }


    public boolean save (Pansion pansion){
        String query = "INSERT INTO public.pension" +
                "(" +
                " hotel_id, " +
                "ultra_all_inc , " +
                "all_inc," +
                "full_board, " +
                "half_board, " +
                "full_board_e_alcohol, " +
                "bed_breakfast, " +
                "room_only" +
                ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pansion.getHotelId());
            pr.setBoolean(2, pansion.isUltraAllInc());
            pr.setBoolean(3, pansion.isAllInc());
            pr.setBoolean(4, pansion.isFullBoard());
            pr.setBoolean(5, pansion.isHalfBoard());
            pr.setBoolean(6, pansion.isFullBoardEAlcohol());
            pr.setBoolean(7, pansion.isBedBreakfast());
            pr.setBoolean(8, pansion.isOnlyRoom());

            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

}
