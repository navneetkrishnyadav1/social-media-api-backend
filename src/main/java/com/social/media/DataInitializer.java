package com.social.media;

import com.social.media.com.social.media.repositories.PostRepository;
import com.social.media.com.social.media.repositories.SocialGroupRepository;
import com.social.media.com.social.media.repositories.SocialProfileRepository;
import com.social.media.com.social.media.repositories.SocialUserRepository;
import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//this class means when project will run all things will done while running
@Configuration
public class DataInitializer {

    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository profileRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository userRepository,
                           SocialGroupRepository groupRepository,
                           SocialProfileRepository profileRepository,
                           PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
    }
//when application will start
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Create users
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();
            SocialUser user4 = new SocialUser();

            // Save users
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

            // Create groups
            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            //Add users to group
            group1.getSocialUsers().add(user1);
            group1.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user3);
            group2.getSocialUsers().add(user4);
            group2.getSocialUsers().add(user2);

            groupRepository.save(group1);
            groupRepository.save(group2);

            //Associate Users to group
            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);
            user4.getGroups().add(group2);

            //Save users back to database to update association
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

            // Create posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // Create profiles
            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            profileRepository.save(profile1);
            profileRepository.save(profile2);
            profileRepository.save(profile3);

            //FETCH TYPES
            System.out.println("FETCHING SOCIAL USER");
            userRepository.findById(1L);
        };
    }
}
