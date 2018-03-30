package com.dao;

import java.util.List;

import com.model.Friend;
import com.model.User;

public interface FriendDao {
List<User>suggestedUsers(String email);
void addFriend(Friend friend);
List<Friend>pendingRequests(String toIdEmail);
void acceptRequest(Friend request);
void deleteRequest(Friend request);
public List<Friend> listOfFriends(String email);
}
