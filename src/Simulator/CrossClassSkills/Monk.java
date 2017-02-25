package Simulator.CrossClassSkills;

/**
 * Created by andre on 2017-02-10.
 */
public class Monk {
    String skillname;
    int potency;
    String type;
    int cooldown;
    private boolean used;

    public Monk(){
        used = false;
    }

    public void internalRelease() {
        skillname = "Internal_Release";
        potency = -1;
        type = "Cast";
        cooldown = 60;
        used = true;


    }
    public String getInfo() {
        return "Name: " + skillname + " " + "Potency: " + potency + " " + "Type: " + type;
    }

    public int getPotency() {
        if (type.equals("Physical") || type.equals("Magical")) {
            return potency;
        } else return 0;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getType(){
        return type;
    }

    public int getCooldown(){return cooldown;}

    public boolean getUsed(){return used;}


}
