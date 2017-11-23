package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.JobClasses.Resources;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.Timers.DoTBar;

class CurrentState {
    private TypeNames type;
    private int potency;
    private String attack;
    private TypeNames type2;
    private AllBuffs buffs;
    private Resources resources;
    private DoTBar doTBar;

    public CurrentState(TypeNames type, int potency, String attack, TypeNames type2, AllBuffs buffs, DoTBar doTBar, Resources resources) {
        this.type = type;
        this.doTBar = doTBar;
        this.potency = potency;
        this.attack = attack;
        this.type2 = type2;
        this.buffs = buffs;
        this.resources = resources;
    }

    public TypeNames getType() {
        return type;
    }

    public void setType(TypeNames type) {
        this.type = type;
    }

    public DoTBar getDoTBar() {
        return doTBar;
    }

    public void setDoTBar(DoTBar doTBar) {
        this.doTBar = doTBar;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public TypeNames getType2() {
        return type2;
    }

    public void setType2(TypeNames type2) {
        this.type2 = type2;
    }

    public AllBuffs getBuffs() {
        return buffs;
    }

    public void setBuffs(AllBuffs buffs) {
        this.buffs = buffs;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
