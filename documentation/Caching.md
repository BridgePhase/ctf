# Caching for stability

The openFDA API limits the number of results you can receive with a given [API key](https://open.fda.gov/api/reference/) to 240 results per minute. At first we were under the impression this limit referred to requests per minute but during our functional load test we discovered this was not the case. In order to alleviate that issue, we implemented a caching solution.

The caching works by intercepting the URL request to openFDA, checking if the request is found in the cache, and returning the cached response (thus saving a network call). If it is not found, a request is made to openFDA and the response is saved as a cache entry. Since the openFDA datasets are not live data, we can ensure we have the correct results by clearing our cached entries twice a day. 
