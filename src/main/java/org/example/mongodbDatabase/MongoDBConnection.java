package org.example.mongodbDatabase;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Denna klassen MongoDBConnection hanterar anslutningen till MongoDB-databasen
 */
public class MongoDBConnection {

    /**
     * Här definieras konstanter för anslutningsinformation till MongoFB-databasen.
     * DATABASE_HOST innehåller värdet för värdnamnet där databasen körs,
     * DATABASE_PORT anger portnumret för att ansluta till databasen och
     * DATABASE_NAME är namnet på databasen
     */
    private static final String DATABASE_HOST = "localhost";
    private static final int DATABASE_PORT = 27017;
    private static final String DATABASE_NAME= "todoApplikation";

    /**
     * Här deklareras privata instansvariabler, instance är en instansvariabel av typen MongoDBConnection
     * och används för att hålla en enda instans av anslutningsobjektet. mongoClient är en instansvariabel
     * av typen MongoClient som används för att hantera anslutningen till MongoDB-servern. database är en
     * instansvariabel av typen MongoDatabase som representerar själva databasen
     */
    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    /**
     * Detta är konstruktorn för klassen MongoDBConnection och skapar anslutningen till MongoDB-databasen
     * genom att skapa anslutningssträng baserad på namn och portnummer. Sedan skapas en instans av MongoClient
     * med hjälp av anslutningssträngen och instansen av MongoDatabase hämtas från klienten baserat på databasnamnet
     */
    private MongoDBConnection(){
        String connectionString = String.format("mongodb://%s:%d", DATABASE_HOST, DATABASE_PORT);
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    /**
     * Metod för att hämta den enda instansen av MongoDBConnection. Om det inte finns någon tidigare instans
     * skapas en ny instans och returneras.
     * @return
     */
    public static MongoDBConnection getInstance(){
        if (instance == null){
            instance = new MongoDBConnection();
        }
        return instance;
    }

    /**
     * Metod för att få tillgång till instansen av MongoDatabase som representerar databasen
     * @return
     */
    public MongoDatabase getDatabase(){
        return database;
    }

    /**
     * Metod som stänger anslutningen till MongoDB-server
     */

    public void close(){
        mongoClient.close();
    }
}
