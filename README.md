# Url Shortify

# Overview
Url Shortify is an restful api for shortening URL,
similar to [TinyURL.com][tinyurl] and [bitly.com][bitly], plus basic statistics over the data.

# Features
- Development based on Spring Boot, Redis and MongoDB.
- Support for building on docker-compose.
- Providing REST APIs for shortening a URL and get the original URL.
- Providing extended REST APIs for data statistics, such as the total hits, last access date and etc.

# Stack
  >  The minimum requirements to run the quick start are: 
  >  * JDK 1.8 or above
  >  * Git
  >  * Maven 3.5.3 or above
  >  * MongoDB
  >  * Redis
  >  * Docker Engine
  >  * Swagger
  >  * A Java IDE like IntelliJ IDEA (optional)
 
 # Run the project
  1. Clone
     ```bash
     $ git clone https://github.com/JayGibran/url-shortify.git
     $ cd url-shortify
     ```
     
  2. Build an image from the Dockerfile
     ```bash
     $ mvn -DskipTests clean package
     $ docker-compose up
     ```
   By default the Server will run on localhost:8080
  
  3. Test the REST APIs
     ```
     http://localhost:8080/swagger-ui.html
     ```

### API

**Shortify a URL:**

```
POST /api/v1/shortener
```
**Request Scheme:**
```
{"originalUrl":"www.google.com"}
```

**Get Original URL:**
```
GET /api/v1/redirect/{key}
```
where ***KEY*** Is shorted url 

**Get Statistics:**

```
/api/v1/statistics/{key}
```

where ***KEY*** Is shorted url 

# License
Url Shortify is released under the [MIT License](https://github.com/JayGibran/url-shortify/blob/master/LICENSE).

[tinyurl]:https://tinyurl.com/
[bitly]:https://bitly.com