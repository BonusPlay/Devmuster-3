package hobbajt.com.helpme;

public class PeopleConditions
{
    private int stable;
    private int serious;
    private int critical;

    public PeopleConditions(int stable, int serious, int critical)
    {
        this.stable = stable;
        this.serious = serious;
        this.critical = critical;
    }

    public int getStable()
    {
        return stable;
    }

    public int getSerious()
    {
        return serious;
    }

    public int getCritical()
    {
        return critical;
    }
}
