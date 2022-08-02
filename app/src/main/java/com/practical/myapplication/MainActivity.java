package com.practical.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.practical.myapplication.adapters.DataListAdapter;
import com.practical.myapplication.apis.APIServices;
import com.practical.myapplication.apis.RetrofitInstance;
import com.practical.myapplication.models.DataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    DataListAdapter dataListAdapter;
    APIServices apiServices;
    RecyclerView recyclerView;
    List<DataModel> listdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView.setHasFixedSize(false);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        listdata = new ArrayList<>();

        loadRecyceldata();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void loadRecyceldata() {

        apiServices = RetrofitInstance.getRetrofit().create(APIServices.class);
        apiServices.getDataList().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                if(response.isSuccessful()) {
                    listdata = response.body();

                    recyclerView.setAdapter(new DataListAdapter(MainActivity.this, listdata));
                }else {
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void id_down(MenuItem item) {
        Toast.makeText(this, R.string.id_down, Toast.LENGTH_SHORT).show();
        Collections.sort(listdata, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel dataModel, DataModel t1) {
                return t1.getId().compareToIgnoreCase(dataModel.getId());

            }

        });
        recyclerView.setAdapter(new DataListAdapter(MainActivity.this,listdata));
    }

    public void title_up(MenuItem item) {
        Toast.makeText(this, R.string.title_up, Toast.LENGTH_SHORT).show();
        Collections.sort(listdata, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel dataModel, DataModel t1) {
                return dataModel.getTitle().compareToIgnoreCase(t1.getTitle());

            }

        });
        recyclerView.setAdapter(new DataListAdapter(MainActivity.this,listdata));


    }

    public void title_down(MenuItem item) {
        Toast.makeText(this, R.string.title_down, Toast.LENGTH_SHORT).show();

        Collections.sort(listdata, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel dataModel, DataModel t1) {
                return t1.getTitle().compareToIgnoreCase(dataModel.getTitle());

            }

        });
        recyclerView.setAdapter(new DataListAdapter(MainActivity.this,listdata));

    }
}