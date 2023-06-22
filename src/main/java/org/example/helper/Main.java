package org.example.helper;

import org.example.mongodbDatabase.TodoDB;
import org.example.mongodbDatabase.UserDB;
import org.example.mongodbFacade.TodoFacade;
import org.example.mongodbFacade.UserFacade;

public class Main {
    public static void main(String[] args) {

        TodoDB todoDB = new TodoDB();
        UserDB userDB = new UserDB();


        UserFacade userFacade = new UserFacade(userDB);
        TodoFacade todoFacade = new TodoFacade(todoDB);

        Menu menu = new Menu(userFacade, todoFacade);
        menu.displayMenu();
    }
}