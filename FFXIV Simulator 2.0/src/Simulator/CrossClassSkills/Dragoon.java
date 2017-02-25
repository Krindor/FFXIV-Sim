package Simulator.CrossClassSkills;

/**
 * Created by andre on 2017-02-10.
 */
public class Dragoon {
    String skillname;
    int potency;
    String type;
    int cooldown;
    private boolean used;

    public Dragoon(){
        used = false;
    }

    public void bloodForBlood() {
        skillname = "Blood_for_Blood";
        potency = -1;
        type = "Cast";
        cooldown = 80;
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

    public int getCooldown() {return cooldown;}

    public boolean getUsed(){return used;}


}
