package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "socialProfile")
    @JoinColumn(name = "social_user")
    @JsonIgnore
    private SocialUser user;

    private String description;
    public void  setSocialProfile(SocialProfile socialProfile){
        this.user = new SocialUser();
        if(user.getSocialProfile() != this){
            user.setSocialProfile(this);
        }

    }

}
