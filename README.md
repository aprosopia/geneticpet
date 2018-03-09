
### Overview

I have started this project in attempt to become a better Java developer and to learn to write Restful APIs.
Before getting into programming I was a veterinarian, this is why the API is about genetic / inherited diseases in dogs and cats.
Hope you will find it useful.

### Technologies Used
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [JDBC / SQL](http://www.oracle.com/technetwork/java/javase/jdbc/index.html)
- [Junit / Hamcrest](https://github.com/junit-team/junit4/wiki/matchers-and-assertthat)


### Endpoints

#### Breed

 Http Method | Description| Url | Request Paramters|
--------|--------|--------|--------|
GET  | List all breeds for certain species   |  /breed/**{species}**  | **{species}**  can be `dog` or `cat`

**Sample Response**

```json
  [
        {
            "name": "akita",
            "id": 1
        },
        {
            "name": "basenji",
            "id": 2
        }
    ]
```

------------
 Http Method | Description| Url | Request Paramters|
--------|--------|--------|--------|
GET | Read breed details   | /breed/**{species}/{id}**   | **{species}**, **{id}**

**Sample Response**

```json
{
    "id": 1,
    "name": "akita",
    "species": "dog",
    "diseasesBySystem": {
        "blood": [
            {
                "id": 2,
                "name": "Hemophilia"
            }
        ],
        "eyes": [
            {
                "id": 1,
                "name": "Cataracts"
            }
        ]
    }
}
```

------------
 Http Method | Description| Url | Request Body
--------|--------|--------|--------|
POST | Breed object to be created   | /breed/**create**| JSON  

**Sample JSON payload**

```json
    {
        "id": 1,
        "name": "akita",
        "species": "dog",
        "diseasesBySystem": {
            "blood": [
                {
                    "id": 2,
                    "name": "Hemophilia"
                }
            ],
            "eyes": [
                {
                    "id": 1,
                    "name": "Cataracts"
                }
            ]
        }
    }
```

------------
#### Disease
Http Method | Description| Url| 
--------|--------|--------|
GET | List all diseases   |   /disease/list

**Sample Response**

```json
[
    {
        "id": 1,
        "name": "Cataracts"
    },
    {
        "id": 2,
        "name": "Hemophilia"
    },
    {
        "id": 3,
        "name": "Gastritis"
    }
]
```

------------
Http Method | Description| Url| Request Parameters  
--------|--------|--------|--------|
GET| Read disease details   | /disease/**{id}**   |  **{id}** is some integer 

**Sample Response**

```json
{
    "id": 1,
    "name": "Cataracts",
    "system": "eyes",
    "description": "  A cataract is any opacity or loss of transparency of the lens of the eye. The opacity may be confined to a small area of the lens or capsule, or it may affect the whole structure. A complete cataract affecting both eyes will result in blindness, whereas small non-progressive cataracts will not interfere with vision. Primary cataracts occur in some breeds; in other breeds the cataract may develop secondarily to another inherited disorder such as progressive retinal atrophy or glaucoma.",
     "afectedBreeds": [
        {
            "name": "Akita",
            "id": 1
        },
        {
            "name": "Basenji",
            "id": 2
        }
    ]
}
```
Http Method | Description| Url| Request Parameters  
--------|--------|--------|--------|
POST | Disease object to be created   | /disease/**create**  | JSON

**Sample JSON payload**

```json
{
    "id": 2,
    "name": "Hemophilia",
    "system": "blood",
    "description": " Hemophilia is a bleeding disorder of varying severity that is due to a deficiency in specific clotting factors.",
     "afectedBreeds": [
        {
            "name": "Akita",
            "id": 1
        },
        {
            "name": "Basenji",
            "id": 2
        }
    ]
}
```
