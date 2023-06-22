package org.example.helper;

import org.example.mongodbDatabase.TodoDB;
import org.example.mongodbDatabase.UserDB;
import org.example.mongodbFacade.TodoFacade;
import org.example.mongodbFacade.UserFacade;

public class Main {
    public static void main(String[] args) {

        UserDB userDB = new UserDB();
        TodoDB todoDB = new TodoDB();

        UserFacade userFacade = new UserFacade(userDB);
        TodoFacade todoFacade = new TodoFacade(todoDB);

        Menu menu = new Menu(userFacade, todoFacade);
        menu.displayMenu();
    }
}