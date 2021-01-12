package com.example.soldat.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soldat.R;
import com.example.soldat.business.AccessBodyType;
import com.example.soldat.business.AccessModifications;
import com.example.soldat.business.AccessSkill;
import com.example.soldat.enums.buildStage;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.PC;

import java.util.ArrayList;

public class CharacterCreationActivity extends AppCompatActivity {
    private PC currCharacter;
    private buildStage currStage;
    private ArrayList<Aspects> currOptions;
    private TableLayout aspectTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);
        currCharacter = new PC();
        currStage = buildStage.BODY;
        currOptions = new ArrayList<>();
        aspectTable = findViewById(R.id.aspect_selection);
        updatePoints();
        setupCharacterName();
        setupOptionsList();
    }

    private void setupCharacterName() {
        final EditText characterName = findViewById(R.id.input_Character_Name);
        characterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currCharacter.setCharacterName(characterName.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void updatePoints() {
        TextView currentPoints = findViewById(R.id.remaining_Points);
        String currentPointCount = "Remaining points:\n" + currCharacter.getRemainingCreationPoints();
        currentPoints.setText(currentPointCount);
    }

    private void createBodyList() {
        for(Aspects a : currOptions) {
            BodyType bType = (BodyType)a;
            if(bType.isPrimary()) {
                String name = bType.getName();
                String cost = "" + bType.getCost();
                String desc = bType.getDescription();
                final TableRow newRow = (TableRow) getLayoutInflater().inflate(R.layout.new_aspect_row, null);
                final TextView nameView = newRow.findViewById(R.id.aspect_name);
                final TextView costView = newRow.findViewById(R.id.aspect_cost);
                final ImageButton editButton = newRow.findViewById(R.id.aspect_info);
                nameView.setText(name);
                costView.setText(cost);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aspectDescription(name, desc);
                    }
                });
                aspectTable.addView(newRow);
            }
        }
    }

    private void createSkillList() {

    }

    private void createModList() {

    }

    private void aspectDescription(String title, String text) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CharacterCreationActivity.this);
        builder.setTitle(title);
        builder.setMessage(text);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void setupOptionsList() {
        TextView stage = findViewById(R.id.current_Step_Title);
        switch (currStage) {
            case BODY:
                stage.setText("Body Type");
                AccessBodyType bodyTransport = new AccessBodyType();
                bodyTransport.getBodyOptions(currOptions);
                for(Aspects a: currOptions) {
                    BodyType bType = (BodyType)a;
                    System.out.println(bType.getName());
                }
                createBodyList();
                break;
            case SKILL:
                stage.setText("Skill options");
                AccessSkill skillTransport = new AccessSkill();
                skillTransport.getSkillOptions(currOptions);
                createSkillList();
                break;
            case MOD:
                stage.setText("Benefits & Detriments");
                createModList();
                break;
        }
    }
}
