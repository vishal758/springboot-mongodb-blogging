package com.postInfo.sys.post.data.response.User.Profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profiles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialProfile {

    private String githubProfile;
    private String linkedInProfile;

    public SocialProfile() {
    }

    public SocialProfile(String githubProfile, String linkedInProfile) {
        this.githubProfile = githubProfile;
        this.linkedInProfile = linkedInProfile;
    }

    public String getGithubProfile() {
        return githubProfile;
    }

    public void setGithubProfile(String githubProfile) {
        this.githubProfile = githubProfile;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    @Override
    public String toString() {
        return "Profiles{" +
                ", githubProfile='" + githubProfile + '\'' +
                ", linkedInProfile='" + linkedInProfile + '\'' +
                '}';
    }
}
