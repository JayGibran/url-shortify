# Url Shortify

# Overview
Url Shortify is an restful api for shortening URL,
similar to [TinyURL.com][tinyurl] and https://bitly.com, plus basic statistics over the data.

# Features
- Development based on Spring Boot and Redis.
- Support for building docker image by a maven plugin named dockerfile-maven-plugin.
- Providing REST APIs for shortening a URL and get the original URL.
- Providing extended REST APIs for data statistics, such as the total hits, last access date and etc.

# Stack
  >  The minimum requirements to run the quick start are: 
  >  * JDK 1.8 or above
  >  * Git
  >  * Maven 3.5.3 or above
  >  * MongoDB
  >  * Docker Engine
  >  * A Java IDE like IntelliJ IDEA (optional)
 
 # Run the project
  1. Clone
     ```bash
     $ git clone https://github.com/JayGibran/url-shortify.git
     $ cd url-shortify
     ```
     
  2. Build an image from the Dockerfile
     ```bash
     $ mvn -DskipTests clean package -U
     $ docker-compose up
     ```
  
  3. Test the REST APIs
     ```
     http://127.0.0.1/swagger-ui.html
     ```


# License
Url Shortify is released under the [MIT License](https://github.com/JayGibran/url-shortify/blob/master/LICENSE).

[tinyurl]:https://tinyurl.com/
[dwz]:http://dwz.cn/