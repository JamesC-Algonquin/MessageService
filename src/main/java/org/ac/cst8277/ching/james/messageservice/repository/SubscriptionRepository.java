package org.ac.cst8277.ching.james.messageservice.repository;

import org.ac.cst8277.ching.james.messageservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    long deleteBySubscriptionID(UUID subscriptionID);

    Iterable<Subscription> findBySubscriber(UUID sub);
}
