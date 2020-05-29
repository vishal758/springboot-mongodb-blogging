package com.postInfo.sys.post.data.response.User.Profile;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileData {
    private SocialProfile socialProfile;
    private ContactDetails contactDetails;

    public UserProfileData() {
    }

    public UserProfileData(SocialProfile socialProfile, ContactDetails contactDetails) {
        this.socialProfile = socialProfile;
        this.contactDetails = contactDetails;
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
