package com.example.backend.autoLoad;

import com.example.backend.entities.PostType;
import com.example.backend.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PostTypeLoader implements CommandLineRunner {

    @Autowired
    TopicRepository topicRepository ;
    @Override
    public void run(String... args) throws Exception {
        String[] topics = {"Sport", "Technology", "Health", "Religion", "Troll", "Politics", "Personal"};
        for (String topic : topics) {
            if(!topicRepository.existsByName(topic))
                topicRepository.save(new PostType(topic));
        }
    }
}
