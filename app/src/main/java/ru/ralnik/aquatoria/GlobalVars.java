package ru.ralnik.aquatoria;

import android.os.Environment;
import android.webkit.WebView;

public class GlobalVars {

    public static WebView webView;
    public static String XLS_URL = "http://pb2192.profitbase.ru/export/excel_table/209589480cad4545e4f5541f7249a427";
    public static String XLS_FILE = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/ru.ralnik.aquatoria/tmp.xls";

}
