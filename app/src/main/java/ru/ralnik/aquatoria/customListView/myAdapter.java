package ru.ralnik.aquatoria.customListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import ru.ralnik.aquatoria.R;


public class myAdapter extends CursorAdapter {

    public Context context;
    private LayoutInflater mInflater;
    //private int size;

    public myAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        //this.size = c.getCount();
        this.context = context;
    }
    /*
    public int getSize(){
        return this.size;
    }*/

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View root = mInflater.from(context).inflate(R.layout.listview_item, parent, false);
        ViewHolder holder = new ViewHolder();

        ImageView imageBG = (ImageView) root.findViewById(R.id.imgBG);
        LinearLayout row = (LinearLayout) root.findViewById(R.id.row);
        TextView column1 = (TextView) root.findViewById(R.id.colKorpus);
        TextView column2 = (TextView) root.findViewById(R.id.colFlat);
        TextView column3 = (TextView) root.findViewById(R.id.colFloor);
        TextView column4 = (TextView) root.findViewById(R.id.colRooms);
        TextView column5 = (TextView) root.findViewById(R.id.colSquare);
        TextView column6 = (TextView) root.findViewById(R.id.colCost);
        TextView column7 = (TextView) root.findViewById(R.id.colBalcon);
        TextView column8 = (TextView) root.findViewById(R.id.colLoggia);
        TextView column9 = (TextView) root.findViewById(R.id.colKladovaya);


        /*
        int ID = cursor.getColumnIndex(myDBHelper.KEY__ID);
        int COST = cursor.getColumnIndex(myDBHelper.KEY_COST);
        int COSTF = cursor.getColumnIndex(myDBHelper.KEY_COSTF);
        int TYPE= cursor.getColumnIndex(myDBHelper.KEY_TYPE);
        int SQUARE= cursor.getColumnIndex(myDBHelper.KEY_SQUARE);
        int NUMBER= cursor.getColumnIndex(myDBHelper.KEY_NUMBER);
        int CORPUS= cursor.getColumnIndex(myDBHelper.KEY_BUILDING);
        int FLOOR= cursor.getColumnIndex(myDBHelper.KEY_FLOOR);
        */



        holder.imageBG = imageBG;
        holder.column1 = column1;
        holder.column2 = column2;
        holder.column3 = column3;
        holder.column4 = column4;
        holder.column5 = column5;
        holder.column6 = column6;
        holder.column7 = column7;
        holder.column8 = column8;
        holder.column9 = column9;
        holder.selected = row;

        root.setTag(holder);
        return root;


        //return LayoutInflater.from(context).inflate(R.layout.table_layout, parent, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        int ID = cursor.getColumnIndex("_id");
        int KORPUS = cursor.getColumnIndex("Corpus");
        int FLAT = cursor.getColumnIndex("FlatNumber");
        int SECTION = cursor.getColumnIndex("Section");
        int FLOOR = cursor.getColumnIndex("Floor");
        int ROOMS= cursor.getColumnIndex("Rooms");
        int SQUARE= cursor.getColumnIndex("Square");
        int COST= cursor.getColumnIndex("FullCost");
        int Balcon= cursor.getColumnIndex("CountBalcony");
        int Loggia= cursor.getColumnIndex("CountLoggia");
        int Kladovaya= cursor.getColumnIndex("Kladovaya");




        ViewHolder holder = (ViewHolder) view.getTag();
        //Log.d("myDebug","getTag: "+view.getTag());


        if(holder != null) {


            holder.position = cursor.getPosition();
            holder.column1.setText(cursor.getString(KORPUS) + "/" + cursor.getString(SECTION));
            holder.column2.setText(cursor.getString(FLAT));
            holder.column3.setText(getCorrectType(cursor.getInt(FLOOR)));
            holder.column4.setText(String.valueOf(cursor.getInt(ROOMS)));
            holder.column5.setText(String.valueOf(cursor.getFloat(SQUARE)));
            holder.column6.setText(String.valueOf(makePrettyCost(cursor.getString(COST))));
            holder.column7.setText(cursor.getString(Balcon));
            holder.column8.setText(cursor.getString(Loggia));
            holder.column9.setText(cursor.getString(Kladovaya));


            holder.classID = cursor.getInt(ID);
            holder.id = cursor.getString(ID);

            //Log.d("myDebug","id_fromTABLE="+cursor.getLong(ID)+" number="+cursor.getInt(NUMBER)+" corpus="+cursor.getString(CORPUS)+" cost="+makePrettyCost(String.valueOf(cursor.getInt(COST))));

            if(holder.position %2 == 0){
                holder.imageBG.setImageLevel(1);
            }else{
                holder.imageBG.setImageLevel(0);
            }



            holder.column2.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column3.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column4.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column5.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column6.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column7.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column8.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            holder.column9.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            /*
                Проверяем если запись выделенная то при прокрутки текст так и останется темным
                если проверку не делать, то текст при прокрутки опять меняется на белый
            */
        if(listviewItemSelected.ID != 0) {
            if (listviewItemSelected.ID == holder.classID) {

                holder.column1.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column2.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column3.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column4.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column5.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column6.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column7.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column8.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
                holder.column9.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
            }
        }

        }
    }

    private String makePrettyCost(String cost){
        long realcost=0;
        realcost = Math.round(Double.valueOf(cost));

        String tempCost =  String.valueOf(realcost);
        String pattern = null;
        switch (tempCost.length()){
            case 6:
                pattern = "###,###";
                break;
            case 7:
                pattern = "#,###,###";
                break;
            case 8:
                pattern = "##,###,###";
                break;
            case 9:
                pattern = "###,###,###";
                break;
            case 10:
                pattern = "#,###,###,###";
                break;
            default:
                pattern = "###";
                break;
        }
        DecimalFormat mf = new DecimalFormat(pattern);
        tempCost = mf.format(realcost);
        return tempCost.replace(","," ");
    }

    private String getCorrectType(int type){
        switch (type){
            case 100:
                return "студия";

            case 200:
                return "офис";

            case 300:
                return "паркинг";

            default:
                return type+"";
        }
    }

    public static class ViewHolder {

        public int position;
        public ImageView imageBG;
        public TextView column1;
        public TextView column2;
        public TextView column3;
        public TextView column4;
        public TextView column5;
        public TextView column6;
        public TextView column7;
        public TextView column8;
        public TextView column9;
        public LinearLayout selected ;

        public long classID;
        public String id;
    }
}
