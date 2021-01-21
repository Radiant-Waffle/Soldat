package com.example.soldat.persistence;

import com.example.soldat.enums.modificationType;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.Aspects.Modification;
import com.example.soldat.objects.PlayerCharacter;
import com.example.soldat.objects.Aspects.Skill;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseStub {
    private String dbName;
    private String dbType;
    private ArrayList<Aspects> bodyOptions;
    private ArrayList<Aspects> skillOptions;
    private ArrayList<Aspects> modOptions;
    private ArrayList<PlayerCharacter> characterList;

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

    public void getPCList(ArrayList<PlayerCharacter> playerCharacter) {
        if(playerCharacter != null) {
            playerCharacter.clear();
            playerCharacter.addAll(characterList);
        }
    }

    public void insertPC(PlayerCharacter currPlayerCharacter) {
        if(currPlayerCharacter != null) {
            characterList.add(currPlayerCharacter);
        }
    }

    public void updatePC(PlayerCharacter currPlayerCharacter) {
        if(currPlayerCharacter != null) {
            int index = characterList.indexOf(currPlayerCharacter);

            if(index >= 0) {
                characterList.set(index, currPlayerCharacter);
            }
        }
    }

    public void deletePC(PlayerCharacter currPlayerCharacter) {
        if(currPlayerCharacter != null) {
            int index = characterList.indexOf(currPlayerCharacter);

            if(index >= 0) {
                characterList.remove(index);
            }
        }
    }

    private void initializeBodyTypes() {
        ArrayList<String> subOptions = new ArrayList<>(Arrays.asList("Limb basic"));
        BodyType human = new BodyType("Human", 0, "Standard", subOptions, true);
        bodyOptions.add(human);
        ArrayList<String> subOptions2 = new ArrayList<>();
        subOptions2.add("Limb basic");
        subOptions2.add(0, "Beserker");
        BodyType mutant = new BodyType("Mutant", 3, "Weird thing", subOptions2, true);
        bodyOptions.add(mutant);
        BodyType limb_basic = new BodyType("Limb basic", 2, "limb cool", 4, false);
        bodyOptions.add(limb_basic);
        BodyType beserker = new BodyType("Beserker", -1, "Even weirder thing", false);
        bodyOptions.add(beserker);
        BodyType robot = new BodyType("Robot", 5, "Beep boop", true);
        bodyOptions.add(robot);
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
        cost = new ArrayList<>(Arrays.asList(3));
        Modification mute = new Modification("Mute", cost, "...", modificationType.PHYSICAL_DETRIMENTS);
        cost = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7));
        Modification alternateIdentity  = new Modification("Alternate Identity", cost, "Whit a minute, who are you?", modificationType.SOCIAL_BENEFITS, "Mistaken Identity");
        Modification mistakenIdentity  = new Modification("Mistaken Identity", cost, "Whit a minute, aren't you that guy?", modificationType.SOCIAL_DETRIMENTS, "Alternate Identity");
        cost = new ArrayList<>(Arrays.asList(3));
        ArrayList<String> restrictions = new ArrayList<>(Arrays.asList("Robot"));
        Modification adeptMind  = new Modification("Adept Mind", cost, "Big Brain", modificationType.MENTAL_BENEFITS, restrictions);
        cost = new ArrayList<>(Arrays.asList(1, 2, 3));
        Modification phobia  = new Modification("Phobia", cost, "AHHHHHHHH!!!", modificationType.MENTAL_DETRIMENTS);
        cost = new ArrayList<>(Arrays.asList(4));
        restrictions = new ArrayList<>(Arrays.asList("Human, Mutant, Cyborg"));
        Modification advancedOS  = new Modification("Advanced OS", cost, "Reloading from previous save", modificationType.TECHNOLOGICAL_BENEFITS, restrictions);
        cost = new ArrayList<>(Arrays.asList(1));
        Modification glitch  = new Modification("Glitch", cost, "ERROR! ERROR!", modificationType.TECHNOLOGICAL_DETRIMENTS, restrictions);
        modOptions.add(acuteBalance);
        modOptions.add(mute);
        modOptions.add(alternateIdentity);
        modOptions.add(mistakenIdentity);
        modOptions.add(adeptMind);
        modOptions.add(phobia);
        modOptions.add(advancedOS);
        modOptions.add(glitch);
    }

    private void initializeCharacters() {
        ArrayList<Aspects> skillList = new ArrayList<>();
        skillList.add(skillOptions.get(1));
        skillList.add(skillOptions.get(2));
        BodyType bType = (BodyType)bodyOptions.get(0);
        PlayerCharacter bobbert = new PlayerCharacter("Bobbert", bType, skillList);
        characterList.add(bobbert);
    }
}
