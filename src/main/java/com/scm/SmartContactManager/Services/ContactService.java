package com.scm.SmartContactManager.Services;

import com.scm.SmartContactManager.Entities.Contacts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

Contacts saveContacts(Contacts contacts);

//update contact
Contacts updateContact(Contacts contacts);

//get Contact
List<Contacts>getAllContacts();

//get single contact
Contacts getContactById(String id);

//delete contact
void deleteContact(String id);

//search Contact
List<Contacts>searchContacts(String name,String email,String phoneNumber);

//get Contact by userId
List<Contacts>getContactByUserId(String userId);











}
