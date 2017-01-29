package com.phonebook.dao;

import com.phonebook.entity.Contacts;
import com.phonebook.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactDao extends CrudRepository<Contacts, Integer> {

    @Query("select c from Contacts c where (c.surname like '%'||:surname||'%') and (c.user = :user)")
    List<Contacts> getContactsBySurname(@Param("surname") String surname, @Param("user") User user);

    @Query("select c from Contacts c where (c.firstName like '%'||:firstName||'%') and (c.user = :user)")
    List<Contacts> getContactsByFirstName(@Param("firstName") String firstName, @Param("user") User user);

    @Query("select c from Contacts c where (c.mobilePhoneNumber like '%'||:mobilePhoneNumber||'%') and (c.user = :user)")
    List<Contacts> getContactsByMobilePhoneNumber(@Param("mobilePhoneNumber") String mobilePhoneNumber, @Param("user") User user);
}
