package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Damage.DamageCalculation;
import io.github.krindor.ffxivsimulator.Damage.Formulas;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.JobClasses.Resources;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.Timers.DoTBar;

public class CurrentState {

    private AllBuffs buffs;
    private Resources resources;
    private DoTBar doTBar;
    private String prevAttack;
    private DamageCalculation damageCalculation;
    private double damage;
    private final Formulas formulas;

    public CurrentState(String prevAttack, AllBuffs buffs, DoTBar doTBar, Resources resources, DamageCalculation damageCalculation, Formulas formulas) {
        this.doTBar = doTBar;
        this.buffs = buffs;
        this.resources = resources;
        this.prevAttack = prevAttack;
        this.damageCalculation = damageCalculation;
        this.formulas = formulas;
    }

    public Formulas getFormulas() {
        return formulas;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public DamageCalculation getDamageCalculation() {
        return damageCalculation;
    }

    public void setDamageCalculation(DamageCalculation damageCalculation) {
        this.damageCalculation = damageCalculation;
    }

    public String getPrevAttack() {
        return prevAttack;
    }

    public void setPrevAttack(String prevAttack) {
        this.prevAttack = prevAttack;
    }

    public DoTBar getDoTBar() {
        return doTBar;
    }

    public void setDoTBar(DoTBar doTBar) {
        this.doTBar = doTBar;
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
