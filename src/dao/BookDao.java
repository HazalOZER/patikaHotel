package dao;

import core.Db;
import entity.Book;
import entity.Hotel;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookDao {
    private final Connection con;

    public BookDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Book> findAll() {
        String sql = "SELECT * FROM public.book";
        return this.selectByQuery(sql);
    }

    public ArrayList<Book> selectByQuery(String query) {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                bookList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book match(ResultSet rs) throws SQLException {

        Book obj = new Book();


        obj.setId(rs.getInt("id"));
        obj.setRoomId(rs.getInt("room_id"));
        obj.setName(rs.getString("name"));
        obj.setMail(rs.getString("mail"));
        obj.setMpno(rs.getString("mpno"));
        obj.setTcno(rs.getString("tcno"));
        obj.setPension(rs.getString("pension"));
        obj.setStartDate(rs.getString("start_date"));
        obj.setFinishDate(rs.getString("finish_date"));
        obj.setAdult(rs.getInt("adult"));
        obj.setChild(rs.getInt("child"));
        obj.setPrice(rs.getDouble("price"));
        obj.setNote(rs.getString("note"));


        return obj;
    }

    public boolean save(Book book) {
        book.setStartDate(LocalDate.parse(book.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        book.setFinishDate(LocalDate.parse(book.getFinishDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());

        String query = "INSERT INTO public.book (room_id, " +
                "name, " +
                "mail, " +
                "mpno, " +
                "tcno, " +
                "note, " +
                "pension, " +
                "start_date, " +
                "finish_date, " +
                "price, " +
                "adult, " +
                "child )" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, book.getRoomId());
            pr.setString(2, book.getName());
            pr.setString(3, book.getMail());
            pr.setString(4, book.getMpno());
            pr.setString(5, book.getTcno());
            pr.setString(6, book.getNote());
            pr.setString(7, book.getPension());
            pr.setDate(8, Date.valueOf(book.getStartDate()));
            pr.setDate(9, Date.valueOf(book.getFinishDate()));
            pr.setDouble(10, book.getPrice());
            pr.setInt(11, book.getAdult());
            pr.setInt(12, book.getChild());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Book getById(int id) {
        Book obj = null;
        String query = "SELECT * FROM public.book WHERE id = ?";
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

    public boolean delete(int id) {

        String query = "DELETE FROM public.book WHERE id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean update(Book book) {


        String query = "UPDATE public.book SET " +

                "name = ? , " +
                "mail = ? ," +
                "mpno = ? ," +
                "tcno = ? ," +
                "note = ? " +

                "WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setString(1, book.getName());
            pr.setString(2, book.getMail());
            pr.setString(3, book.getMpno());
            pr.setString(4, book.getTcno());
            pr.setString(5, book.getNote());
            pr.setInt(6,book.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getRoomIdByBookId(int bookId){
       String query = "SELECT room_id FROM public.book WHERE id = "+bookId;
        int roomID =0;
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            if (rs.next()) {
                roomID = rs.getInt("room_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomID;
    }
    public int getPriceByBookId(int bookId){
        String query = "SELECT price FROM public.book WHERE id = "+bookId;
        int price =0;
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            if (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
