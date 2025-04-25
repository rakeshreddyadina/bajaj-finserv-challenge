package com.adina.bajajfinservchallenge.runner;

import com.adina.bajajfinservchallenge.client.ApiClient;
import com.adina.bajajfinservchallenge.model.WebhookRequest;
import com.adina.bajajfinservchallenge.model.WebhookResponse;
import com.adina.bajajfinservchallenge.model.WebhookResult;
import com.adina.bajajfinservchallenge.service.MutualFollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final ApiClient apiClient;
    private final MutualFollowersService mutualFollowersService;

    @Value("${user.name:Adina Rakesh Reddy}")
    private String userName;

    @Value("${user.regNo:RA2211003020423}")
    private String regNo;

    @Value("${user.email:ra5958@srmist.edu.in}")
    private String email;

    @Autowired
    public ApplicationRunner(ApiClient apiClient, MutualFollowersService mutualFollowersService) {
        this.apiClient = apiClient;
        this.mutualFollowersService = mutualFollowersService;
    }

    @Override
    public void run(String... args) {
        try {
            // Prepare webhook request
            WebhookRequest request = new WebhookRequest(userName, regNo, email);
            System.out.println("Sending webhook request with: " + request);

            // Call webhook API
            WebhookResponse response = apiClient.generateWebhook(request);
            System.out.println("Webhook response received.");
            System.out.println("Webhook URL: " + response.getWebhook());

            // 👇 Log received users data here
            List<WebhookResponse.User> userList = response.getData().getUsers().getUsers();
            System.out.println("Users data received from webhook: " + userList);

            // Find mutual followers
            List<List<Integer>> mutualFollowers =
                    mutualFollowersService.findMutualFollowers(userList);
            System.out.println("Mutual followers found: " + mutualFollowers);

            // Prepare result object
            WebhookResult result = new WebhookResult(regNo, mutualFollowers);

            // Submit result back to webhook URL
            System.out.println("Submitting result to webhook...");
            apiClient.submitResult(response.getWebhook(), response.getAccessToken(), result);

            System.out.println("Task completed successfully!");
        } catch (Exception e) {
            System.err.println("Error occurred during webhook processing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
