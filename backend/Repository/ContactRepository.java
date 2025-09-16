package com.example.email.Repository;

import com.example.email.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c JOIN c.campagnes ca WHERE ca.id = :campagneId")
    Set<Contact> findByCampagneId(@Param("campagneId") Long campagneId);


}
