package business;

import dao.PansionDao;
import entity.Pansion;

import java.util.ArrayList;

public class PansionManager {
    private final PansionDao pansionDao;
    public PansionManager (){
        pansionDao = new PansionDao();
    }

    public ArrayList<Pansion> selectByQuery(String query){
        return this.pansionDao.selectByQuery(query);
    }
    public boolean save( Pansion pansion){
        return this.pansionDao.save(pansion);
    }
    public ArrayList<Pansion> findAllByHotelId (int hotelId){
        return this.pansionDao.findAllByHotelId(hotelId);
    }
}
