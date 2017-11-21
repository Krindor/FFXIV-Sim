package io.github.krindor.ffxivsimulator.Damage;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class DamageOverTime {

    private int potency;

    private double damage;

    private Formulas formulas;

    private double duration;

    private String name;

    private AllBuffs allBuffs;

    public DamageOverTime(int potency, StatModel statModel, String name, AllBuffs allBuffs) {
        formulas = new Formulas(statModel);
        this.potency = potency;
        this.name = name;
        this.allBuffs = allBuffs;
    }

    public double getDamage() {
        /*Type1 = All Type2 = Crit */
        formulas.setCritMultiplier(allBuffs.getMultiplier(TypeNames.All, TypeNames.Crit));


        damage = formulas.getMultiplier() * (potency / 100.0) * allBuffs.getMultiplier(TypeNames.Physical, TypeNames.All) * formulas.getDhCritMultiplier() * formulas.getSSModifier() * formulas.getPotionMultiplier(allBuffs.getMultiplier(TypeNames.All, TypeNames.Potion));


        return damage;
    }

    public String getName() {
        return name + " DoT";
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void timeChange(double change) {
        duration = duration - change;
    }


}
