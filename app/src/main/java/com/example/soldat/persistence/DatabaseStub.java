package com.example.soldat.persistence;

import com.example.soldat.enums.modificationType;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.Aspects.Modification;
import com.example.soldat.objects.PC;
import com.example.soldat.objects.Aspects.Skill;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseStub {
    private String dbName;
    private String dbType;
    private ArrayList<Aspects> bodyOptions;
    private ArrayList<Aspects> skillOptions;
    private ArrayList<Aspects> modOptions;
    private ArrayList<PC> characterList;

    public DatabaseStub(String dbName) {
        this.dbName = dbName;
        dbType = "stub";
    }

    public void openDatabase() {
        bodyOptions = new ArrayList<>();
        skillOptions = new ArrayList<>();
        modOptions = new ArrayList<>();
        characterList = new ArrayList<>();
        initializeBodyTypes();
        initializeSkills();
        initializeMods();
        initializeCharacters();
    }

    public void closeDatabase() {
        System.out.print("Closed " + dbType + " database: " + dbName);
    }

    public void getBodyOptions(ArrayList<Aspects> bType) {
        if(bType != null) {
            bType.clear();
            bType.addAll(bodyOptions);
        }
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

    public void getSkillOptions(ArrayList<Aspects> skills) {
        if(skills != null) {
            skills.clear();
            skills.addAll(skillOptions);
        }
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

    public void getModOptions(ArrayList<Aspects> mods) {
        if(mods != null) {
            mods.clear();
            mods.addAll(modOptions);
        }
    }

    public void insertMod(Modification currMod) {
        if(currMod != null) {
            modOptions.add(currMod);
        }
    }

    public void updateMod(Modification currMod) {
        if(currMod != null) {
            int index = modOptions.indexOf(currMod);

            if(index >= 0) {
                modOptions.set(index, currMod);
            }
        }
    }

    public void deleteMod(Modification currMod) {
        if(currMod != null) {
            int index = modOptions.indexOf(currMod);

            if(index >= 0) {
                modOptions.remove(index);
            }
        }
    }

    public void getPCList(ArrayList<PC> pc) {
        if(pc != null) {
            pc.clear();
            pc.addAll(characterList);
        }
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

    private void initializeMods() {
        ArrayList<Integer> cost = new ArrayList<>(Arrays.asList(1));
        Modification acuteBalance = new Modification("Acute Balance", cost, "stay upright", modificationType.PHYSICAL_BENEFITS);
        cost = new ArrayList<>(Arrays.asList(-3));
        Modification mute = new Modification("Mute", cost, "...", modificationType.PHYSICAL_DETRIMENTS);
        cost = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7));
        Modification alternateIdentity  = new Modification("Alternate Identity", cost, "Whit a minute, who are you?", modificationType.SOCIAL_BENEFITS);
        modOptions.add(acuteBalance);
        modOptions.add(mute);
        modOptions.add(alternateIdentity);
    }

    private void initializeCharacters() {
        ArrayList<Aspects> skillList = new ArrayList<>();
        skillList.add(skillOptions.get(1));
        skillList.add(skillOptions.get(2));
        BodyType bType = (BodyType)bodyOptions.get(0);
        PC bobbert = new PC("Bobbert", bType, skillList);
        characterList.add(bobbert);
    }
}
