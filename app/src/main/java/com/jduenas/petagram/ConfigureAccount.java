package com.jduenas.petagram;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jduenas.petagram.restApi.ConstantesRestApi;
import com.jduenas.petagram.restApi.EndpointsApi;
import com.jduenas.petagram.restApi.adapter.RestApiAdapter;
import com.jduenas.petagram.pojo.User;
import com.jduenas.petagram.restApi.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigureAccount extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btSaveAccount;
    private EditText etAccount;
    private ArrayList<User> userAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_account);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }
        btSaveAccount   = (Button) findViewById(R.id.btSaveAccount);
        btSaveAccount.setOnClickListener(this);
        etAccount       = (EditText) findViewById(R.id.etAccount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btSaveAccount:
                saveAccount();
                break;
        }
    }

    private void saveAccount() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUserSearched = restApiAdapter.construyeGsonDeserializadorDataUserSearched();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUserSearched);
        userAccount = new ArrayList<>();
        Call<UserResponse> userResponseCall = endpointsApi.getUsersSearch(etAccount.getText().toString(), ConstantesRestApi.ACCESS_TOKEN);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                userAccount = userResponse.getUsers();
                if (userAccount.size()>0){
                    SharedPreferences sharedPref = ConfigureAccount.this.getPreferences(ConfigureAccount.this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.account_name), userAccount.get(0).getUsername());
                    editor.putString(getString(R.string.account_id), userAccount.get(0).getId());
                    editor.putString(getString(R.string.profile_picture), userAccount.get(0).getProfile_picture());
                    editor.commit();
                    Toast.makeText(ConfigureAccount.this,"Cuenta Guardada",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ConfigureAccount.this,"No existe un usuario con ese nombre",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(ConfigureAccount.this,"Error en la conexion. Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.i("Error getRecentMedia","FALLO LA CONEXION: "+t.toString());
            }
        });

    }
}
