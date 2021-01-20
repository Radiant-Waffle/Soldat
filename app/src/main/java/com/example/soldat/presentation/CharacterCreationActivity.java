package com.example.soldat.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soldat.R;
import com.example.soldat.business.AccessBodyType;
import com.example.soldat.business.AccessSkill;
import com.example.soldat.enums.buildStage;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.Aspects.Skill;
import com.example.soldat.objects.PlayerCharacter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharacterCreationActivity extends AppCompatActivity {
    private PlayerCharacter currCharacter;
    private buildStage currStage;
    private ArrayList<Aspects> currOptions;
    private TableLayout aspectTable;
    private ArrayList<TableRow> selectedRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_character_creation);
        currCharacter = new PlayerCharacter();
        currStage = buildStage.BODY;
        currOptions = new ArrayList<>();
        aspectTable = findViewById(R.id.aspect_table);
        selectedRow = new ArrayList<>();
        updatePoints();
        setupNavOptions();
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

    private void setupNavOptions() {
        Button backButton = findViewById(R.id.back_Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(currStage) {
                    case BODY:
                        currCharacter = null;
                        openMainActivity();
                        break;
                    case SKILL:
                        currStage = buildStage.BODY;
                        setupOptionsList();
                        break;
                    case MOD:
                        currStage = buildStage.SKILL;
                        setupOptionsList();
                        break;
                }
            }
        });
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(currStage) {
                    case BODY:
                        currStage = buildStage.SKILL;
                        TableLayout optionLayout = findViewById(R.id.bottom_sheet_options);
                        optionLayout.removeAllViews();
                        setupOptionsList();
                        break;
                    case SKILL:
                        currStage = buildStage.MOD;
                        setupOptionsList();
                        break;
                    case MOD:
                        //TODO: complete character
                        break;
                }
            }
        });
    }

    private void createBodyList() {
        for(Aspects a : currOptions) {
            BodyType bType = (BodyType)a;
            if(bType.isPrimary()) {
                String name = bType.getName();
                String cost = "" + bType.getCost();
                String desc = bType.getDescription();
                final TableRow newRow = (TableRow) getLayoutInflater().inflate(R.layout.new_aspect_row,null);
                selectedRow.add(newRow);
                TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                lp.height = 120;
                lp.setMargins(10,10,10,10);
                newRow.setLayoutParams(lp);
                final TextView nameView = newRow.findViewById(R.id.aspect_name);
                final TextView costView = newRow.findViewById(R.id.aspect_cost);
                final ImageButton infoButton = newRow.findViewById(R.id.aspect_info);
                nameView.setText(name);
                costView.setText(cost);
                infoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aspectDescription(name, desc);
                    }
                });
                newRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(TableRow curr : selectedRow) { curr.setSelected(false); }
                        newRow.setSelected(true);
                        if(currCharacter.getBody() != null) {
                            int refund = -currCharacter.getBody().getCost();
                            currCharacter.changeRemainingCreationPoints(refund);
                        }
                        clearSubList();
                        currCharacter.setBody(bType);
                        currCharacter.changeRemainingCreationPoints(bType.getCost());
                        updatePoints();
                        openSubList(bType);
                    }
                });
                aspectTable.addView(newRow, lp);
                if(currCharacter.getBody() != null) {
                    if (currCharacter.getBody().equals(bType)) {
                        newRow.setSelected(true);
                        openSubList(bType);
                    }
                }
            }
        }
    }

    private void clearSubList() {
        if(currCharacter.getBodyAugments() != null) {
            for (Aspects a : currCharacter.getBodyAugments()) {
                BodyType currType = (BodyType) a;
                if(currType.getMultiple() == 1) {
                    currCharacter.changeRemainingCreationPoints(-currType.getCost());
                } else {
                    currCharacter.changeRemainingCreationPoints(-currType.getMultipleSelected());
                }
            }
            currCharacter.clearBodyAugments();
        }
        TableLayout optionLayout = findViewById(R.id.bottom_sheet_options);
        optionLayout.removeAllViews();
    }

    private void openSubList(BodyType bType) {
        ArrayList<String> subTypes = bType.getSubOptions();
        ArrayList<BodyType> subOptions = new ArrayList<>();
        for(Aspects a : currOptions) {
            BodyType currType = (BodyType)a;
            String typeName = currType.getName();
            if(subTypes.contains(typeName)) {
                subOptions.add(currType);
            }
        }
        if(!subOptions.isEmpty()) {
            TableLayout optionLayout = findViewById(R.id.bottom_sheet_options);
            for (BodyType curr : subOptions) {
                buildSubOption(optionLayout, curr);
            }
            LinearLayout table = findViewById(R.id.bottom_options);
            if(table.getVisibility() != View.VISIBLE){
                table.setVisibility(View.VISIBLE);
            }

        }
    }

    private void clearOptionsList() {
        aspectTable.removeAllViews();
    }

    private void buildSubOption(TableLayout optionLayout, BodyType curr) {
        final TableRow newRow = (TableRow)getLayoutInflater().inflate(R.layout.sub_option_row,null);
        String name = curr.getName();
        String cost = "" + curr.getCost();
        int baseCost = curr.getCost();
        String desc = curr.getDescription();
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.height = 120;
        lp.setMargins(10,10,10,10);
        newRow.setLayoutParams(lp);
        final CheckBox checkView = newRow.findViewById(R.id.optionCheckBox);
        final TextView nameView = newRow.findViewById(R.id.sub_option_name);
        final TextView costView = newRow.findViewById(R.id.sub_option_cost);
        final ImageButton infoButton = newRow.findViewById(R.id.sub_option_info);
        final Spinner amount = new Spinner(this);
        nameView.setText(name);
        if(curr.getMultiple() == 1) {
            costView.setText(cost);
        } else {
            newRow.removeView(costView);
            final List<Integer> values = new ArrayList<>();
            for(int i = 1; i <= curr.getMultiple(); i++) { values.add(i * baseCost); }
            ArrayAdapter<Integer> val = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, values);
            val.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            amount.setAdapter(val);
            if(curr.getMultipleSelected() != 1) { amount.setSelection(curr.getMultipleSelected() / baseCost - 1); }
            amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(checkView.isChecked()) {
                        currCharacter.changeRemainingCreationPoints(-curr.getMultipleSelected());
                        int currCost = (int) amount.getSelectedItem();
                        curr.setMultipleSelected(currCost);
                        currCharacter.changeRemainingCreationPoints(currCost);
                        updatePoints();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            amount.setBackgroundResource(R.drawable.spinner_shape);
            newRow.addView(amount, 2);
        }
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aspectDescription(name, desc);
            }
        });
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currCost = baseCost;
                if(curr.getMultiple() != 1) {
                    currCost = (int)amount.getSelectedItem();
                    curr.setMultipleSelected(currCost);
                }
                if(checkView.isChecked()) {
                    currCharacter.addBodyAugments(curr);
                    currCharacter.changeRemainingCreationPoints(currCost);
                    updatePoints();
                }
                else {
                    currCharacter.removeBodyAugments(curr);
                    currCharacter.changeRemainingCreationPoints(-currCost);
                    updatePoints();
                }
            }
        });
        if(currCharacter.getBodyAugments().contains((Aspects)curr)) {
            checkView.setChecked(true);
        }
        optionLayout.addView(newRow);
    }

    private void createSkillList() {
        for(Aspects a : currOptions) {
            Skill currSkill = (Skill)a;
            String name = currSkill.getName();
            String cost = "" + currSkill.getCost();
            String desc = currSkill.getDescription();
            final TableRow newRow = (TableRow) getLayoutInflater().inflate(R.layout.new_aspect_row,null);
            selectedRow.add(newRow);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 120;
            lp.setMargins(10,10,10,10);
            newRow.setLayoutParams(lp);
            final TextView nameView = newRow.findViewById(R.id.aspect_name);
            final TextView costView = newRow.findViewById(R.id.aspect_cost);
            final ImageButton infoButton = newRow.findViewById(R.id.aspect_info);
            nameView.setText(name);
            costView.setText(cost);
            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    aspectDescription(name, desc);
                }
            });
            newRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newRow.isSelected()) {
                        currCharacter.removeSkill(currSkill);
                        newRow.setSelected(false);
                        int refund = -currSkill.getCost();
                        currCharacter.changeRemainingCreationPoints(refund);
                    } else {
                        currCharacter.addSkill(currSkill);
                        newRow.setSelected(true);
                        int refund = currSkill.getCost();
                        currCharacter.changeRemainingCreationPoints(refund);
                    }
                    updatePoints();
                }
            });
            if(currCharacter.getSkillList().contains((Aspects)currSkill)) { newRow.setSelected(true); }
            aspectTable.addView(newRow, lp);
        }
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
        clearOptionsList();
        switch (currStage) {
            case BODY:
                stage.setText("Body Type");
                AccessBodyType bodyTransport = new AccessBodyType();
                bodyTransport.getBodyOptions(currOptions);
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
    private void openMainActivity() {
        Intent selectionActivity = new Intent(CharacterCreationActivity.this, MainActivity.class);
        startActivity(selectionActivity);
    }
}
