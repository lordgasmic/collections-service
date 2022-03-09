package com.lordgasmic.collections.wine.models;

import lombok.Data;

import java.util.List;

@Data
public class WineFriendsRequest {
    private List<String> users;
    private List<Integer> wineIds;
}
