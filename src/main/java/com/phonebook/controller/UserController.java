package com.phonebook.controller;

import com.phonebook.entity.Contacts;
import com.phonebook.entity.User;
import com.phonebook.service.ContactService;
import com.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private ContactService contactService;
    private UserService userService;

    @RequestMapping("/createContact")
    public ModelAndView createNewContact() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("doesItAlreadyExist", false);

        modelAndView.setViewName("enterContactInfo");

        return modelAndView;
    }

    @RequestMapping(value = "/findContactsByName", method = RequestMethod.POST)
    public ModelAndView getUserContactsByName(@RequestParam String firstName) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        List<Contacts> listOfContactsByName = contactService.listOfContactsByFirstName(firstName, user);

        modelAndView.addObject("listOfContacts", listOfContactsByName);

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @RequestMapping(value = "/findContactsBySurname", method = RequestMethod.POST)
    public ModelAndView getUserContactsBySurname(@RequestParam String surname) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        List<Contacts> listOfContactsByName = contactService.listOfContactsBySurname(surname, user);

        modelAndView.addObject("listOfContacts", listOfContactsByName);

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @RequestMapping(value = "/findContactsByMobilePhoneNumber", method = RequestMethod.POST)
    public ModelAndView getUserContactsByMobilePhoneNumber(@RequestParam String mobilePhoneNumber) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        List<Contacts> listOfContactsByName = contactService.listOfContactsByMobilePhoneNumber(mobilePhoneNumber, user);

        modelAndView.addObject("listOfContacts", listOfContactsByName);

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @RequestMapping(value = "/editContactInfo_id={contactId}")
    public ModelAndView editContact(@PathVariable int contactId) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("doesItAlreadyExist", true);
        modelAndView.addObject("contact", contactService.getContactById(contactId));

        modelAndView.setViewName("enterContactInfo");

        return modelAndView;
    }

    @RequestMapping(value = "/addNewContact", method = RequestMethod.POST)
    public ModelAndView addNewContact(@RequestParam String firstName,
                                      @RequestParam String surname,
                                      @RequestParam String patronymic,
                                      @RequestParam String mobilePhoneNumber,
                                      @RequestParam String homePhoneNumber,
                                      @RequestParam String address,
                                      @RequestParam String email) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        userService.createNewUserContact(user, firstName, surname, patronymic, mobilePhoneNumber, homePhoneNumber,
                address, email);

        modelAndView.addObject("listOfContacts", user.getListOfContacts());
        modelAndView.addObject("doesItAlreadyExist", false);

        modelAndView.setViewName("contacts");

        return modelAndView;
    }



    @RequestMapping(value = "/updateContact_id={contactId}")
    public ModelAndView updateContact(@PathVariable int contactId,
                                      @RequestParam String firstName,
                                      @RequestParam String surname,
                                      @RequestParam String patronymic,
                                      @RequestParam String mobilePhoneNumber,
                                      @RequestParam String homePhoneNumber,
                                      @RequestParam String address,
                                      @RequestParam String email) {

        ModelAndView modelAndView = new ModelAndView();

        contactService.updateContactInfo(contactId, firstName, surname, patronymic, mobilePhoneNumber,
                homePhoneNumber, address, email);

        User user = userService.getAuthUser();

        modelAndView.addObject("listOfContacts", user.getListOfContacts());

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @RequestMapping(value = "/deleteContactId={contactId}")
    public ModelAndView deleteContact(@PathVariable int contactId) {

        userService.deleteUserContactById(contactId);

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        user.setListOfContacts(userService.deleteContactFromList(user ,contactId));

        modelAndView.addObject("listOfContacts", user.getListOfContacts());

        modelAndView.setViewName("contacts");

        return modelAndView;
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam String login,
                                     @RequestParam String password,
                                     @RequestParam String fullName) {

        ModelAndView modelAndView = new ModelAndView();

        userService.createNewUser(login, password, fullName);

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getAuthUser();

        modelAndView.addObject("listOfContacts", user.getListOfContacts());

        modelAndView.setViewName("contacts");

        return modelAndView;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
