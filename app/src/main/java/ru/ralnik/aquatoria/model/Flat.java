package ru.ralnik.aquatoria.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "flats")
public class Flat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
//    @ColumnInfo(name = "_id")
//    private Long id;
    @ColumnInfo(name = "_id")
    private Long  id;              //ID помещения

    @ColumnInfo(name = "FlatNumber")
    private int  FlatNumber;         // номер помещения

    @ColumnInfo(name = "FullCost")
    private Long  FullCost;              //Полная цена

    @ColumnInfo(name = "Address")
    private String Address;        //Адрес

    @ColumnInfo(name = "Floor")
    private int Floor;              //Этаж

    @ColumnInfo(name = "Section")
    private int  Section;              //Подъезд

    @ColumnInfo(name = "Corpus")
    private String  Corpus;            //Наименование (название) дома (Корпус)

    @ColumnInfo(name = "Square")     //Площадь
    private Float Square;

    @ColumnInfo(name = "CostForMetr")
    private int  CostForMetr;          //Цена за метр

    @ColumnInfo(name = "Rooms")
    private int Rooms;                  //Количество комнат

    @ColumnInfo(name = "FinishTypeId")
    private String  FinishTypeId;           //Код планировки

    @ColumnInfo(name = "StatusBuildings")
    private String  StatusBuildings;         //Статус (стадия) строительства

    @ColumnInfo(name = "LiveSquare")
    private Float  LiveSquare;              //Жилай площадь

    @ColumnInfo(name = "KitchenSquare")
    private Float  KitchenSquare;              //Площадь кухни

    @ColumnInfo(name = "CountSquare")
    private Float  CountSquare;               //Расчетная площадь

    @ColumnInfo(name = "Window")
    private String  Window;       //Куда уходят окна

    @ColumnInfo(name = "CountBalcony")
    private int CountBalcony;           //Количяество балконов

    @ColumnInfo(name = "CountLoggia")
    private int CountLoggia;            //Количество лоджий

    @ColumnInfo(name = "Studia")
    private String Studia;           //Студия

    @ColumnInfo(name = "Kladovaya")
    private int Kladovaya;      //Количество кладовых

    @ColumnInfo(name = "Bedroom")
    private int Bedroom;      //Количество спален

    @ColumnInfo(name = "Status")
    private String Status;      //Статус


    public Flat(){
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public int getFlatNumber() {
        return FlatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        FlatNumber = flatNumber;
    }

    public Long getFullCost() {
        return FullCost;
    }

    public void setFullCost(Long fullCost) {
        FullCost = fullCost;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int floor) {
        Floor = floor;
    }

    public int getSection() {
        return Section;
    }

    public void setSection(int section) {
        Section = section;
    }

    public String getCorpus() {
        return Corpus;
    }

    public void setCorpus(String corpus) {
        Corpus = corpus;
    }

    public Float getSquare() {
        return Square;
    }

    public void setSquare(Float square) {
        Square = square;
    }

    public int getCostForMetr() {
        return CostForMetr;
    }

    public void setCostForMetr(int costForMetr) {
        CostForMetr = costForMetr;
    }

    public int getRooms() {
        return Rooms;
    }

    public void setRooms(int rooms) {
        Rooms = rooms;
    }

    public String getFinishTypeId() {
        return FinishTypeId;
    }

    public void setFinishTypeId(String finishTypeId) {
        FinishTypeId = finishTypeId;
    }

    public String getStatusBuildings() {
        return StatusBuildings;
    }

    public void setStatusBuildings(String statusBuildings) {
        StatusBuildings = statusBuildings;
    }

    public Float getLiveSquare() {
        return LiveSquare;
    }

    public void setLiveSquare(Float liveSquare) {
        LiveSquare = liveSquare;
    }

    public Float getKitchenSquare() {
        return KitchenSquare;
    }

    public void setKitchenSquare(Float kitchenSquare) {
        KitchenSquare = kitchenSquare;
    }

    public Float getCountSquare() {
        return CountSquare;
    }

    public void setCountSquare(Float countSquare) {
        CountSquare = countSquare;
    }

    public String getWindow() {
        return Window;
    }

    public void setWindow(String window) {
        Window = window;
    }

    public int getCountBalcony() {
        return CountBalcony;
    }

    public void setCountBalcony(int countBalcony) {
        CountBalcony = countBalcony;
    }

    public int getCountLoggia() {
        return CountLoggia;
    }

    public void setCountLoggia(int countLoggia) {
        CountLoggia = countLoggia;
    }

    public String getStudia() {
        return Studia;
    }

    public void setStudia(String studia) {
        Studia = studia;
    }

    public int getKladovaya() {
        return Kladovaya;
    }

    public void setKladovaya(int kladovaya) {
        Kladovaya = kladovaya;
    }

    public int getBedroom() {
        return Bedroom;
    }

    public void setBedroom(int bedroom) {
        Bedroom = bedroom;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    //    @Override
//    public String toString() {
//                return
//                "LayoutUrl=" + LayoutUrl + "&" +
//                "AddressId=" + AddressId + "&" +
//                "SectionNumber=" + SectionNumber + "&" +
//                "Floor=" + Floor + "&" +
//                "Rooms=" + Rooms + "&" +
//                "Quantity=" + Quantity + "&" +
//                "DiscountMax=" + DiscountMax + "&" +
//                "FinishTypeId=" + FinishTypeId + "&" +
//                "TownHouse=" + TownHouse + "&" +
//                "PentHouse=" + PentHouse + "&" +
//                "TwoLevel=" + TwoLevel + "&" +
//                "SeparateEntrance=" + SeparateEntrance + "&" +
//                "WithWindow=" + WithWindow + "&" +
//                "FirePlace=" + FirePlace + "&" +
//                "Terrace=" + Terrace + "&" +
//                "CountBalcony=" + CountBalcony + "&" +
//                "CountLoggia=" + CountLoggia + "&" +
//                "CountTerrace=" + CountTerrace;
//    }


    @Override
    public String toString() {
        return  "FlatNumber=" + FlatNumber + "&" +
                "FullCost=" + FullCost + "&" +
                "Floor=" + Floor + "&" +
                "Section=" + Section + "&" +
                "Corpus=" + Corpus + "&" +
                "Square=" + Square + "&" +
                "CostForMetr=" + CostForMetr + "&" +
                "Rooms=" + Rooms + "&" +
                "LiveSquare=" + LiveSquare + "&" +
                "KitchenSquare=" + KitchenSquare + "&" +
                "CountSquare=" + CountSquare + "&" +
                "CountBalcony=" + CountBalcony + "&" +
                "CountLoggia=" + CountLoggia + "&" +
                "Kladovaya=" + Kladovaya + "&" +
                "Bedroom=" + Bedroom ;
    }
}
