import edd.LinkedList;
import gui.Window;
import edd.tree.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JFrame;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
Add this to the manifest main-class: Main

*/

public class Main {

    public static void main(String[] args) {
        Window application = new Window();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //application.runServer();
        /*
        JSONParser jsonParser = new JSONParser();
        LinkedList users = new LinkedList();
        try (FileReader reader = new FileReader("C:\\Users\\benav\\Downloads\\usuarios.json"))
        {
            //Read JSON file
            Object obj = null;
            obj = jsonParser.parse(reader);
            JSONObject usersObj = (JSONObject) obj;
            JSONArray arr = (JSONArray) usersObj.get("Usuarios"); // check that users is not null
            //Iterate over employee array

            arr.forEach( user -> users.addJsonUsers( (JSONObject) user   ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        users.deleteUser(202005878);
        //users.deleteUser(201503477);
        users.begin();
        while (users.hasNext()){
            System.out.println(users.next().toString());
        }

        users.printLast();

*/








        /*

        //BOOKS
        JSONParser jsonParser = new JSONParser();
        ArrayList<Book> books = new ArrayList<>();
        try (FileReader reader = new FileReader("C:\\Users\\benav\\Downloads\\libros.json"))
        {
            //Read JSON file
            Object obj = null;
            obj = jsonParser.parse(reader);
            JSONArray booksList = (JSONArray) obj;

            //Iterate over employee array
            booksList.forEach( emp -> books.add( new Book( (JSONObject) emp, "dddd" ) ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        books.forEach(book -> System.out.println(book.toString()));*/


    }
}
