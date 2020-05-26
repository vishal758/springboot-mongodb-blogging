package com.postInfo.sys.post.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.postInfo.sys.post.data.ContactDetails;
import com.postInfo.sys.post.data.SocialProfile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {
    @Id
    private String id;
    private SocialProfile socialProfile;
    private ContactDetails contactDetails;

    public Profile() {
    }

    public Profile(String id, SocialProfile socialProfile, ContactDetails contactDetails) {
        this.id = id;
        this.socialProfile = socialProfile;
        this.contactDetails = contactDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
