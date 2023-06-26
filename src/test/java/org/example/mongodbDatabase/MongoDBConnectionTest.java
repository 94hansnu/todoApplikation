package org.example.mongodbDatabase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

class MongoDBConnectionTest {
    @Mock
    private MongoClient mockMongoClient;
    @Mock
    private MongoDatabase mockMongoDatabase;
    private MongoDBConnection mongoDBConnection;

    /**
     * Metoden körs före varje enskild testmetod och skapar instanser av mockobjekten och MongoDBConnection klassen
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mongoDBConnection = new MongoDBConnection();
        mongoDBConnection.mongoClient = mockMongoClient;
        mongoDBConnection.database = mockMongoDatabase;
    }

    /**
     * metoden körs efter varje enskild testmetod och anropar close()-metoden i MongoDBConnection-klassen för att stänga
     * anslutningen.
     */
    @AfterEach
    void tearDown() {
        mongoDBConnection.close();
    }

    /**
     * I metoden testas getDatabase()-metoden och jämför om den returnerade MongoDatabase-instansen är densamma som
     * mock-objektet mockMongoDatabase.
     */
    @Test
    void getInstance() {
        MongoDatabase database = mongoDBConnection.getDatabase();
        assertEquals(mockMongoDatabase, database);
    }

    /**
     * I metoden testas getDatabase()-metoden igen och jämför också här om den returnerade MongoDatabase-instansen är
     * densamma som mock-objektet mockMongoDatabase.
     */
    @Test
    void getDatabase() {
        MongoDatabase database = mongoDBConnection.getDatabase();
        assertEquals(mockMongoDatabase, database);
    }

    /**
     * I metoden stängs anslutningen genom att anropa close()-metoden på mock-objektet mockMongoClient. Sedan verifieras
     * att close()-metoden har anropats exakt en gång med Mockito.verify().
     */
    @Test
    void close() {
        mongoDBConnection.close();
        Mockito.verify(mockMongoClient, times(1)).close();
    }
}