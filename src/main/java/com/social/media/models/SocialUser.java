package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_profile_id")
    private SocialProfile socialProfile;

    //here List because user will have many post or list of post that user posted; and this should have onetomany
    @OneToMany (mappedBy = "socialUser")
    private List<Post> posts = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn (name = "user_Id"),
            inverseJoinColumns = @JoinColumn (name = "group_Id")
    )
    private Set<SocialGroup> groups = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
