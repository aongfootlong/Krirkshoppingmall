package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.R;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ServiceActivity;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.utility.GetAllDataByUrl;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.utility.MyAlert;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.utility.MyConstant;

/**
 * Created by Pattrawut on 3/6/2018.
 */

public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();


    }   // End Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else {
//                    No Space
                    try {

                        MyConstant myConstant = new MyConstant();
                        boolean statusAboolean = true;
                        GetAllDataByUrl getAllDataByUrl = new GetAllDataByUrl(getActivity());
                        getAllDataByUrl.execute(myConstant.getURLGetAllUserString());

                        String jsonString = getAllDataByUrl.get();
                        Log.e("27marcsv1", "Json==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);

                        String[] ColumnString = myConstant.getColumnUser();
                        String[] LoginString = new String[ColumnString.length];

                        for (int i=0; i<jsonArray.length();i+=1) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString(ColumnString[2]))) {

                                statusAboolean = false;
                                for (int i1=0;i1<ColumnString.length; i1+=1) {
                                    LoginString[i1] = jsonObject.getString(ColumnString[i1]);
                                }
                            }
                        }

                        if (statusAboolean) {
//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False", "No This User In My Database");

                        } else if (passwordString.equals(LoginString[3])) {
//                            Pass True
                            Toast.makeText(getActivity(), "Welcome" + LoginString[1],
                                    Toast.LENGTH_SHORT).show();

//                            Move to Service Activity
                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("Login", LoginString);
                            startActivity(intent);
                            getActivity().finish();

                        } else {
//                            Pass False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False","Please Try Again");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }   // If

            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }
}
