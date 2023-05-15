
# Instagram basic design part 


This project is Instagram basic design part 1 built using Spring Boot with Java.



## Framework Used
* Spring Boot



## Language Used
* Java



## Data Model

The All ...ModelClass  is defined inside the model packages which has the following attributes:
   
   inside User Model:-<br>
   
private String firstName; <br>
private String lastName;<br>
private Integer age;<br>
private String email;<br>
private String phoneNumber;<br>
  
   
   
  Product Post:- 
   
  private Integer postId;<br>
private Timestamp createdDate;<br>
private Timestamp updatedDate;<br>
private String postData;<br>
  
 AuthenticationToken:- <br>
   
 private Long tokenId;<br>
private String token;<br>
private LocalDate tokenCreationDate;<br>

etc...





## Functions used :-

### API Endpoints :-
signUp

* PostMapping: /signIn 

This endpoint makes a call to method in userService class which is connected to database. In database check token given through API.


* GetMapping: 

This endpoint returns data of specific post based on id through API.


* PutMapping: 

This endpoint makes a call to method in userService class which is connected to database. In database we update user.







* PostMapping: 

This endpoint makes a call to method in PostService class which is connected to database. In database we add a new Post given through API.






## JpaRepository extended by Repository class...


We have used JpaRepository as a database to implement CRUD Operations.



## Project Summary

I have created Instagram basic design part 1 project.  In this project I have used @OneToOne maping and @manyToOne mapping. the user will have to do SignIn and SignUp first. than  Can save Post, update User.  can get post. etc...


