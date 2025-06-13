package com.springx.mypro.scheduler;

import com.springx.mypro.cache.AppCache;
import com.springx.mypro.entity.JournalEntry;
import com.springx.mypro.entity.User;
import com.springx.mypro.repository.UserRepositoryImpl;
import com.springx.mypro.service.EmailService;
import com.springx.mypro.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


//    @Scheduled(cron = "0 * * ? * *")
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaEmail(){
        List<User> users = userRepositoryImpl.getUserForSA();

        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x ->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", "Your journal entry is: " + entry + " and the sentiment is: " + sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
