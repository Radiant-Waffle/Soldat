package com.example.soldat.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.soldat.R;
import com.example.soldat.application.Services;
import com.example.soldat.business.AccessPC;
import com.example.soldat.objects.BodyType;
import com.example.soldat.objects.PC;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<PC> characterList;
    private AccessPC aPC;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Services.createDatabase("soldatDB");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variable initialization
        characterList = new ArrayList<>();
        aPC = new AccessPC();

        //initializing views
        tableLayout = findViewById(R.id.character_selection_table);

        loadList();
    }

    @Override
    public void onClick(View v) {

    }

    private void loadList() {
        characterList = aPC.getPCList();
        for(PC person : characterList) {
            createNewRow(person.getCharacterName(), person.getBody());
        }
        final TableRow addButton = new TableRow(this);
        tableLayout.addView(addButton);
        Button newCharacter = new Button(this);
        newCharacter.setText("+     New Character");
        newCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCharacterCreationActivity();
            }
        });
        addButton.addView(newCharacter);
    }

    private void createNewRow(String name, BodyType body) {
        final TableRow newRow = (TableRow) getLayoutInflater().inflate(R.layout.new_character_row, null);
        final TextView nameView = newRow.findViewById(R.id.character_name);
        final TextView typeView = newRow.findViewById(R.id.character_Type);
        final ImageButton editButton = newRow.findViewById(R.id.edit_button);
        nameView.setText(name);
        typeView.setText(body.getName());
        /*editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        newRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        tableLayout.addView(newRow);
    }

    private void openCharacterCreationActivity() {
        Intent selectionActivity = new Intent(MainActivity.this, CharacterCreationActivity.class);
        startActivity(selectionActivity);
    }
}