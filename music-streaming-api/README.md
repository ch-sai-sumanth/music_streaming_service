<center>
<h1> INSTAGRAM CLONE </h1>
</center>
<center>
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/Maven-3.0.6-brightgreen.svg" />
</a>
   <a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
  </a>
</center>
This project is a basic music streaming application that allows users to sign in, sign up, and manage their music. Additionally, users can create playlist and add songs in to playlist. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.


---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* User Model
```
userId : integer
firstName : string
lastName : string
email : string
password : string
phoneNumber : string
```

* Admin
```
adminId : Integer
adminName : String
adminEmail : String
adminPassword : String
```
* PlayList
```
playListId : Integer
playListName : String
mood : Mood(Enum)
user : User
song : List<Song>
```
* Song 
```
songId : Integer
title : String
artist : String
album : String
genre : String
duration : String
releaseDate : String
playlist : PlayList
```
* PlayedSongs
```
Id : Integer
user : User
playlist : PlayList
song : Song
```

## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points 

The following endpoints are available in the API:

* User Controller:
```
POST /user/signup: create a new user account
POST /user/signin: authenticate a user and receive an authentication token
PUT /updateUser: updates user details

POST /user/createPlayList: To create new playlist
DELETE/ user/deletePlaylist/{id}: To delete existing playlist
POST/addSongToPlaylist/{playlist}/{songID}: To add songs in to playlist
DELETE/user/deleteSongFromPlaylist/{playlist}/{songID}: To delete song from playlist

GET/user/getAllSongs: get all songs
GET/user/{songID}: get song by ID
```

* Admin Controller
```
POST/admin/add: To add Admin
```
* Song Controller
```
POST/song/add : To add song
PUT/song/update/{id}: To update song information using ID
DELETE/song/delete/{id}: To delete song using ID
GET/song/getAllSongs: To get all songs
GET/song/songID/{id}: To get song information using song ID
```
* Played-songs-Controller
```
POST/playedSongs/{playlistID}/{songID} : To post the songs that is playing by user
GET/playedSongs/userID/{userId}: To get the songs that are already played by user
```

<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary

The Music Streaming Service is a comprehensive platform that enables users to discover,and manage their playlist. The project incorporates various entities, including User, Admin, Song, Playlist, and PlayedSong, to cater to different user roles and functionalities.

The application provides a seamless authentication process, allowing users to sign up and sign in securely. Once authenticated, users are granted access to a range of features and operations tailored to their role.

For regular users, the system offers playlist management functionalities, empowering them to create, modify, and delete their personalized playlists. Users can add songs to their playlists, customize the order, and curate their own music collections.

Administrators, on the other hand, have privileged access and control over the Song entity. They can perform CRUD operations on songs, managing the library and ensuring a diverse and up-to-date music collection for users to enjoy.

The project leverages Spring Boot's capabilities, providing a robust and efficient framework for building the music streaming service. The integration with MySQL database ensures reliable and scalable data storage, accommodating the growing needs of the application.

Additionally, the project incorporates authentication mechanisms to protect user accounts and ensure secure access to the service. User sign-up and sign-in processes are implemented with appropriate safeguards to maintain data privacy and prevent unauthorized access.

Overall, the Music Streaming Service project combines the power of Spring Boot, MySQL, and thoughtful entity design to deliver a user-friendly and feature-rich music streaming experience, catering to the needs of both regular users and administrators.


## 📝 Licence
---
Copywrite © 2019 [sumanth]().


 
---
_This README was generated with ❤️  by [readme-md-generator]()_

