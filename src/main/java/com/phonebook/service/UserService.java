package com.phonebook.service;

import com.phonebook.dao.UserDao;
import com.phonebook.dao.UserFileRepo;
import com.phonebook.entity.Contacts;
import com.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private ContactService contactService;
    private UserDao userDao;
    private UserFileRepo userFileRepo;

    private PasswordEncoder passwordEncoder;

    public void createNewUser(String login, String password, String fullName) {

        User user = new User();

        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setFull_name(fullName);

        userDao.save(user);

        userFileRepo.save(getUserByLogin(user.getLogin()));
    }

    public void deleteUserContactById(int contactId) {
        contactService.removeUserContact(contactId);
    }

    public User getUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public void createNewUserContact(User user, String firstName, String surname, String patronymic,
                                     String mobilePhoneNumber, String homePhoneNumber, String address, String email) {

        Contacts contacts = new Contacts();
        contacts.setUser(user);
        contacts.setName(firstName);
        contacts.setSurname(surname);
        contacts.setPatronymic(patronymic);
        contacts.setMobilePhoneNumber(mobilePhoneNumber);
        contacts.setHomePhoneNumber(homePhoneNumber);
        contacts.setAddress(address);
        contacts.setEmail(email);

        List<Contacts> lisOfContacts = user.getListOfContacts();
        lisOfContacts.add(contacts);

        user.setListOfContacts(lisOfContacts);

        userDao.save(user);
    }

    public List<Contacts> deleteContactFromList(User user ,int contactId) {

        List<Contacts> listOfContacts = user.getListOfContacts();

        listOfContacts.remove(contactService.getContactById(contactId));
        deleteUserContactById(contactId);

        return listOfContacts;
    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return getUserByLogin(userDetails.getUsername());
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserFileRepo(UserFileRepo userFileRepo) {
        this.userFileRepo = userFileRepo;
    }
}

