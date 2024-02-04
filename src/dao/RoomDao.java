package dao;

import core.Db;
import entity.Hotel;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    public RoomDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        String sql = "SELECT * FROM public.room";
        return this.selectByQuery(sql);
    }

    public ArrayList<Room> findAllByHotelId(int hotelId) {
        String query = "SELECT * FROM public.room WHERE hotel_id = " + hotelId;

        return this.selectByQuery(query);
    }

    public ArrayList<Room> selectByQuery(String query) {

        ArrayList<Room> roomList = new ArrayList<>();

        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    public Room match(ResultSet rs) throws SQLException {

        Room obj = new Room();

        obj.setId(rs.getInt("id"));
        obj.setOtelId(rs.getInt("hotel_id"));
        obj.setPansionId(rs.getInt("pansion_id"));
        obj.setSeasonId(rs.getInt("season_id"));
        obj.setType(Room.Type.valueOf(rs.getString("room_type")));
        obj.setStock(rs.getInt("stock"));
        obj.setAdultPrice(rs.getDouble("adult_price"));
        obj.setChildPrice(rs.getDouble("child_price"));
        obj.setBed(rs.getInt("bed"));
        obj.setSquareMeter(rs.getDouble("square_meter"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGameConsole(rs.getBoolean("game_console"));
        obj.setCashCase(rs.getBoolean("cash_case"));
        obj.setProjection(rs.getBoolean("projection"));

        return obj;
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id, " +
                "pansion_id, " +
                "season_id, " +
                "room_type, " +
                "stock, " +
                "adult_price, " +
                "child_price, " +
                "bed, " +
                "square_meter, " +
                "television, " +
                "minibar, " +
                "game_console, " +
                "cash_case, " +
                "projection " +
                ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getOtelId());
            pr.setInt(2, room.getPansionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4, room.getType().name());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdultPrice());
            pr.setDouble(7, room.getChildPrice());
            pr.setInt(8, room.getBed());
            pr.setDouble(9, room.getSquareMeter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isCashCase());
            pr.setBoolean(14, room.isProjection());

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean stock(Room room){
        String query = "UPDATE public.room SET stock = ? WHERE id =? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,(room.getStock()-1));
            pr.setInt(2,room.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


}
