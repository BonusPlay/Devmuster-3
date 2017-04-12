package hobbajt.com.helpme.Events;

import java.util.ArrayList;
import java.util.List;

import hobbajt.com.helpme.HelpType;
import hobbajt.com.helpme.R;

public enum Events
{
    POŻAR("Pożar", HelpType.FIRE_BRIGADE, R.drawable.ic_fire),
    PRZEWRÓCONE_DRZEWO("Przewrócone drzewo", HelpType.FIRE_BRIGADE, R.drawable.ic_broken_tree),
    STŁUCZKA("Stłuczka", HelpType.FIRE_BRIGADE, R.drawable.ic_crash),
    WYPADEK("Wypadek", HelpType.AMBULANCE, R.drawable.ic_crash),
    ZASŁABNIĘCIE("Zasłabnięcie", HelpType.AMBULANCE, R.drawable.ic_faint),
    POTRĄCENIE("Potrącenie", HelpType.AMBULANCE, R.drawable.ic_accident),
    BÓJKA("Bójka", HelpType.POLICE, R.drawable.ic_fight),
    KRADZIEŻ("Kradzież", HelpType.POLICE, R.drawable.ic_thief),
    NAPAD("Napad", HelpType.POLICE, R.drawable.ic_shoot);

    private final String name;
    private final HelpType helpType;
    private final int iconId;

    Events(String name, HelpType helpType, int iconId)
    {
        this.name = name;
        this.helpType = helpType;
        this.iconId = iconId;
    }

    public static List<Events> getByCategory(HelpType helpType)
    {
        List<Events> events = new ArrayList<>();
        for(Events event : values())
        {
            if(event.getHelpType() == helpType)
                events.add(event);
        }
        return events;
    }

    public HelpType getHelpType()
    {
        return helpType;
    }

    public String getName()
    {
        return name;
    }

    public int getIconId()
    {
        return iconId;
    }
}
