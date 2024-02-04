package business;

import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> selectByQuery(String query) {
        return this.seasonDao.selectByQuery(query);
    }

    public boolean save(Season season) {
        return seasonDao.save(season);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season obj : seasonList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getFinish_date();
            rowObject[i] = obj.getCost();

            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }
    public ArrayList<Season> findAllByHotelId(int hotelId){
        return this.seasonDao.findAllByHotelId(hotelId);
    }

}
