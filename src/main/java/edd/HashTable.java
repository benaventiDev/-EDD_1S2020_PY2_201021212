package edd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HashTable {
    private LinkedList[] lists;

    public HashTable(){
        lists = new LinkedList[45];
        for (int i = 0; i < lists.length; i++){
            lists[i] = new LinkedList();
        }
    }



    public void add(Object obj){

        JSONObject usersObj = (JSONObject) obj;
        JSONArray arr = (JSONArray) usersObj.get("Usuarios"); // check that users is not null
        arr.forEach( user ->  add((JSONObject) user) );
    }


    public void add(JSONObject obj){

        add(new LinkedList.User( (long) obj.get("Carnet"), (String) obj.get("Nombre"), (String) obj.get("Apellido"), (String) obj.get("Carrera"), (String) obj.get("Password") ));

    }


    public boolean add(LinkedList.User user){
        if(userExists(user.getCarnet())){
            return false;
        }
        int index = (int) user.getCarnet() % 45;
        return lists[index].addUser(user);
    }

    public void printUsers(){
        for(int i =0; i< lists.length; i++){
            lists[i].begin();
            while (lists[i].hasNext()){
                System.out.println("Hash index: " + i + lists[i].next().toString());
            }

        }
    }

    public boolean userExists(long carnet){
        int index = (int) carnet % 45;
        if(lists[index].getSize() == 0){
            return false;
        }
        if(lists[index].userExists(carnet)){
            return true;
        }else {
            return false;
        }
    }

}
