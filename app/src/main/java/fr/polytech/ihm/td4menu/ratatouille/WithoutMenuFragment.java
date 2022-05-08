package fr.polytech.ihm.td4menu.ratatouille;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class withoutMenu extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_without_menu, container, false);

        Spinner spinnerOrigin = view.findViewById(R.id.spinnerOrigin);
        Spinner spinnerRegime = view.findViewById(R.id.spinnerRegime);

        ArrayAdapter<CharSequence> adapterOrigin = ArrayAdapter.createFromResource(getContext(),R.array.origine, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRegime = ArrayAdapter.createFromResource(getContext(),R.array.regime, android.R.layout.simple_spinner_item);

        adapterOrigin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapterOrigin);

        adapterRegime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegime.setAdapter(adapterRegime);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }
}