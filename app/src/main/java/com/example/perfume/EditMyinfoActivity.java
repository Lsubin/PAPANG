package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.User;
import com.example.perfume.main.my.MyFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMyinfoActivity extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_logout;
    Button btn_withdrawal;

    TextView edit_nickname;
    TextView edit_birth;
    TextView edit_gender;
    TextView edit_phone_num;
    TextView edit_adress;

    String email;
    String nickname;

    DataService dataService;
    DataApi dataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myinfo);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        btn_back = (ImageButton) findViewById(R.id.btn_back);

        edit_nickname = (TextView)findViewById(R.id.edit_nickname);
        edit_birth = (TextView)findViewById(R.id.edit_birth);
        edit_gender = (TextView)findViewById(R.id.edit_gender);
        edit_phone_num = (TextView)findViewById(R.id.edit_phone_num);
        edit_adress = (TextView)findViewById(R.id.edit_adress);

        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_withdrawal = (Button)findViewById(R.id.btn_withdrawal);

        checkLogin();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataApi.deleteUser(email).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Logout();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
    }


    private void Logout(){
        SharedPreferences pref = getSharedPreferences("Info", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Access", "Logout");
        editor.remove("Email");
        editor.remove("Nickname");
        editor.commit();
    }

    private void checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        email = sharedPreferences.getString("Email","");
        nickname = sharedPreferences.getString("Nickname","");
        edit_nickname.setText(nickname);
        dataApi.getUser(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                edit_phone_num.setText(user.getPhone());
                if(!TextUtils.isEmpty(user.getAddress())){
                    int index = user.getAddress().indexOf(" ");
                    String address =  user.getAddress().substring(index+1);
                    edit_adress.setText(address);
                }
                if(!TextUtils.isEmpty(String.valueOf(user.getBirth())))
                    edit_birth.setText(String.valueOf(user.getBirth()));
                if(!TextUtils.isEmpty(user.getGender()))
                    edit_gender.setText(user.getGender());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}