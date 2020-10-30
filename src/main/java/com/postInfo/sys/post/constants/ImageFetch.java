package com.postInfo.sys.post.constants;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ImageFetch implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<Integer, String> map = new HashMap<>();


    public ImageFetch() {
        super();
    }
    private final String url1 = "https://images.unsplash.com/photo-1453928582365-b6ad33cbcf64?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1952&q=80";
    private final String url2 = "https://images.unsplash.com/photo-1432821579285-1b649e5b1ce3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
    private final String url3 = "https://images.unsplash.com/photo-1495465798138-718f86d1a4bc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";
    private final String url4 = "https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";
    private final String url5 = "https://images.unsplash.com/photo-1516062423079-7ca13cdc7f5a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1361&q=80";
    private final String url6 = "https://images.unsplash.com/photo-1488190211105-8b0e65b80b4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";
    private final String url7 = "https://images.unsplash.com/photo-1557367184-663fba4b8b91?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";
    private final String url8 = "https://images.unsplash.com/photo-1510074377623-8cf13fb86c08?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1352&q=80";
    private final String url9 = "https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1453&q=80";
    private final String url10 = "https://images.unsplash.com/photo-1504237111663-37d6094bec09?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80";

    private void initializeImageMap() {
        map.put(0, url1);
        map.put(1, url2);
        map.put(2, url3);
        map.put(3, url4);
        map.put(4, url5);
        map.put(5, url6);
        map.put(6, url7);
        map.put(7, url8);
        map.put(8, url9);
        map.put(9, url10);
        System.out.println("initialize imageMap called...");
//        System.out.println(map);
    }

    public String getRandomImageUrl() {
        Random random = new Random();
        Integer index = random.nextInt(Constants.maxImageUrl - Constants.minImageUrl) + Constants.minImageUrl;
        return this.map.get(index);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.initializeImageMap();
    }
}
