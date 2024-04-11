package com.example.application.controller;

import com.example.application.models.Movie;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MovieController {


    private List<Movie> movieList;
    private MongoCollection<Document> movieCollection;

    @Autowired
    public MovieController(MongoClient mongoClient) {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            movieCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("users");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }

    private Movie toMovie(Document doc){
        System.out.println(doc.getString("title")+ " "
                 + doc.getInteger("releaseYear") + " "
                 + doc.getString("genre") + " "
                 + doc.getString("director")+ " "
                 + doc.getDouble("averageRating"));
        return new Movie(
                doc.getString("title"),
                doc.getInteger("releaseYear"),
                doc.getString("genre"),
                doc.getString("director"),
                doc.getDouble("averageRating")
        );
    }

    private Document toDocument(Movie movie) {
        return new Document("title", movie.getTitle())
                .append("releaseYear", movie.getReleaseYear())
                .append("genre", movie.getGenre())
                .append("director", movie.getDirector())
                .append("averageRating", movie.getAverageRating());
    }

    public List<Movie> getAllMovies() {
        // Return the list of all movies
        movieList = movieCollection.find().map(this::toMovie).into(new ArrayList<>());
        return movieList;
    }
    public void rateMovie(int id, double rating) {
        // Update the rating of the movie with the specified ID
        Bson filter = Filters.eq("_id", id);
        Bson update = Updates.set("averageRating", rating);
        movieCollection.updateOne(filter, update);
    }
    // Other controller methods for CRUD operations (Create, Update, Delete) can be

    public boolean checkUser(String checkUsername, String checkPassword) {
        Bson filter = Filters.and(
                Filters.eq("username",checkUsername),
                Filters.eq("password",checkPassword)
        );
        Document findUser = movieCollection.find(filter).first();
        if(findUser==null)
            return false;
        return true;
    }

    public void addMovie(Movie movie){
        Document document = toDocument(movie);
        Random random = new Random();
        int nextID = random.nextInt(1000);
        document.append("_id", nextID);
        movieCollection.insertOne(document);
    }

    public boolean updateMovie(Movie updatedMovie){
        String movieTitle = updatedMovie.getTitle();
        Bson filter = Filters.eq("title",movieTitle);
        Document updatedDocument = toDocument(updatedMovie);
        UpdateResult result = movieCollection.replaceOne(filter,updatedDocument);
        if(result.getModifiedCount()==0)
            return false;
        return true;
    }

    public boolean deleteMovie(String movieTitle, Integer movieYear) {
        Bson filter = Filters.and(
                Filters.eq("title",movieTitle),
                Filters.eq("releaseYear",movieYear)
        );
        Document movieToDelete = movieCollection.find(filter).first();
        if(movieToDelete==null)
            return false;
        movieCollection.deleteOne(filter);
        return true;
    }


    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}