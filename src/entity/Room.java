package entity;

import business.HotelManager;

public class Room {
    private int id;
    private int otelId;

    private int seasonId;
    private Type type;
    private int stock;
    private double adultPrice;
    private double childPrice;
    private int bed;
    private double squareMeter;
    private boolean television;
    private boolean minibar;
    private boolean gameConsole;
    private boolean cashCase;
    private boolean projection;
    private String pension;
    private Hotel hotel;

    public enum Type {
        SINGLE,
        DOUBLE,
        JUNIOR,
        SUIT

    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOtelId() {
        return otelId;
    }

    public void setOtelId(int otelId) {
        this.otelId = otelId;
    }


    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public double getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(double squareMeter) {
        this.squareMeter = squareMeter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isCashCase() {
        return cashCase;
    }

    public void setCashCase(boolean cashCase) {
        this.cashCase = cashCase;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public Hotel getHotel() {

        HotelManager hotelManager = new HotelManager();
        return hotelManager.getById(getOtelId());

    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }
}
