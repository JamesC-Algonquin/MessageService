package org.ac.cst8277.ching.james.messageservice.controller;

import org.ac.cst8277.ching.james.messageservice.entity.Subscription;
import org.ac.cst8277.ching.james.messageservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/")
    public Subscription subscribe(@RequestBody Subscription subscription){
        return subscriptionService.subscribe(subscription);
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable("id") UUID subID){
        long num = subscriptionService.deleteByID(subID);
        if(num>=1) {
            return "Deleted User.";
        }
        return "No User Found.";
    }

}
