package com.bridgephase.ctf.model.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.bridgephase.ctf.model.jpa.CacheEntry;

public interface CacheEntryRepository extends CrudRepository<CacheEntry, String> {

	Iterable<CacheEntry> findByLastUpdatedBefore(Date date);

}
