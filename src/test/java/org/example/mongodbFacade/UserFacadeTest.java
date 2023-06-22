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

    @Test
    void createUser() {
        User user = new User("1", "Hanadi Snunu",28, new ArrayList<>());
        doNothing().when(userDB).save(user);
        userFacade.createUser(user);
        Mockito.verify(userDB, times(1)).save(user);
    }

    @Test
    void getUserById_existId() {
        String userId = "1";
        User expectedUser = new User(userId, "Hanadi Snunu", 28, new ArrayList<>());

        when(userDB.findById(userId)).thenReturn(expectedUser);

        User resultUser = userFacade.getUserById(userId);

        assertEquals(expectedUser, resultUser);
    }

    @Test
    void getUserById_notExistId(){
        String userId = "1";

        when(userDB.findById(userId)).thenReturn(null);

        User resultUser = userFacade.getUserById(userId);

        verify(userDB, times(1)).findById(userId);

        assertNull(resultUser);
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("1", "Hanadi Snunu", 28, new ArrayList<>()));
        expectedUsers.add(new User("2", "Kelly Andersson", 24, new ArrayList<>()));

        when(userDB.findAll()).thenReturn(expectedUsers);

        List<User> resultUser = userFacade.getAllUsers();

        assertEquals(expectedUsers, resultUser);
    }

    @Test
    void updateUser() {
        User user = new User("1", "Hanadi Snunu", 28, new ArrayList<>());

        doNothing().when(userDB).update(user);

        userFacade.updateUser(user);

        verify(userDB, times(1)).update(user);
    }

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

    @Test
    void deleteUser() {
        String userId = "1";

        doNothing().when(userDB).delete(userId);

        userFacade.deleteUser(userId);

        verify(userDB, times(1)).delete(userId);
    }
}