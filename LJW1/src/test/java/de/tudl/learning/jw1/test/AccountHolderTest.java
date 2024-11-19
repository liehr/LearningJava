package de.tudl.learning.jw1.test;

import de.tudl.learning.jw1.AccountHolder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;

class AccountHolderTest
{
    @Test
    void testAccountHolderInitialization()
    {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDate birthday = LocalDate.parse("1994-12-01");

        AccountHolder holder = new AccountHolder(id, name, surname, birthday);

        assertEquals(id, holder.getId(), "ID should be equal.");
        assertEquals(name, holder.getName(), "Name should be equal.");
        assertEquals(surname, holder.getSurname(), "Surname should be equal.");
        assertEquals(birthday, holder.getBirthday(), "Birthday should be equal.");
    }

    
}
