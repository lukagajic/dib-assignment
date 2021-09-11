# DIB Travel assignment - Junior Backend Developer
## About project
This project was created as a task for the position of Junior Backend Developer at DIB Travel company.
## Features
1. Fetching all beers from the database
2. Fetching one beer from the database by ID
3. Deleting beer from the database by ID
4. Logic for populating the database with the maximum of 10 beers
## Additional features and constraints
1. Initial empty database seeding with 10 beers on the first run of the application using CommandLineRunner.
2. Only unique beers can be persisted inside the database.
3. If there is !=0 beers, populate the database up to 10 beers.
4. Communicate with the external API to collect and store beer data.
## API Documentation
### Available routes
#### 1.  Fetch all beers inside the database
```http
GET /beers
```
#### cURL
```
curl http://localhost:8080/beers
```
#### Response:
```json
[
    {
        "beerId": 1,
        "name": "Berliner Weisse With Yuzu - B-Sides",
        "description": "Japanese citrus fruit intensifies the sour nature of this German classic.",
        "meanTemperature": 68.75
    },
    {
        "beerId": 2,
        "name": "Hello My Name Is Agnetha",
        "description": "Brewed exclusively for the Swedish market, this Hello My Name brew features a twist of flavour inspired by Sweden.",
        "meanTemperature": 66.0
    },
    {
        "beerId": 3,
        "name": "Tokyo Rising Sun - Lowland",
        "description": "A forgotten gem in the deepest, darkest corner of the warehouse. Aged in a Lowland whisky cask resulting in decadent chocolate, toasted vanilla, indulgent spiced fruit, a mesmerizingly hypnotic mouthfeel and new layers that emerge on every sip.",
        "meanTemperature": 65.0
    },
    {
        "beerId": 4,
        "name": "Prototype Black Rye IPA",
        "description": "Our Prototype Challenge sees us pit three new trial beers against each other. This Black Rye IPA combines two spins on an American IPA, in the left-right jab combo of extra ingredients.",
        "meanTemperature": 66.0
    },
    {
        "beerId": 5,
        "name": "Tactical Nuclear Penguin",
        "description": "This beer is about pushing the boundaries, it is about taking innovation in beer to a whole new level. Dark and decadent, plum, treacle and roast coffee are amplified beyond any stout you've had before.",
        "meanTemperature": 65.0
    },
    {
        "beerId": 6,
        "name": "Pilsen Lager",
        "description": "Our Unleash the Yeast series was an epic experiment into the differences in aroma and flavour provided by switching up your yeast. We brewed up a wort with a light caramel note and some toasty biscuit flavour, and hopped it with Amarillo and Centennial for a citrusy bitterness. Everything else is down to the yeast. Pilsner yeast ferments with no fruity esters or spicy phenols, although it can add a hint of butterscotch.",
        "meanTemperature": 65.0
    },
    {
        "beerId": 7,
        "name": "Sidewalk Shark",
        "description": "A citrus-infused gose - a traditional German sour wheat beer brewed with salt, in this case with the addition of kaffir lime leaf and lemon peel.",
        "meanTemperature": 60.0
    },
    {
        "beerId": 8,
        "name": "Simcoe",
        "description": "A special release of our IPA is Dead series - IPA is Dead Simoce. Hopped to hell with citrusy bitter and aroma hops from the West Coast of the USA. Bitter, orange, mandarin, floral, this IPA showcases the best the west has to offer.",
        "meanTemperature": 65.0
    },
    {
        "beerId": 9,
        "name": "Opaque Jake",
        "description": "Our first single-hop Vermont IPA; all the flavour in this beer comes from a combination of Citra's naturally complex flavour profile, the yeast-produced esters, and the biotransformation of the hop oils by its contact with the yeast.",
        "meanTemperature": 66.0
    },
    {
        "beerId": 10,
        "name": "Hop Shot",
        "description": "Hop Shot is a high-velocity hop hit. Our latest experiment in ice-distillation is a west coast IPA, subjected to Antarctic temperatures and concentrated by removing the ice that forms. The result is a 22% ABV hop grenade in a 110ml bottle.",
        "meanTemperature": 65.0
    }
]
```
#### 2. Fetch a single beer from the database by ID
```http
GET /beers/{id}
```
#### cURL
```
curl http://localhost:8080/beers/1
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `id` | `number` | *Required*. Unique beer id. |
#### Response:
```json
{
    "beerId": 1,
    "name": "Berliner Weisse With Yuzu - B-Sides",
    "description": "Japanese citrus fruit intensifies the sour nature of this German classic.",
    "meanTemperature": 68.75
}
```
#### 3. Delete a beer by ID
```http
DELETE /beers/{id}
```
#### cURL
```
curl -X DELETE http://localhost:8080/beers/1
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `id` | `number` | *Required*. Unique beer id. |
#### Case 1 - Beer exists and the ID is valid

#### Response:
```json
{
    "successful": true,
    "message": "Beer with an id of 1 successfully deleted!"
}
```
#### Case 2 - Beer does not exist or the ID is invalid
#### Response:
```json
{
    "successful": false,
    "message": "Beer with an id of 15 could not be found!"
}
```
#### 4. Populate the database with the maximum of 10 beers
```http
POST /beers/{id}
```
#### cURL
```
curl -X POST http://localhost:8080/beers
```
#### Case 1 - The database consists of e.g. 8 beers
#### Response:
```json
{
    "successful": true,
    "message": "Succesfully added 2 beers to the database!"
}
```
#### Case 2 - The database is full
#### Response:
```json
{
    "successful": false,
    "message": "The database is full"
}
```
#### Case 3 - The database is empty (constraint from the assignment, if there is !=0 beers, then fill up the database up to 10 beers)
```json
{
    "successful": false,
    "message": "There are no beers in database!"
}
```
## Questions
### 1. What motivated your technology choices?
I used *Spring Boot* to create a RESTful service, it's a new, easier way to create "production-ready" applications on top of the Spring Framework. It comes along with "starter" libraries which are bundled and pre-configured in such way that developers can immediately start writing code and also, they do not need to spend much time configuring the environment and managing different versions for diffrent libraries. For the communication with the external API, I used *Spring WebFlux* library, more specifically its' *WebClient* API which is a modern way to communicate between different API's and services. Another option was to use REST Template, but this way of communication with the API's is getting deprecated. For the database, I used H2 in-memory database since it's the easiest way to run and test the application. For the data layer, I used *Spring Data JPA* library which is build on top of the *Hibernate* ORM. It comes with support for JPA entities, and also it has **repositories** - Predefined interfaces which you can extend and then you get all the necessary CRUD functionalities  for entities out of the box.
### 2. How you tackled the task?
I spent some time reading and analysing all the requirements and constraints that were given in the assignment file. Then, I started planning the database structure and JPA entities based on the API and requirements from the task. After that, I created the repository and service layer of the application for all the necessary operations. Also, I thought about communicating with an external API, designed and implemented a method which would be responsible for that task. Finally, I exposed all the necessary routes for the task, using RestController.
### What you would do differently were you given more time
I would maybe consider protecting the API, or create a database in an enviroment such as MySQL or similar, but that's not really the time problem, because it is certainly possible but with the H2 database it is much easier to run application and database without much effort and without any problems. If the requirements would extend and application would grow, then it would be mandatory to switch to some reliable database engine (SQL or NoSQL depending on the project requirements and design).
