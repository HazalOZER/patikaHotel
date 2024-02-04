package dao;

import core.Db;
import entity.Hotel;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelDao {
   private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        String sql = "SELECT * FROM public.hotel";
        return this.selectByQuery(sql);
    }


    public Hotel match(ResultSet rs) throws SQLException {

        Hotel obj = new Hotel();

        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAdress(rs.getString("adress"));
        obj.setStars(rs.getInt("star"));
        obj.setMail(rs.getString("mail"));
        obj.setMpno(rs.getString("mpno"));
        obj.setParking(rs.getBoolean("parking"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitnessCenter(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoomService(rs.getBoolean("room_service"));

        return obj;
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public boolean save(Hotel hotel) {

        String query = "INSERT INTO public.hotel " +
                "(" +
                "name, " +
                "mail, " +
                "mpno, " +
                "star, " +
                "adress, " +
                "parking , " +
                "wifi , " +
                "pool , " +
                "fitness , " +
                "concierge , " +
                "spa , " +
                "room_service  " +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getMail());
            pr.setString(3, hotel.getMpno());
            pr.setInt(4,hotel.getStars());
            pr.setString(5,hotel.getAdress());
            pr.setBoolean(6, hotel.isParking());
            pr.setBoolean(7,hotel.isWifi());
            pr.setBoolean(8,hotel.isPool());
            pr.setBoolean(9,hotel.isFitnessCenter());
            pr.setBoolean(10,hotel.isConcierge());
            pr.setBoolean(11,hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());


            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /* public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET " +
                "name = ? , " +
                "adress = ? , " +
                "city = ? , " +
                "mail = ? , " +
                "mpno = ? , " +
                "star = ?  " +
                "WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAdress());
            pr.setString(3, hotel.getCity());
            pr.setString(4, hotel.getMail());
            pr.setString(5, hotel.getMpno());
            pr.setInt(6,hotel.getStars());
            pr.setInt(7, hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }*/

    public boolean delete(int id) {

        String query = "DELETE FROM public.hotel WHERE id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ?";
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

    public ArrayList<Hotel> searchForBooking(String nameCity, String start_date, String finish_date, int adult, int child) {

        start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        finish_date = LocalDate.parse(finish_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        String query="(SELECT public.hotel.* FROM public.hotel " +
                "LEFT JOIN public.room " +
                "ON hotel.id = room.hotel_id " +
                "WHERE " +
                "(" +
                "(hotel.name ILIKE '%"+nameCity+"%') " +
                "OR " +
                "(hotel.adress ILIKE '%"+nameCity+"%')" +
                ") " +
                "AND " +
                "(public.room.stock >0) " +
                "AND  " +
                "(room.bed >="+(adult+child)+")" +
                ") " +
                "INTERSECT" +
                "( " +
                "SELECT public.hotel.* FROM public.hotel " +
                "LEFT join public.season " +
                "ON hotel.id = season.hotel_id " +
                "WHERE   " +
                "'"+Date.valueOf(start_date)+"' BETWEEN season.start_date AND season.finish_Date " +
                "AND " +
                "'"+Date.valueOf(finish_date)+"' BETWEEN season.start_date AND season.finish_Date) ";



        ArrayList<Hotel> searchedHotelList = this.selectByQuery(query);



        return searchedHotelList;

    }
    public int newHotelId(){
        String query = "SELECT MAX(id) FROM public.hotel LIMIT 1; ";
        int last =0;
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
           if (rs.next()) {
            last = rs.getInt("max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last;
    }

}
