package com.springx.mypro.scheduler;

import com.springx.mypro.cache.AppCache;
import com.springx.mypro.entity.JournalEntry;
import com.springx.mypro.entity.User;
import com.springx.mypro.enums.Sentiment;
import com.springx.mypro.repository.UserRepositoryImpl;
import com.springx.mypro.service.EmailService;
import com.springx.mypro.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaEmail(){
        List<User> users = userRepositoryImpl.getUserForSA();

        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x ->x.getSentiment()).collect(Collectors.toList());
            HashMap<Sentiment, Integer> sentimentsCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if (sentiment != null) {
                    sentimentsCounts.put(sentiment, sentimentsCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment, Integer> entry : sentimentsCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null){
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }
}
