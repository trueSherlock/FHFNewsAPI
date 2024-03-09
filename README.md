<div>

<h1>REST Api to interact with GNews.io service</h1>
<p>This a simple REST API framework that interacts with a pubic news API for fetching articles.</p>


</div>

# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#star2-about-the-project)


## :star2: About the Project
<p> This is service is a simple REST service that interacts with the <a href="https://gnews.io/">GNews.io</a> API 
service. It provides 3 APIs as described below:</p>

<ul>
    <li><b>/api/topNews</b> - Fetch top N (free API limits to 10 per request) articles </li>
    <li><b>/api/searchByTitle</b> - Search within the article title field for the given keyword</li>
    <li><b>/api/searchByKeyword</b> - General keyword search </li>
</ul>

### :key: Caching 
<p>The API implements simple caching using Caffeine. The cache is configured with a 10 min timeout and 100 item limit
(configurable via application.properties). If any API requests yields no response, that will not be cached.
Response is cached with the API method arguments as keys.</p>

Requests that are not served from cache will trigger a log message like:
```bash
2024-03-08T18:48:41.163-05:00  INFO 56024 --- [nio-8080-exec-5] o.fhf.news.services.GNewsFetcherService  : getTopHeadLines: Firing call to GNews Service.
2024-03-08T18:58:42.061-05:00  INFO 56024 --- [io-8080-exec-10] o.fhf.news.services.GNewsFetcherService  : searchArticles: Firing call to GNews Service.
```

### :key: Documentation
<p>The project leverages Swagger and OpenAPI to automatically generate the documentation for the service. This gives a
a nice page where the service can be tested as well. Once the project is deployed, this can be accessed from 
<a href="http://localhost:8080/swagger-ui/index.html">http://localhost:8080/swagger-ui/index.html</a></p>

A PDF of this screen is included here : (docs/Swagger UI.pdf)



## :toolbox: Getting Started

### :running: Run Locally

Clone the project

```bash
https://github.com/trueSherlock/FHFNewsAPI
```
The project is build using JDK 21 and Maven 3.9.5
You can build the project and run the tests by running
```bash
./mvnw clean package
```
Once successfully built, you can run the service by running:
```bash
./mvnw spring-boot:run
```
Check the stdout to make sure no exceptions are thrown
Once the application runs you should see something like this
```bash
2024-03-08T18:40:51.921-05:00 INFO 56024 --- [ main] o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat started on port 8080 (http) with context path '' 
2024-03-08T18:40:51.925-05:00 INFO 56024 --- [ main] org.fhf.news.FHFNewsApplication : Started FHFNewsApplication in 0.62 seconds (process running for 0.747)
```