### Overview

I have started this project in attempt to become a better Java developer and to learn to write Restful APIs.
Before getting into programming I was a doctor, this is why the API is about genetic / inherited diseases in dogs and cats.
Hope you will find it useful.

### Technologies Used
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [JDBC / SQL](http://www.oracle.com/technetwork/java/javase/jdbc/index.html)
- [Junit / Hamcrest](https://github.com/junit-team/junit4/wiki/matchers-and-assertthat)


### Endpoints

#### Breed

 Url | Description| Http Method | Request Paramters | Sample Response
---------------- | -----|---------------|----------------------------|------------------------|
  /breed/**{species}** | List all breeds for certain species   | GET  | **{species}**  can be `dog` or `cat` |  ` {"some": "json" }  ` |
  /breed/**{species}**/**{id}** | Read breed details   | GET  | **{species}**  can be `dog` or `cat` and **{id}** is some integer |  ` {"some": "json" }  ` |


#### Disease

 Url | Description| Http Method | Request Paramters | Sample Response
---------------- | -----|---------------|----------------------------|------------------------|
  /disease/list | List all diseases   | GET  |   |  ` {"some": "json" }  ` |
  /disease/**{id}** | Read disease details   | GET  |  **{id}** is some integer |  ` {"some": "json" }  ` |
