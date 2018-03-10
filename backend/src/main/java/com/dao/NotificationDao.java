package com.dao;

import java.util.List;

import com.model.Notification;

public interface NotificationDao {
	
	List<Notification> getNotificationsNotViewed(String email);
	Notification getNotification(int id);
	void updateNotification(int id);

}
