package hobbajt.com.helpme;

public enum HelpType
{
    FIRE_BRIGADE("Straż Pożarna", R.drawable.ic_fire_extinguisher, R.color.orange, "570859077"),
    AMBULANCE("Karetka", R.drawable.ic_aid_kit, R.color.red, "570859077"),
    POLICE("Policja", R.drawable.ic_policeman, R.color.blue, "570859077"),
    CUSTOM("Własne", R.drawable.ic_person, R.color.green);


    private final String name;
    private final int iconId;
    private String phoneNumber;
    private int color;

    HelpType(String name, int iconId, int color, String phoneNumber)
    {
        this.name = name;
        this.iconId = iconId;
        this.color = color;
        this.phoneNumber = phoneNumber;
    }


    HelpType(String name, int iconId, int color)
    {
        this.name = name;
        this.iconId = iconId;
        this.color = color;
    }

    public int getIconId()
    {
        return iconId;
    }

    public int getColor()
    {
        return color;
    }

    public String getName()
    {
        return name;
    }

    public static HelpType getByName(String name)
    {
        for(HelpType type : values())
        {
            if(name.equals(type.getName()))
                return type;
        }
        return null;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
