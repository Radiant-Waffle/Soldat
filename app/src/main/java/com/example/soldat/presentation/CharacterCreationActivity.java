package com.example.soldat.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soldat.R;
import com.example.soldat.business.AccessBodyType;
import com.example.soldat.business.AccessModifications;
import com.example.soldat.business.AccessSkill;
import com.example.soldat.enums.buildStage;
import com.example.soldat.enums.modificationType;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.Aspects.Modification;
import com.example.soldat.objects.Aspects.Skill;
import com.example.soldat.objects.PlayerCharacter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CharacterCreationActivity extends AppCompatActivity {
    private PlayerCharacter currCharacter;
    private buildStage currStage;
    private ArrayList<Aspects> currOptions;
    private TableLayout aspectTable;
    private ExpandableListView modTable;
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
        modTable = findViewById(R.id.mod_table);
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
        String currentPointCount = "Remaining\npoints:" + currCharacter.getRemainingCreationPoints();
        currentPoints.setText(currentPointCount);
    }

    private void setupNavOptions() {
        Button backButton = findViewById(R.id.back_Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(currStage) {
                    case BODY:
                        new AlertDialog.Builder(CharacterCreationActivity.this)
                            .setTitle("Warning: All current progress will be lost")
                            .setMessage("Are you sure you want to continue?")
                        // Set up the buttons
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    currCharacter = null;
                                    openMainActivity();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
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
        if(!subTypes.isEmpty()) {
            for (Aspects a : currOptions) {
                BodyType currType = (BodyType) a;
                String typeName = currType.getName();
                if (subTypes.contains(typeName)) {
                    subOptions.add(currType);
                }
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
        modTable.removeAllViews();
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
            cost = cost + "X ";
            ViewGroup parent = (ViewGroup) costView.getParent();
            int index = parent.indexOfChild(costView);
            costView.setText(cost);
            final List<Integer> values = new ArrayList<>();
            for(int i = 1; i <= curr.getMultiple(); i++) { values.add(i); }
            ArrayAdapter<Integer> val = new ArrayAdapter<Integer>(this, R.layout.spinner_item, values);
            val.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            amount.setBackgroundResource(R.drawable.spinner_shape);
            amount.setPopupBackgroundResource(R.drawable.spinner_shape);
            amount.setAdapter(val);
            if(curr.getMultipleSelected() != 1) { amount.setSelection(curr.getMultipleSelected() - 1); }
            else { amount.setSelection(0); }
            amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(checkView.isChecked()) {
                        currCharacter.changeRemainingCreationPoints(-curr.getMultipleSelected()*baseCost);
                        int currCost = (int) amount.getSelectedItem();
                        curr.setMultipleSelected(currCost);
                        currCharacter.changeRemainingCreationPoints(currCost*baseCost);
                        updatePoints();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            TableRow.LayoutParams spinLP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT, 1f);
            spinLP.setMarginEnd(10);
            spinLP.gravity = Gravity.START;
            amount.setLayoutParams(spinLP);
            newRow.addView(amount, index + 1);
        }
        infoButton.setOnClickListener(view -> aspectDescription(name, desc));
        checkView.setOnClickListener(view -> {
            int currCost = baseCost;
            if(curr.getMultiple() != 1) {
                currCost = (int)amount.getSelectedItem();
                curr.setMultipleSelected(currCost);
            }
            if(checkView.isChecked()) {
                currCharacter.addBodyAugments(curr);
                currCharacter.changeRemainingCreationPoints(currCost);
            }
            else {
                currCharacter.removeBodyAugments(curr);
                currCharacter.changeRemainingCreationPoints(-currCost);
            }
            updatePoints();
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
            infoButton.setOnClickListener(view -> aspectDescription(name, desc));
            newRow.setOnClickListener(view -> {
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
            });
            if(currCharacter.getSkillList().contains((Aspects)currSkill)) { newRow.setSelected(true); }
            aspectTable.addView(newRow, lp);
        }
    }

    private void createModList() {
        for(modificationType type : modificationType.values()) {
            currOptions = currCharacter.getModList(type);
            createUserModList(type);
        }
        /*AccessModifications modTransport = new AccessModifications();
        modTransport.getModOptions(currOptions, modificationType.PHYSICAL_BENEFITS);
        createField(true);
        modTransport.getModOptions(currOptions, modificationType.PHYSICAL_DETRIMENTS);
        createField(false);
        modTransport.getModOptions(currOptions, modificationType.SOCIAL_BENEFITS);
        createField(true);
        modTransport.getModOptions(currOptions, modificationType.SOCIAL_DETRIMENTS);
        createField(false);
        modTransport.getModOptions(currOptions, modificationType.MENTAL_BENEFITS);
        createField(true);
        modTransport.getModOptions(currOptions, modificationType.MENTAL_DETRIMENTS);
        createField(false);
        modTransport.getModOptions(currOptions, modificationType.TECHNOLOGICAL_BENEFITS);
        createField(true);
        modTransport.getModOptions(currOptions, modificationType.TECHNOLOGICAL_DETRIMENTS);
        createField(false);*/
    }

    private void createUserModList(modificationType type) {
        String title = "";
        switch(type) {
            case PHYSICAL_BENEFITS:
                title = "Physical Benefits";
                break;
            case PHYSICAL_DETRIMENTS:
                title = "Physical Detriments";
                break;
            case SOCIAL_BENEFITS:
                title = "Social Benefits";
                break;
            case SOCIAL_DETRIMENTS:
                title = "Social Detriments";
                break;
            case MENTAL_BENEFITS:
                title = "Mental Benefits";
                break;
            case MENTAL_DETRIMENTS:
                title = "Mental Detriments";
                break;
            case TECHNOLOGICAL_BENEFITS:
                title = "Technological Benefits";
                break;
            case TECHNOLOGICAL_DETRIMENTS:
                title = "Technological Detriments";
                break;
        }
        for(Aspects a : currOptions) {
            Modification currMod = (Modification) a;
            String name = currMod.getName();
            int currCost = currMod.getLevelSelected();
            String cost = "" + currMod.getCost().get(currCost);
            String desc = currMod.getDescription();
            final ConstraintLayout newRow = (ConstraintLayout) getLayoutInflater().inflate(R.layout.modification_block,null);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 120;
            lp.setMargins(10,10,10,10);
            newRow.setLayoutParams(lp);
            final TextView nameView = newRow.findViewById(R.id.mod_title);
            final TextView costView = newRow.findViewById(R.id.mod_cost);
            final ImageButton infoButton = newRow.findViewById(R.id.mod_info);
            final ImageButton deleteButton = newRow.findViewById(R.id.delete_Button);
            final LinearLayout boxView = newRow.findViewById(R.id.mod_indicator);
            nameView.setText(name);
            costView.setText(cost);
            infoButton.setOnClickListener(view -> aspectDescription(name, desc));
            ArrayList<Integer> costList = currMod.getCost();
            if(costList.size() > 1) {
                ArrayList<View> boxes = makeBoxes(costList.size(), currMod.getLevelSelected());
                for(View box : boxes) {
                    boxView.addView(box);
                }
                newRow.setOnClickListener(view -> {
                    int costValue = currMod.getCost().get(currCost);
                    currCharacter.changeRemainingCreationPoints(-costValue);
                    int newCost = costSlider(costList, currCost);
                    costValue = currMod.getCost().get(newCost);
                    costView.setText(costValue);
                    currMod.setLevelSelected(newCost);
                    currCharacter.changeRemainingCreationPoints(costValue);
                    updatePoints();
                });
            }
            deleteButton.setOnClickListener(view -> {
                int costValue = currMod.getCost().get(currCost);
                currCharacter.removeMod(currMod);
                modTable.removeView(newRow);
            });
        }
    }

    private ArrayList<View> makeBoxes(int size, int level) {
        ArrayList<View> boxes = new ArrayList<>();
        for(int x = 0; x < size; x++) {
            View box = new View(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 30;
            lp.width = 30;
            lp.gravity = Gravity.CENTER;
            lp.setMargins(4, 4, 4, 4);
            box.setLayoutParams(lp);
            box.setBackgroundResource(R.drawable.skill_level_selector);
            box.setActivated(x < level);
            boxes.add(box);
        }
        return boxes;
    }

    private int costSlider(ArrayList<Integer> costList, int currCost) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CharacterCreationActivity.this);
        View view = getLayoutInflater().inflate( R.layout.slider_dialog, null);
        builder.setView(view);
        LinearLayout costView = view.findViewById(R.id.seekLabel);
        for(int labelCost : costList) {
            TextView label = new TextView(this);
            label.setText(labelCost);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            lp.width = 0;
            label.setLayoutParams(lp);
            label.setTextColor(Color.WHITE);
            costView.addView(label, lp);
        }
        SeekBar seek = view.findViewById(R.id.seekBar);
        seek.setMax(costList.size() - 1);
        seek.setProgress(currCost);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return seek.getProgress();
    }

    /*private void createField(Boolean benefit) {
        final TableRow newRow = (TableRow) getLayoutInflater().inflate(R.layout.modification_table,null);
        final RecyclerView rowView = newRow.findViewById(R.id.mod_row_view);
        for(Aspects a : currOptions) {
            int currCost = 0;
            Modification currMod = (Modification) a;
            String name = currMod.getName();
            ArrayList<Integer> cost = currMod.getCost();
            String desc = currMod.getDescription();
            final ConstraintLayout modBlock = (ConstraintLayout) getLayoutInflater().inflate(R.layout.modification_block, null);
            final TextView nameView = modBlock.findViewById(R.id.mod_title);
            final TextView costView = modBlock.findViewById(R.id.mod_cost);
            final LinearLayout indicatorView = modBlock.findViewById(R.id.mod_indicator);
            nameView.setText(name);
            String costText = "" + cost.get(currCost);
            ArrayList<Aspects> charModList = currCharacter.getModList();
            if(charModList.contains(a)) {
                Modification modInChar = (Modification)charModList.get(charModList.indexOf(a));
                if(modInChar.getLevelSelected() != cost.size()) {
                    currCost = cost.get(modInChar.getLevelSelected());
                    costText = "" + cost.get(currCost);
                } else {
                    costText = "Max";
                }
            }
            costView.setText(costText);
            ArrayList<View> boxes = new ArrayList<>();
            for(int x = 0; x < cost.size(); x++) {
                View box = new View(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.height = 30;
                lp.width = 30;
                lp.gravity = Gravity.CENTER;
                lp.setMargins(4, 4, 4, 4);
                box.setLayoutParams(lp);
                box.setBackgroundResource(R.drawable.skill_level_selector);
                box.setActivated(x < currCost);
                indicatorView.addView(box);
                boxes.add(box);
            }
            modBlock.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    aspectDescription(name, desc);
                    return true;
                }
            });
            modBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentCostSelected;

                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            lp.height = 240;
            lp.width = 360;
            lp.setMargins(10,10,10,10);
            modBlock.setLayoutParams(lp);
            rowView.addView(modBlock, lp);

        }
        aspectTable.addView(newRow);
    }*/

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
                aspectTable.setVisibility(View.VISIBLE);
                modTable.setVisibility(View.GONE);
                createBodyList();
                break;
            case SKILL:
                stage.setText("Skill options");
                AccessSkill skillTransport = new AccessSkill();
                skillTransport.getSkillOptions(currOptions);
                aspectTable.setVisibility(View.VISIBLE);
                modTable.setVisibility(View.GONE);
                createSkillList();
                break;
            case MOD:
                stage.setText("Benefits & Detriments");
                aspectTable.setVisibility(View.GONE);
                modTable.setVisibility(View.VISIBLE);
                createModList();
                break;
        }
    }
    private void openMainActivity() {
        Intent selectionActivity = new Intent(CharacterCreationActivity.this, MainActivity.class);
        startActivity(selectionActivity);
    }
}
