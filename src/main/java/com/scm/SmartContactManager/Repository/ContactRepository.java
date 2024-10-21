package com.scm.SmartContactManager.Repository;

import com.scm.SmartContactManager.Entities.Contacts;
import com.scm.SmartContactManager.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, String> {

    //find the contact by user id
    //this one is custom finder query
    List<Contacts> findByUser(User user);


    //this one is custom query method
    @Query("select c from Contacts c where c.user.id=:userId")
    List<Contacts>findByUserId(@Param("userId") String userId);


}
