package com.example.soldat.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soldat.R;
import com.example.soldat.objects.BodyType;
import com.example.soldat.objects.PC;

public class CharacterCreationActivity extends AppCompatActivity {
    private PC currCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);
        currCharacter = new PC();
        setupCharacterName();
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
}
