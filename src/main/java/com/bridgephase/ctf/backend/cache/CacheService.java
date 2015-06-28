package com.bridgephase.ctf.backend.cache;

import java.util.Date;

public interface CacheService {

	public void clearCacheEntriesOlderThan(Date date);
	
}
