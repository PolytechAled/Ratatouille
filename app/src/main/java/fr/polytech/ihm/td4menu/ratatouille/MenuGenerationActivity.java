package fr.polytech.ihm.td4menu.ratatouille;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MenuGenerationActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private WithoutMenuFragment withoutMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_generation);

        withoutMenuFragment = new WithoutMenuFragment();
        this.fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_menu_generation, withoutMenuFragment);
        this.fragmentTransaction.commit();
    }
}