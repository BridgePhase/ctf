package com.bridgephase.ctf.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.ctf.model.jpa.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
