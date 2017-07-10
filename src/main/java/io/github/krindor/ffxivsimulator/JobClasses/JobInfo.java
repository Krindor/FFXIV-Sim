package io.github.krindor.ffxivsimulator.JobClasses;

import io.github.krindor.ffxivsimulator.JSON.Buffs;

/**
 * Created by andre on 2017-07-10.
 */
public class JobInfo {
    private static Buffs buffs;
    public JobInfo(Buffs buff){
        buffs = buff;
    }

    public static Buffs getBuffs() {
        return buffs;
    }


}
