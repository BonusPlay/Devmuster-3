package hobbajt.com.helpme;

public class CustomHelpType
{
    private String name;
    private String phoneNumber;

    public CustomHelpType(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
