package com.example.daggerexample.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.example.daggerexample.Adapters.RecyclerViewAdapterClass;
import com.example.daggerexample.Interfaces.ApiCall;
import com.example.daggerexample.Models.HerosModelClass;
import com.example.daggerexample.MyApplication.MyApplicationClass;
import com.example.daggerexample.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //injecting retrofit
    @Inject Retrofit retrofit;
    RecyclerView recyclerView;
    CarouselLayoutManager layoutManager;
    RecyclerViewAdapterClass recyclerViewAdapterClass;
    ArrayList<HerosModelClass> modelClassArrayList;
    HerosModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVeiw();

        recyclerView = findViewById(R.id.recyclerView);
        modelClassArrayList = new ArrayList<>();

        getHerosList();
    }

    private void initVeiw(){

        ((MyApplicationClass) getApplication()).getNetComponent().inject(this);

        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);

    }

    private void getHerosList(){

        ApiCall api = retrofit.create(ApiCall.class);
        Call<List<HerosModelClass>> call = api.getHeroes();

        call.enqueue(new Callback<List<HerosModelClass>>() {
            @Override
            public void onResponse(Call<List<HerosModelClass>> call, Response<List<HerosModelClass>> response) {
                List<HerosModelClass> heroList = response.body();
                /*String[] heroes = new String[0];
                if (heroList != null) {
                    heroes = new String[heroList.size()];
                }

                if (heroList != null) {
                    for (int i = 0; i < heroList.size(); i++) {
                        heroes[i] = heroList.get(i).getName();
                        heroes[i] = heroList.get(i).getImageurl();
                        Log.d("MainActivity","Getting ArrayList "+heroes[i]);
                    }
                }*/

                for (int i = 0; i < heroList.size(); i++){
                    modelClass = new HerosModelClass();
                    modelClass.setName(heroList.get(i).getName());
                    Log.d("MainActivity", "Number of movies received: " + heroList.get(i).getName());
                    modelClass.setImageurl(heroList.get(i).getImageurl());
                    modelClassArrayList.add(modelClass);
                }

                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
                recyclerViewAdapterClass = new RecyclerViewAdapterClass(modelClassArrayList,modelClass,getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerViewAdapterClass);
            }

            @Override
            public void onFailure(Call<List<HerosModelClass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
