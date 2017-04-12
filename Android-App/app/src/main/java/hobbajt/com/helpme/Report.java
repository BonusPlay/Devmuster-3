package hobbajt.com.helpme;


import hobbajt.com.helpme.Location.Location;

public class Report
{
    private String phoneNumber;
    private Location location;
    private Username username;
    private int priority;
    private String eventType;
    private PeopleConditions people;

    public Report(String phoneNumber, Location location, String eventType, PeopleConditions people, Username username, int priority)
    {
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.eventType = eventType;
        this.people = people;
        this.username = username;
        this.priority = priority;
    }

    public Report()
    {
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public Location getLocation()
    {
        return location;
    }

    public String getEventType()
    {
        return eventType;
    }

    public PeopleConditions getPeopleCount()
    {
        return people;
    }

    public Username getUsername()
    {
        return username;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public void setUsername(Username username)
    {
        this.username = username;
    }

    public void setPeople(PeopleConditions people)
    {
        this.people = people;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
