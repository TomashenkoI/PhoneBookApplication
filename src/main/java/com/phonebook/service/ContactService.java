package com.phonebook.service;

import com.phonebook.dao.ContactDao;
import com.phonebook.entity.Contacts;
import com.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactDao contactDao;

    public Contacts getContactById(int id){
        return contactDao.findOne(id);
    }

    public void updateContactInfo(int contactId, String name, String surname, String patronymic,
                                  String mobilePhoneNumber, String homePhoneNumber, String address, String email) {

        Contacts contacts = contactDao.findOne(contactId);

        contacts.setName(name);
        contacts.setSurname(surname);
        contacts.setPatronymic(patronymic);
        contacts.setMobilePhoneNumber(mobilePhoneNumber);
        contacts.setHomePhoneNumber(homePhoneNumber);
        contacts.setAddress(address);
        contacts.setEmail(email);

        contactDao.save(contacts);
    }

    public List<Contacts> listOfContactsByFirstName(String firstName, User user) {
        return contactDao.getContactsByFirstName(firstName, user);
    }

    public List<Contacts> listOfContactsBySurname(String surname, User user) {
        return contactDao.getContactsBySurname(surname, user);
    }

    public List<Contacts> listOfContactsByMobilePhoneNumber(String mobilePhoneNumber, User user){
        return contactDao.getContactsByMobilePhoneNumber(mobilePhoneNumber, user);
    }

    public void removeUserContact(int contactId) {
        contactDao.findOne(contactId).setUser(null);
        contactDao.delete(contactId);
    }

//    public String phoneNumberParser(String phoneNumber) {
//
//        StringBuilder number = new StringBuilder();
//
//        number.append(phoneNumber.substring(0, 3));
//        number.append(phoneNumber.substring(5, 8));
//        number.append(phoneNumber.substring(10, 13));
//        number.append(phoneNumber.substring(14, 16));
//        number.append(phoneNumber.substring(18, 19));
//
//        return number.toString();
//    }



    @Autowired
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
}
