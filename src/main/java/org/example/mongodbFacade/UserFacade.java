package org.example.mongodbFacade;

import org.example.model.User;
import org.example.mongodbDatabase.UserDB;

import java.util.List;

/**
 * Klassen fungerar som ett gränssnitt mellan applikationens logik och databaslagret (UserDB). Den har en referens till
 * en instans av UserDB-klassen och används för att utföra operationer relaterade till User-objekt.
 */
public class UserFacade {
    UserDB userDB;

    public UserFacade(UserDB userDB){
        this.userDB= userDB;
    }

    /**
     * Metoden används för att skapa en ny användare (User). Den tar emot ett User-objekt och använder UserDB-objektet
     * för att spara det i databasen genom att anropa save-metoden i UserDB.
     * @param user
     */
    public void createUser(User user){
        userDB.save(user);
    }

    /**
     * Metoden används för att hämta en användare (User) från databasen baserat på dess ID. Den tar emot ett ID som
     * parameter och använder UserDB-objektet för att söka efter den matchande användaren genom att anropa
     * findById-metoden i UserDB. Returnerar den hittade användaren.
     * @param id
     * @return
     */
    public User getUserById(String id){
        return userDB.findById(id);
    }

    /**
     * Metoden används för att hämta alla användare (User) från databasen. Den använder UserDB-objektet för att hämta en
     * lista av alla tillgängliga användare genom att anropa findAll-metoden i UserDB. Returnerar en lista av User-objekt.
     * @return
     */
    public List<User> getAllUsers(){
        return userDB.findAll();
    }

    /**
     * Metoden används för att uppdatera en befintlig användare (User). Den tar emot ett User-objekt som parameter och
     * använder UserDB-objektet för att utföra uppdateringen genom att anropa update-metoden i UserDB.
     * @param user
     */
    public void updateUser(User user){
        userDB.update(user);
    }

    /**
     * Metoden används för att uppdatera namnet på en befintlig användare (User). Den tar emot ett ID och ett nytt namn
     * som parameter. Den använder UserDB-objektet för att hämta den befintliga användaren baserat på ID genom att
     * anropa findById-metoden i UserDB. Om användaren hittas, uppdateras namnet med det nya namnet och UserDB-objektet
     * används för att utföra uppdateringen genom att anropa update-metoden i UserDB. Returnerar den uppdaterade användaren.
     * @param id
     * @param newName
     * @return
     */
    public User updateUserName(String id, String newName){
        User user = userDB.findById(id);
        if (user != null){
            user.setName(newName);
            userDB.update(user);
        }
        return user;
    }

    /**
     * Metoden används för att ta bort en användare från databasen baserat på dess ID. Den tar emot ett ID som parameter
     * och använder UserDB-objektet för att radera den matchande användaren genom att anropa delete-metoden i UserDB.
     * @param id
     */
    public void deleteUser(String id){
        userDB.delete(id);
    }
}
