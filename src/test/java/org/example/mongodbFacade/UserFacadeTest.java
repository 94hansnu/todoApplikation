package org.example.mongodbFacade;

import org.example.model.User;
import org.example.mongodbDatabase.UserDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserFacadeTest {

    @Mock
    private UserDB userDB;
    private UserFacade userFacade;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userFacade = new UserFacade(userDB);
    }

    /**
     * I createUser()-metoden skapas ett User-objekt och används som argument för createUser()-metoden i userFacade.
     * Mock-objektet userDB förväntas anropas exakt en gång med user som argument för save()-metoden.
     */
    @Test
    void createUser() {
        User user = new User("1", "Hanadi Snunu",28, new ArrayList<>());
        doNothing().when(userDB).save(user);
        userFacade.createUser(user);
        Mockito.verify(userDB, times(1)).save(user);
    }

    /**
     * I getUserById_existId()-metoden testas scenariot när ett User-objekt finns med den givna userId.
     * Mock-objektet userDB förväntas anropas exakt en gång med userId som argument och returnera expectedUser.
     * Det kontrolleras också om getUserById()-metoden i userFacade returnerar samma User-objekt som förväntat.
     */
    @Test
    void getUserById_existId() {
        String userId = "1";
        User expectedUser = new User(userId, "Hanadi Snunu", 28, new ArrayList<>());

        when(userDB.findById(userId)).thenReturn(expectedUser);

        User resultUser = userFacade.getUserById(userId);

        assertEquals(expectedUser, resultUser);
    }

    /**
     * I getUserById_notExistId()-metoden testas scenariot när inget User-objekt finns med den givna userId.
     * Mock-objektet userDB förväntas anropas exakt en gång med userId som argument och returnera null.
     * Det kontrolleras också om getUserById()-metoden i userFacade returnerar null.
     */
    @Test
    void getUserById_notExistId(){
        String userId = "1";

        when(userDB.findById(userId)).thenReturn(null);

        User resultUser = userFacade.getUserById(userId);

        verify(userDB, times(1)).findById(userId);

        assertNull(resultUser);
    }

    /**
     * I getAllUsers()-metoden förväntas findAll()-metoden på mock-objektet userDB anropas exakt en gång och returnera
     * expectedUsers. Det kontrolleras också om getAllUsers()-metoden i userFacade returnerar samma lista med
     * User-objekt som förväntat.
     */
    @Test
    void getAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("1", "Hanadi Snunu", 28, new ArrayList<>()));
        expectedUsers.add(new User("2", "Kelly Andersson", 24, new ArrayList<>()));

        when(userDB.findAll()).thenReturn(expectedUsers);

        List<User> resultUser = userFacade.getAllUsers();

        assertEquals(expectedUsers, resultUser);
    }

    /**
     * I updateUser()-metoden förväntas update()-metoden på mock-objektet userDB anropas exakt en gång
     * med user som argument.
     */
    @Test
    void updateUser() {
        User user = new User("1", "Hanadi Snunu", 28, new ArrayList<>());

        doNothing().when(userDB).update(user);

        userFacade.updateUser(user);

        verify(userDB, times(1)).update(user);
    }

    /**
     * I updateUserName()-metoden förväntas findById()-metoden på mock-objektet userDB anropas exakt en gång med userId
     * som argument och returnera user. Sedan uppdateras namnet för user och update()-metoden på userDB anropas också
     * exakt en gång med den uppdaterade User-instansen. Det kontrolleras också om namnet i updatedUser har
     * uppdaterats korrekt.
     */
    @Test
    void updateUserName() {
        String userId = "1";
        String newName = "Marcus";
        User user = new User(userId, "Hanadi Snunu", 28, new ArrayList<>());

        when(userDB.findById(userId)).thenReturn(user);

        User updatedUser = userFacade.updateUserName(userId, newName);

        verify(userDB, times(1)).findById(userId);

        assertEquals(newName, updatedUser.getName());

        verify(userDB, times(1)).update(updatedUser);
    }

    /**
     * I deleteUser()-metoden förväntas delete()-metoden på mock-objektet userDB anropas exakt en gång med userId
     * som argument.
     */
    @Test
    void deleteUser() {
        String userId = "1";

        doNothing().when(userDB).delete(userId);

        userFacade.deleteUser(userId);

        verify(userDB, times(1)).delete(userId);
    }
}