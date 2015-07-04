# Caching for stability

The Open FDA API limits the number of results you can receive with a given API key to 240 results per minute. At first we were under the impression this limit referred to requests per minute but during our functional load test we discovered this was not the case. In order to alleviate that issue, we implemented a caching solution.

The caching works by intercepting the URL request to Open FDA, if the request is found in the cache, the cached response is returned which saves a network call to Open FDA. If it's not found, a request is made to Open FDA and the response is saved as a cache entry. Since the Open FDA datasets are not live data, we can ensure we have the correct results by clearing our cached entries twice a day. 
