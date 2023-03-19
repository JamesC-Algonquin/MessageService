package org.ac.cst8277.ching.james.messageservice.controller;

import org.ac.cst8277.ching.james.messageservice.entity.Message;
import org.ac.cst8277.ching.james.messageservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/")
    public Message createMessage(@RequestBody Message message){
        return messageService.createMessage(message);
    }

    @GetMapping("/")
    public Iterable<Message> getMessages(){
        return messageService.getMessages();
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable("id") UUID messageID){
        long num = messageService.deleteByID(messageID);
        if(num>=1) {
            return "Deleted User.";
        }
        return "No User Found.";
    }

    @GetMapping("/producer/{id}")
    public Iterable<Message> getMessageByProducer(@PathVariable("id") UUID producer){
        return messageService.getMessageByProducer(producer);
    }

    @GetMapping("/{id}")
    public Iterable<Message> getMessageForSubscriber(@PathVariable("id") UUID sub){
        return messageService.getMessageForSubscriber(sub);
    }
}
