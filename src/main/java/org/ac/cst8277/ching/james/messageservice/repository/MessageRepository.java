package org.ac.cst8277.ching.james.messageservice.repository;

import org.ac.cst8277.ching.james.messageservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    long deleteByMessageID(UUID messageID);

    Iterable<Message> findByAuthor(UUID producer);
}
