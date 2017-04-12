package hobbajt.com.helpme;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Username implements Serializable
{
    private String name;
    private String surname;

    public Username(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public boolean isExists()
    {
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(surname);
    }


}
