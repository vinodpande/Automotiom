package com.vindroidtech.activitites;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vindroidtech.automotiom.Constant.AppConstant;
import com.vindroidtech.automotiom.R;

public class ConfigSettingActivity extends AppCompatActivity {

    EditText editTextIpAddress,editTextPort;
    Button buttonConfigSave,buttonConfigCancel;
    String IpAddress;
    String port;

    void inItUI(){
        editTextIpAddress=findViewById(R.id.ediTextConfigIPAddress);
        editTextPort=findViewById(R.id.ediTextConfigPort);
        buttonConfigSave=findViewById(R.id.buttonConfigSave);
        buttonConfigCancel=findViewById(R.id.buttonConfigCancel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_setting);
        inItUI();

        AppConstant.sharedPreferences=getSharedPreferences(AppConstant.sharedPreferencesName, Context.MODE_PRIVATE);
        if(AppConstant.sharedPreferences.getString(AppConstant.KEY_IP_ADDRESS,null)!=null){
            editTextIpAddress.setText(AppConstant.sharedPreferences.getString(AppConstant.KEY_IP_ADDRESS,null));
        }
        if(AppConstant.sharedPreferences.getString(AppConstant.KEY_PORT,null)!=null){
            editTextPort.setText(AppConstant.sharedPreferences.getString(AppConstant.KEY_PORT,null));
        }
    }

    void setValueToSharedPreferences(){
        IpAddress=editTextIpAddress.getText().toString();
        port=editTextPort.getText().toString();
        if(TextUtils.isEmpty(editTextIpAddress.getText()) && TextUtils.isEmpty(editTextPort.getText())){
            Toast.makeText(this,"Enter Valid IP Address and Port",Toast.LENGTH_LONG).show();
        }
        else {
            SharedPreferences.Editor editor = AppConstant.sharedPreferences.edit();
            editor.putString(AppConstant.KEY_IP_ADDRESS,IpAddress);
            editor.putString(AppConstant.KEY_PORT,port);
            editor.commit();
            finish();
        }
    }

    void clearUI(){
        editTextIpAddress.setText(null);
        editTextPort.setText(null);
    }
    public void onButtonClicked(View view){
        int id=view.getId();
        switch (id){
            case R.id.buttonConfigSave:
                Toast.makeText(this,"Save Clicked",Toast.LENGTH_LONG).show();
                setValueToSharedPreferences();
                break;
            case R.id.buttonConfigCancel:
                clearUI();
                Toast.makeText(this,"Cancel Clicked",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
