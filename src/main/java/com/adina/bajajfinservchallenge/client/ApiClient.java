package com.adina.bajajfinservchallenge.client;

import com.adina.bajajfinservchallenge.model.WebhookRequest;
import com.adina.bajajfinservchallenge.model.WebhookResponse;
import com.adina.bajajfinservchallenge.model.WebhookResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WebhookResponse generateWebhook(WebhookRequest request) {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebhookRequest> requestEntity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(url, requestEntity, WebhookResponse.class);
    }

    @Retryable(maxAttempts = 4, backoff = @Backoff(delay = 1000))
    public void submitResult(String webhookUrl, String accessToken, WebhookResult result) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);  // Using the token directly as shown in the example

        HttpEntity<WebhookResult> requestEntity = new HttpEntity<>(result, headers);

        restTemplate.postForObject(webhookUrl, requestEntity, String.class);
    }
}