package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.utility;

import java.net.URI;

/**
 * Created by Pattrawut on 3/27/2018.
 */

public class MyConstant {

//    About URL

    private String URLAddUserString = "http://androidthai.in.th/kir/addDataUng.php";
    private String URLGetAllUserString = "http://androidthai.in.th/kir/getAllUserAong.php";

    //    Array
    private String[] ColumnUser = new String[]{"id", "Name", "User", "Password", "Mode"};

    public  String[] getColumnUser() {
        return ColumnUser;
    }

    public String getURLGetAllUserString() {
        return getURLAddUserString();
    }

    public String getURLAddUserString() {
        return URLAddUserString;
    }

}   // Main Class
