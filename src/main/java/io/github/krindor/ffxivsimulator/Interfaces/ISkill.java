package io.github.krindor.ffxivsimulator.Interfaces;


import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.CurrentState;


public interface ISkill {
    String getName();
    CurrentState useSkill(CurrentState currentState, Abilities abilities);
}
