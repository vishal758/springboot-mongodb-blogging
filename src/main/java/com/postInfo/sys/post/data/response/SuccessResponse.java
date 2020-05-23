package com.postInfo.sys.post.data.response;

public class SuccessResponse {
    private String resourceId;
    private String message;

    public SuccessResponse(String resourceId, String message) {
        this.resourceId = resourceId;
        this.message = message;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
