package org.ac.cst8277.ching.james.messageservice.service;

import jakarta.transaction.Transactional;
import org.ac.cst8277.ching.james.messageservice.entity.Message;
import org.ac.cst8277.ching.james.messageservice.entity.Subscription;
import org.ac.cst8277.ching.james.messageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RestTemplate restTemplate;

    public Message createMessage(Message message) {

        boolean isProducer;
        isProducer = restTemplate.getForObject("http://localhost:9001/users/isProducer/" + message.getAuthor(), Boolean.class);

        if (isProducer){
            return messageRepository.save(message);
        }
        return null;
    }

    public Iterable<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Transactional
    public long deleteByID(UUID messageID) {
        return messageRepository.deleteByMessageID(messageID);
    }

    public Iterable<Message> getMessageByProducer(UUID producer) {
        return messageRepository.findByAuthor(producer);
    }

    public Iterable<Message> getMessageForSubscriber(UUID sub) {
        ArrayList<Message> messages = new ArrayList<>();
        Iterable<Subscription> subs = subscriptionService.findBySubID(sub);
        ArrayList<UUID> prods = new ArrayList<>();
        for(Subscription subscription : subs){
            prods.add(subscription.getProducer());
        }
        for(UUID prod: prods){
            Iterable<Message> prodMessages = getMessageByProducer(prod);
            for(Message message : prodMessages){
                messages.add(message);
            }
        }
        return messages;
    }
}
