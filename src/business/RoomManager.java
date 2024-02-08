package business;

import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    public RoomManager(){
        this.roomDao =new RoomDao();
    }
    public ArrayList<Room> selectByQuery(String query) {

        return this.roomDao.selectByQuery(query);
    }
    public ArrayList<Room> findAll() {

        return this.roomDao.findAll();
    }
    public ArrayList<Room> findAllByHotelId(int hotelId){
        return this.roomDao.findAllByHotelId(hotelId);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {

        ArrayList<Object[]> roomObjList = new ArrayList<>();

        for (Room obj : roomList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getOtelId();
            rowObject[i++] = obj.getPansionId();
            rowObject[i++] = obj.getSeasonId();
            rowObject[i++] = obj.getType().toString();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            rowObject[i++] = obj.getBed();
            rowObject[i++] = obj.getSquareMeter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isCashCase();
            rowObject[i++] = obj.isProjection();

            roomObjList.add(rowObject);
        }

        return roomObjList;
    }
    public Room getByID(int id) {
        return this.roomDao.getById(id);
    }
    public boolean save(Room room){
        return this.roomDao.save(room);
    }
    public boolean decreaseStock(Room room){
        return this.roomDao.decreaseStock(room);
    }
    public boolean increaseStock (Room room) {return this.roomDao.increaseStock(room);}
}
