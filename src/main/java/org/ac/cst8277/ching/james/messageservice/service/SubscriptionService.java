package org.ac.cst8277.ching.james.messageservice.service;

import jakarta.transaction.Transactional;
import org.ac.cst8277.ching.james.messageservice.entity.Subscription;
import org.ac.cst8277.ching.james.messageservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Subscription subscribe(Subscription subscription) {
        boolean isProducer;
        isProducer = restTemplate.getForObject("http://localhost:9001/users/isProducer/" + subscription.getProducer(), Boolean.class);

        if (isProducer){
            return subscriptionRepository.save(subscription);
        }
        return null;
    }

    @Transactional
    public long deleteByID(UUID subID) {
        return subscriptionRepository.deleteBySubscriptionID(subID);
    }

    public Iterable<Subscription> findBySubID(UUID sub) {
        return subscriptionRepository.findBySubscriber(sub);
    }
}
