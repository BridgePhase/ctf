package com.bridgephase.ctf.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.ctf.model.jpa.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
	List<Notification> findAll();
}
