package de.tudl.learning.jw1;

import java.time.LocalDate;
import java.util.UUID;

public class AccountHolder
{
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;

    public AccountHolder(UUID id, String name, String surname, LocalDate birthday)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public AccountHolder()
    {
        id = UUID.randomUUID();
        name = "Name";
        surname = "Surname";
        birthday = LocalDate.now().minusYears(18);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
