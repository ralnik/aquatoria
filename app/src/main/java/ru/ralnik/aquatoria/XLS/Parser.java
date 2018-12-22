package ru.ralnik.aquatoria.XLS;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import ru.ralnik.aquatoria.GlobalVars;
import ru.ralnik.aquatoria.model.Flat;
import ru.ralnik.aquatoria.sqlitedb.FlatRepository;

public class Parser extends Thread implements Runnable {
    Context context;
    public Parser(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        super.run();
        try {
            //AssetManager am = getAssets();
            //InputStream is = am.open("test.xls");
            File file = new File(GlobalVars.XLS_FILE);
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            //int cols = s.getColumns();

            //Log.d("myDebug","countRow="+row);

            for(int i = 1; i < row-1; i++){
                Flat flat = new Flat();


            //    Log.d("myDebug","**********************************************************************************");

          //  Log.d("myDebug","id="+s.getCell(0,i).getContents());
                flat.setId(Long.valueOf(s.getCell(0,i).getContents()));
                if(!s.getCell(1,i).getContents().equals("")) {
            //        Log.d("myDebug","number="+s.getCell(1,i).getContents());
                    flat.setFlatNumber(Integer.valueOf(s.getCell(1, i).getContents()));
                }

                if(!s.getCell(2,i).getContents().equals("")) {
                String fullCost = s.getCell(2,i).getContents().replaceAll("\\s+","");
               //     Log.d("myDebug","="+fullCost);
                    flat.setFullCost(Long.valueOf(fullCost));
                }
              ///////  Log.d("myDebug","Address="+s.getCell(7,i).getContents());
                flat.setAddress(s.getCell(7,i).getContents());
                if(!s.getCell(8,i).getContents().equals("")) {
              ///      Log.d("myDebug","Floor="+s.getCell(8,i).getContents());
                    flat.setFloor(Integer.valueOf(s.getCell(8, i).getContents()));
                }
                if(!s.getCell(9,i).getContents().equals("")) {
             //       Log.d("myDebug","Section="+s.getCell(9,i).getContents());
                    flat.setSection(Integer.valueOf(s.getCell(9, i).getContents()));
                }
             //   Log.d("myDebug","Corpus="+s.getCell(10,i).getContents());
                flat.setCorpus(s.getCell(10,i).getContents());
                if(!s.getCell(12,i).getContents().equals("")) {
             //       Log.d("myDebug","Square="+s.getCell(12,i).getContents());
                    flat.setSquare(Float.valueOf(s.getCell(12, i).getContents()));
                }
                if(!s.getCell(13,i).getContents().equals("")) {
                    String costForMetr = s.getCell(13,i).getContents().replaceAll("\\s+","");
            //        Log.d("myDebug","costForMetr="+costForMetr);
                    flat.setCostForMetr(Integer.valueOf(costForMetr));
                }
                if(!s.getCell(14,i).getContents().equals("")) {
            //        Log.d("myDebug","Rooms="+s.getCell(14,i).getContents());
                    flat.setRooms(Integer.valueOf(s.getCell(14, i).getContents()));
                }
            //    Log.d("myDebug","FinishTypeId="+s.getCell(16,i).getContents());
                flat.setFinishTypeId(s.getCell(16,i).getContents());
             //   Log.d("myDebug","StatusBuilding="+s.getCell(17,i).getContents());
                flat.setStatusBuildings(s.getCell(17,i).getContents());
                if(!s.getCell(18,i).getContents().equals("")) {
             //       Log.d("myDebug","LiveSquare="+s.getCell(18,i).getContents());
                    flat.setLiveSquare(Float.valueOf(s.getCell(18, i).getContents()));
                }
                if(!s.getCell(19,i).getContents().equals("")) {
             //       Log.d("myDebug","KithenSquare="+s.getCell(19,i).getContents());
                    flat.setKitchenSquare(Float.valueOf(s.getCell(19, i).getContents()));
                }
                if(!s.getCell(20,i).getContents().equals("")) {
             //       Log.d("myDebug","CountSquare="+s.getCell(20,i).getContents());
                    flat.setCountSquare(Float.valueOf(s.getCell(20, i).getContents()));
                }
            //    Log.d("myDebug","Window="+s.getCell(21,i).getContents());
                flat.setWindow(s.getCell(21,i).getContents());
                if(!s.getCell(22,i).getContents().equals("")) {
            //        Log.d("myDebug","Loggia="+s.getCell(22,i).getContents());
                    flat.setCountLoggia(Integer.valueOf(s.getCell(22, i).getContents()));
                }
                if(!s.getCell(23,i).getContents().equals("")) {
             //       Log.d("myDebug","Balcony="+s.getCell(23,i).getContents());
                    flat.setCountBalcony(Integer.valueOf(s.getCell(23, i).getContents()));
                }
             //   Log.d("myDebug","studia="+s.getCell(26,i).getContents());
                flat.setStudia(s.getCell(26,i).getContents());
                if(!s.getCell(29,i).getContents().equals("")) {
             //       Log.d("myDebug","kladovaya="+s.getCell(29,i).getContents());
                    flat.setKladovaya(Integer.valueOf(s.getCell(29, i).getContents()));
                }
                if(!s.getCell(30,i).getContents().equals("")) {
            //        Log.d("myDebug","Bedroom="+s.getCell(30,i).getContents());
                    flat.setBedroom(Integer.valueOf(s.getCell(30, i).getContents()));
                }
            //    Log.d("myDebug","Status="+s.getCell(35,i).getContents());
                flat.setStatus(s.getCell(35,i).getContents());

                //будет вставленa новая запись, если запись уже есть, то тогда она обновится полностью по всем полям
                new FlatRepository(this.context).insert(flat);

                //Log.d("myDebug","id: "+ z.getContents());

            }


        } catch (Exception e) {
            Log.d("myDebug","error_parser: " + e.toString());
        }
    }
}
