package io.github.krindor.ffxivsimulator.OverallClassesForSim.BuffDebuffState;

/**
 * Created by andre on 2017-05-03.
 */
public class OtherBuffs {
    private boolean kassatsu;

    private boolean duality;

    private boolean bootShine;

    private boolean suiton;

    public boolean isSuiton() {
        return suiton;
    }

    public void setSuiton(boolean suiton) {
        this.suiton = suiton;
    }

    public boolean isKassatsu() {
        return kassatsu;
    }

    public void setKassatsu(boolean kassatsu) {
        this.kassatsu = kassatsu;
    }

    public boolean isDuality() {
        return duality;
    }

    public void setDuality(boolean duality) {
        this.duality = duality;
    }

    public boolean isBootShine() {
        return bootShine;
    }

    public void setBootShine(boolean bootShine) {
        this.bootShine = bootShine;
    }
}
