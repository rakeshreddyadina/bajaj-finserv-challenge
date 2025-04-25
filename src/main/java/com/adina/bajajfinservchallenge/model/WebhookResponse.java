package com.adina.bajajfinservchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResponse {
    private String webhook;
    private String accessToken;
    private ResponseData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        private UsersWrapper users;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UsersWrapper {
        private List<User> users;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private int id;
        private String name;
        private List<Integer> follows;
    }
}
