## Compile and Deploy ##

To compile to an exploded `war` with Maven, first update
`<directory>C:\tomcat\webapps</directory>` on `line 17` of
`/pom.xml` to the `webapps` directory of your local server.
Then, you can compile directly to that folder by running:

`mvn compile war:exploded -Ptarget`


## REST API ##
The API supports the following requests:

* `/all` : fetch all users in the database
* `/{id}` : get a single user's JSON object by `{id}` (returns status `400` if the given ID does not exist in the database)
*  `/post` : consumes `application/json` with the structure `{firstName: ..., lastName: ...}`, generates a new entry in the database, and returns that entry
(returns status `400` if the body does not have valid format) 

## Structure ##

`src/main/java/` contains the main package, `com.daniil.newtonx`. Classes as follows:

*  `app.UserDatabaseApplication` : main application extending `javax.ws.rs.core.Application`.
*  `rest.UserDatabaseService` : response methods for the API paths above.
*  `api.UserInfo` : schema class for this particular database
*  `api.RequestHandler` : singleton class responsible for handling requests to the API and generating
unique IDs for new entries. 
