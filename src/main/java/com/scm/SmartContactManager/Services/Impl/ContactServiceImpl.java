package com.scm.SmartContactManager.Services.Impl;

import com.scm.SmartContactManager.Entities.Contacts;
import com.scm.SmartContactManager.Repository.ContactRepository;
import com.scm.SmartContactManager.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactRepository contactRepository;


    @Override
    public Contacts saveContacts(Contacts contacts) {
        String contactId= UUID.randomUUID().toString();
        contacts.setId(contactId);
        contactRepository.save(contacts);
        return contacts;
    }

    @Override
    public Contacts updateContact(Contacts contacts) {
        return null;
    }

    @Override
    public List<Contacts> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contacts getContactById(String id) {
       return contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not Found with given id" + id));
    }

    @Override
    public void deleteContact(String id) {
        var contact=contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not Found to delete with given id" + id));
        contactRepository.delete(contact);
    }

    @Override
    public List<Contacts> searchContacts(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contacts> getContactByUserId(String userId) {
       return contactRepository.findByUserId(userId);
    }
}
