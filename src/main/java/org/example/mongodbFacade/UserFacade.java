package org.example.mongodbFacade;

import org.example.model.User;
import org.example.mongodbDatabase.UserDB;

import java.util.List;

public class UserFacade {
    UserDB userDB;

    public UserFacade(UserDB userDB){
        this.userDB= userDB;
    }

    public void createUser(User user){
        userDB.save(user);
    }
    public User getUserById(String id){
        return userDB.findById(id);
    }
    public List<User> getAllUsers(){
        return userDB.findAll();
    }
    public void updateUser(User user){
        userDB.update(user);
    }
    public User updateUserName(String id, String newName){
        User user = userDB.findById(id);
        if (user != null){
            user.setName(newName);
            userDB.update(user);
        }
        return user;
    }
    public void deleteUser(String id){
        userDB.delete(id);
    }
}
