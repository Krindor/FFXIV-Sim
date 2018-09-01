package io.github.krindor.ffxivsimulator.Skill.Ninja;


import io.github.krindor.ffxivsimulator.Damage.Formulas;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.Interfaces.ISkill;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.CurrentState;


public class SpinningEdge implements ISkill{
    private String name;
    public CurrentState useSkill(CurrentState currentState, Abilities abilities){
        double generalMultiplier = Math.floor((abilities.getPotency()) * currentState.getFormulas().getMultiplier()) * currentState.getFormulas().getPotionMultiplier(currentState.getBuffs().getMultiplier(TypeNames.All, TypeNames.Potion));

        Formulas formulas = currentState.getFormulas();

        formulas.setDirectHitMultiplier(currentState.getBuffs().getMultiplier(TypeNames.All, TypeNames.DirectHit));
        formulas.setCritMultiplier(currentState.getBuffs().getMultiplier(TypeNames.All, TypeNames.Crit));

        double damage = generalMultiplier * currentState.getBuffs().getMultiplier(abilities.getType(), abilities.getType2()) * formulas.getDhCritMultiplier();
        return currentState;
    }


    public String getName() {
        return name;
    }
}
