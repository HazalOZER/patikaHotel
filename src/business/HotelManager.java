package business;

import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    /* public Hotel findByLogin(String username, String password) {
         return this.hotelDao.findByLogin(username, password);
     }
 */
    public ArrayList<Hotel> selectByQuery(String query) {

        return this.hotelDao.selectByQuery(query);
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }


    //Yeni otel kaydetme
    public boolean save(Hotel hotel) {

        return hotelDao.save(hotel);
    }

  /*  public boolean update(Hotel hotel) {

        if (this.getById(hotel.getId()) == null) {
            Helper.showMsg(hotel.getId() + " ID kayıtlı otel bulunamadı");
            return false;
        }


        return this.hotelDao.update(hotel);
    }*/

    public boolean delete(int id) {
        return this.hotelDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList) {

        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for (Hotel obj : hotelList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAdress();
            rowObject[i++] = obj.getStars();
            rowObject[i++] = obj.getMpno();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isParking();
            rowObject[i++] = obj.isRoomService();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isFitnessCenter();
            rowObject[i] = obj.isConcierge();
            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }

    public ArrayList<Hotel> findAll() {

        return this.hotelDao.findAll();
    }

    public ArrayList<Hotel> searchForBook(String nameCity, String start_date, String finish_date, int adult, int child) {
        return this.hotelDao.searchForBooking(nameCity, start_date, finish_date, adult, child);
    }

    public int newHotelId() {
        return this.hotelDao.newHotelId();
    }


}
