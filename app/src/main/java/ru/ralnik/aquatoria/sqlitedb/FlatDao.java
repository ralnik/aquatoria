package ru.ralnik.aquatoria.sqlitedb;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.database.Cursor;

import java.util.List;

import ru.ralnik.aquatoria.model.Flat;

@Dao
public interface FlatDao {
    @RawQuery
   Cursor getFlatsByQuery(SupportSQLiteQuery query);

    @Query("SELECT * FROM flats")
    List<Flat> getAll();

    @Query("SELECT * FROM flats")
    Cursor getFlats();

    @Query("SELECT * FROM flats WHERE _id IN (:id)")
    List<Flat> FindByIds(String[] id);

    @Query("SELECT * FROM flats WHERE _id = :id")
    Flat findById(String id);

//   В режиме REPLACE старая запись будет заменена новой. Этот режим хорошо подходит, если вам надо вставить запись, если ее еще нет в таблице или обновить запись, если она уже есть.
//   Также есть режим IGNORE. В этом режиме будет оставлена старая запись и операция вставки не будет выполнена.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Flat... flats);

    @Delete
    void delete(Flat flat);

    @Query("DELETE FROM flats")
    void deleteAll();

    //**************Floor
    @Query("SELECT min(Floor) FROM flats")
    int getMinFloor();

    @Query("SELECT max(Floor) FROM flats")
    int getMaxFloor();

    //**************SQUARE
    @Query("SELECT min(Square) FROM flats")
    Float getMinSquare();

    @Query("SELECT max(Square) FROM flats")
    Float getMaxSquare();

    //**************COST
    @Query("SELECT min(CostForMetr) FROM flats")
    Float getMinCost();

    @Query("SELECT max(CostForMetr) FROM flats")
    Float getMaxCost();

    //**************Budget
    @Query("SELECT min(FullCost) FROM flats")
    Float getMinBudget();

    @Query("SELECT max(FullCost) FROM flats")
    Float getMaxBudget();

    @Query("UPDATE flats set _id = :id, FlatNumber = :FlatNumber, FullCost = :FullCost, Address = :Address, Floor = :Floor, Section = :Section, Corpus = :Corpus, Square = :Square, CostForMetr = :CostForMetr, Rooms = :Rooms, FinishTypeId = :FinishTypeId, StatusBuildings = :StatusBuildings,LiveSquare = :LiveSquare, KitchenSquare = :KitchenSquare, CountSquare = :CountSquare, Window = :Window, CountBalcony = :CountBalcony, CountLoggia = :CountLoggia, Studia = :Studia, Kladovaya = :Kladovaya, Bedroom = :Bedroom, Status = :Status")
    void update(Long id, int FlatNumber, Long FullCost, String Address, int Floor, int Section, String Corpus, Float Square, int CostForMetr, int Rooms, String FinishTypeId, String StatusBuildings, Float LiveSquare, Float KitchenSquare, Float CountSquare, String Window, int CountBalcony, int CountLoggia, String Studia, int Kladovaya, int Bedroom, String Status);
 }
