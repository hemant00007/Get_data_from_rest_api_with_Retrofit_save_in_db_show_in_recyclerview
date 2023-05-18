package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private List<Data> arrayList;
    private CustomRecyclerview adapter;
    private ProgressBar pb;
   List<Data> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);
        recyclerview = findViewById(R.id.recyclerview);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        arrayList= new ArrayList<>();
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting() && arrayList != null) {
            call_restapi();
        } else {


     fetchfromRoom();
        }


    }

    private void fetchfromRoom() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                List<Recipe> recipeList = DatabaseClient.getInstance(MainActivity.this).getAppDatabase().recipeDao().getAll();
                arrayList.clear();
                for (Recipe recipe: recipeList) {
                    Data repo = new Data(recipe.getId(),
                            recipe.getName(),
                            recipe.getYear(),
                            recipe.getColor(),
                            recipe.getPantone());
                    arrayList.add(repo);
                }

                // refreshing recycler view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // adapter.notifyDataSetChanged();
                        recyclerview.setAdapter(new CustomRecyclerview(MainActivity.this,arrayList));
                    }
                });
            }
        });
        thread.start();
    }

    private void call_restapi() {
        Restinteface restinteface =Retrofit_base.getCacheEnabledRetrofit(this).create(Restinteface.class);
        Call<MyModel> call = restinteface.savedata();
        call.enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                arrayList=response.body().getData();
               recipes=response.body().getData();
                recyclerview.setAdapter(new CustomRecyclerview(MainActivity.this,arrayList));

                saveTask();

            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void saveTask() {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //delete previous data before saving api response
                 DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().recipeDao().deleteTable();
                for (int i = 0; i < recipes.size(); i++) {
                    Recipe recipe= new  Recipe();
                    recipe.setName(recipes.get(i).getName());
                    recipe.setYear(recipes.get(i).getYear());
                    recipe.setColor(recipes.get(i).getColor());
                    recipe.setPantone(recipes.get(i).getPantoneValue());
                    recipe.setPantone(recipes.get(i).getPantoneValue());

                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().recipeDao().insert(recipe);
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

}