package ru.ralnik.aquatoria.customListView;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import ru.ralnik.aquatoria.R;
import ru.ralnik.aquatoria.httpPlayer.HttpPlayerFactory;
import ru.ralnik.aquatoria.model.Flat;
import ru.ralnik.aquatoria.sqlitedb.FlatRepository;

public class listviewItemSelected implements AdapterView.OnItemClickListener {

    private String _ID = null;
    public static myAdapter.ViewHolder SELECTED_LISTVIEW_ITEM ; //предыдущий выделенный элемент
    public static long ID = 0;

    Context context;
//    vvvv VVVV;

    public listviewItemSelected(Context context) {
        this.context = context;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //текущий выделенный элемент
        myAdapter.ViewHolder currentViewHolder = (myAdapter.ViewHolder) view.getTag();




        //если предедущего элемента еще нет, то присваеваем ему текущий,
        //иначе с предыдущего снимаем выделение
        if(ID != 0) {

            SELECTED_LISTVIEW_ITEM.column1.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column2.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column3.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column4.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column5.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column6.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column7.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column8.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column9.setTextColor(context.getResources().getColor(R.color.colorTextListitems));

        }


            SELECTED_LISTVIEW_ITEM = currentViewHolder;
            ID = currentViewHolder.classID;
            _ID = currentViewHolder.id;
            //на текущем ставим новое выделение
            //String fname = "pic_"+GlobalVar.razdelFilter.toLowerCase() + currentViewHolder.column2.getText() + "_" + currentViewHolder.column4.getText();
            //currentViewHolder.column1.setImageResource(context.getResources().getIdentifier(fname + "_p", "drawable", context.getPackageName()));



        currentViewHolder.column1.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column2.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column3.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column4.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column5.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column6.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column7.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column8.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
        currentViewHolder.column9.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));

        //Если при выделинии строки по четным один цвет шрифта, по нечетным другой
//            if(currentViewHolder.position %2 == 0){
//                currentViewHolder.column1.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column2.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column3.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column4.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column5.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column6.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//                currentViewHolder.column7.setTextColor(context.getResources().getColor(R.color.colorTableItemSelected));
//            }else{
//                currentViewHolder.column1.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column2.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column3.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column4.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column5.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column6.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//                currentViewHolder.column7.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
//
//            }



        //выводим данные на сервер vvvv
//        myDBHelper tempDB = new myDBHelper(context);
//        tempDB.openDB();
//        Buildings rowById = tempDB.getById(id);
//        GlobalVar.razdel = "7";
//        GlobalVar.number = String.valueOf(rowById.getNumber());
//        GlobalVar.countRooms = String.valueOf(rowById.getCountRooms());
//        GlobalVar.square = String.valueOf(rowById.getSquare());
//        GlobalVar.floor = String.valueOf(rowById.getFloor());
//        GlobalVar.korpus = String.valueOf(rowById.getBuilding());
//        GlobalVar.cost = String.valueOf(rowById.getCost());
//        GlobalVar.section = String.valueOf(rowById.getSection());
//        GlobalVar.roomS = String.valueOf(rowById.getRoomS());
//        GlobalVar.kitchenS = String.valueOf(rowById.getKitchenS());
//        GlobalVar.balconyS = String.valueOf(rowById.getBalconyS());
//        GlobalVar.costf = String.valueOf(rowById.getCostf());
//        tempDB.closeDB();
//
//        //playPause = "1";
//        GlobalVar.lastLink = VVVV.fullLink();
//        Log.d("myDebug", GlobalVar.lastLink);
        // Log.d(TAG,"position: "+position+" id: "+id+" number: "+viewHolder.column4.getText());


        Flat flat = new FlatRepository(context).findById(_ID);
        HttpPlayerFactory.getInstance(context).getCommand().setFlatInfo(flat);
        HttpPlayerFactory.getInstance(context).getCommand().selectById(8);



    }
}
