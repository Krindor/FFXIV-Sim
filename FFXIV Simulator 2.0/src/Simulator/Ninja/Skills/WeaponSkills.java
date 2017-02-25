package Simulator.Ninja.Skills;

/**
 * Created by andre on 2017-02-08.
 */
public class WeaponSkills {
    private String skillname;
    private int potency;
    private String type;
    private int doTPotency;
    private int doTTime;
    private boolean used;

    public WeaponSkills() {
        used = false;
    }

    public void spinningEdge() {
        skillname = "Spinning_Edge";
        potency = 150;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void gustSlash() {
        skillname = "Gust_Slash";
        potency = 200;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void aeolianEdge() {
        skillname = "Aeolian_Edge";
        potency = 320;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void dancingEdge() {
        skillname = "Dancing_Edge";
        potency = 260;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void armorCrush() {
        skillname = "Armor_Crush";
        potency = 280;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void shadowFang() {
        skillname = "Shadow_Fang";
        potency = 200;
        type = "Physical";
        doTPotency = 40;
        doTTime = 18;
        used = true;


    }

    public void mutilate() {
        skillname = "Mutilate";
        potency = 60;
        type = "Physical";
        doTPotency = 30;
        doTTime = 30;
        used = true;
    }

    public String getInfo() {
        return "Name: " + skillname + " " + "Potency: " + potency + " " + "Type: " + type;
    }

    public int getPotency() {
        return potency;
    }

    public int getDoTPotency() {
        return doTPotency;
    }

    public int getDoTTime() {
        return doTTime;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getType() {
        return type;
    }

    public boolean getUsed() {
        return used;
    }
}
