package com.adina.bajajfinservchallenge.service;

import com.adina.bajajfinservchallenge.model.WebhookResponse;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MutualFollowersService {

    public List<List<Integer>> findMutualFollowers(List<WebhookResponse.User> users) {
        List<List<Integer>> mutualFollowers = new ArrayList<>();

        // Map userId -> Set of follows for quick lookup
        Map<Integer, Set<Integer>> followsMap = new HashMap<>();
        for (WebhookResponse.User user : users) {
            followsMap.put(user.getId(), new HashSet<>(user.getFollows()));
        }

        Set<String> processedPairs = new HashSet<>();

        for (WebhookResponse.User user : users) {
            int userId = user.getId();
            for (Integer followedId : user.getFollows()) {
                System.out.println("Checking if " + followedId + " follows back " + userId);

                if (followsMap.containsKey(followedId) && followsMap.get(followedId).contains(userId)) {
                    int minId = Math.min(userId, followedId);
                    int maxId = Math.max(userId, followedId);
                    String pairKey = minId + ":" + maxId;

                    if (!processedPairs.contains(pairKey)) {
                        processedPairs.add(pairKey);
                        mutualFollowers.add(Arrays.asList(minId, maxId));

                        System.out.println("Mutual follow found: " + minId + " and " + maxId);
                    }
                }
            }
        }

        return mutualFollowers;
    }
}
