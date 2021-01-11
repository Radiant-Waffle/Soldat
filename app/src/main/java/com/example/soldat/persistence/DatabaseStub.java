package com.example.soldat.persistence;

import com.example.soldat.objects.BodyType;
import com.example.soldat.objects.PC;
import com.example.soldat.objects.Skill;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseStub {
    private String dbName;
    private String dbType;
    private ArrayList<BodyType> bodyOptions;
    private ArrayList<Skill> skillOptions;
    private ArrayList<PC> characterList;

    public DatabaseStub(String dbName) {
        this.dbName = dbName;
        dbType = "stub";
    }

    public void openDatabase() {
        bodyOptions = new ArrayList<>();
        skillOptions = new ArrayList<>();
        characterList = new ArrayList<>();
        initializeBodyTypes();
        initializeSkills();
        initializeCharacters();
    }

    public void closeDatabase() {
        System.out.print("Closed " + dbType + " database: " + dbName);
    }

    public ArrayList<BodyType> getBodyOptions() {
        return bodyOptions;
    }

    public void insertBodyType(BodyType currType) {
        if(currType != null) {
            bodyOptions.add(currType);
        }
    }

    public void updateBodyType(BodyType currType) {
        if(currType != null) {
            int index = bodyOptions.indexOf(currType);

            if(index >= 0) {
                bodyOptions.set(index, currType);
            }
        }
    }

    public void deleteBodyType(BodyType currType) {
        if(currType != null) {
            int index = bodyOptions.indexOf(currType);

            if(index >= 0) {
                bodyOptions.remove(index);
            }
        }
    }

    public ArrayList<Skill> getSkillOptions() {
        return skillOptions;
    }

    public void insertSkill(Skill currSkill) {
        if(currSkill != null) {
            skillOptions.add(currSkill);
        }
    }

    public void updateSkill(Skill currSkill) {
        if(currSkill != null) {
            int index = skillOptions.indexOf(currSkill);

            if(index >= 0) {
                skillOptions.set(index, currSkill);
            }
        }
    }

    public void deleteSkill(Skill currSkill) {
        if(currSkill != null) {
            int index = skillOptions.indexOf(currSkill);

            if(index >= 0) {
                skillOptions.remove(index);
            }
        }
    }

    public ArrayList<PC> getPCList() {
        return characterList;
    }

    public void insertPC(PC currPC) {
        if(currPC != null) {
            characterList.add(currPC);
        }
    }

    public void updatePC(PC currPC) {
        if(currPC != null) {
            int index = characterList.indexOf(currPC);

            if(index >= 0) {
                characterList.set(index, currPC);
            }
        }
    }

    public void deletePC(PC currPC) {
        if(currPC != null) {
            int index = characterList.indexOf(currPC);

            if(index >= 0) {
                characterList.remove(index);
            }
        }
    }

    private void initializeBodyTypes() {
        ArrayList<String> subOptions = new ArrayList<>(Arrays.asList("Limb basic"));
        BodyType human = new BodyType("Human", 0, "Standard", subOptions, true);
        bodyOptions.add(human);
        subOptions.add(0, "Beserker");
        BodyType mutant = new BodyType("Mutant", 3, "Weird thing", subOptions, true);
        bodyOptions.add(mutant);
        BodyType limb_basic = new BodyType("Limb basic", 1, "limb cool", true, false);
        bodyOptions.add(limb_basic);
        BodyType beserker = new BodyType("Beserker", -1, "Even weirder thing", false);
        bodyOptions.add(beserker);
    }

    private void initializeSkills() {
        Skill athletics = new Skill("Athletics", 1, "frog boi");
        Skill hacking = new Skill("Hacking", 2, "apagandolas luchas");
        Skill melee = new Skill("Melee", 1, "wan puuunch!");
        skillOptions.add(athletics);
        skillOptions.add(hacking);
        skillOptions.add(melee);
    }

    private void initializeCharacters() {
        ArrayList<Skill> skillList = new ArrayList<>();
        skillList.add(skillOptions.get(1));
        skillList.add(skillOptions.get(2));
        BodyType bType = bodyOptions.get(0);
        PC bobbert = new PC("Bobbert", bType, skillList);
        characterList.add(bobbert);
    }
}
