package entity;

public class Pansion {
    private int id;
    private int hotelId;
    private boolean ultraAllInc;
    private boolean allInc;
    private boolean fullBoard;
    private boolean halfBoard;
    private boolean fullBoardEAlcohol;
    private boolean bedBreakfast;
    private boolean onlyRoom;

    public Pansion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public boolean isUltraAllInc() {
        return ultraAllInc;
    }

    public void setUltraAllInc(boolean ultraAllInc) {
        this.ultraAllInc = ultraAllInc;
    }

    public boolean isAllInc() {
        return allInc;
    }

    public void setAllInc(boolean allInc) {
        this.allInc = allInc;
    }

    public boolean isFullBoard() {
        return fullBoard;
    }

    public void setFullBoard(boolean fullBoard) {
        this.fullBoard = fullBoard;
    }

    public boolean isHalfBoard() {
        return halfBoard;
    }

    public void setHalfBoard(boolean halfBoard) {
        this.halfBoard = halfBoard;
    }

    public boolean isFullBoardEAlcohol() {
        return fullBoardEAlcohol;
    }

    public void setFullBoardEAlcohol(boolean fullBoardEAlcohol) {
        this.fullBoardEAlcohol = fullBoardEAlcohol;
    }

    public boolean isBedBreakfast() {
        return bedBreakfast;
    }

    public void setBedBreakfast(boolean bedBreakfast) {
        this.bedBreakfast = bedBreakfast;
    }

    public boolean isOnlyRoom() {
        return onlyRoom;
    }

    public void setOnlyRoom(boolean onlyRoom) {
        this.onlyRoom = onlyRoom;
    }
}
