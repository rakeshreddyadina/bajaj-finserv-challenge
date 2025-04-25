package com.adina.bajajfinservchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookResult {
    private String regNo;
    private List<List<Integer>> outcome;
}