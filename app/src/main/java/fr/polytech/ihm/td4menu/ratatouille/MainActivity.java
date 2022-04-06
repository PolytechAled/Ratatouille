package fr.polytech.ihm.td4menu.ratatouille;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        RecipeAdapter adapter = new RecipeAdapter();
        RecyclerView recyclerView = findViewById(R.id.recipeList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
 */
        Intent intent = new Intent(getApplicationContext(), Home.class);
        //intent.putExtra("mul",val);
        startActivity(intent);
    }
}