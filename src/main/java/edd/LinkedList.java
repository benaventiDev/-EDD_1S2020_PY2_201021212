package edd;

import org.json.simple.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


public class LinkedList {
    private User first;
    private User last;
    private User auxUser;
    private User backupUser;
    private int size;

    public LinkedList(){
        auxUser = new User(-1, "", "", "", "");
        backupUser = auxUser;

    }

    public void addJsonUsers(JSONObject obj){

        addUser(new User( (long) obj.get("Carnet"), (String) obj.get("Nombre"), (String) obj.get("Apellido"), (String) obj.get("Carrera"), (String) obj.get("Password") ));

    }

    public boolean addUser(User user){
        if(userExists(user)){return false;}
        if(first == null){
            size++;
            last = first = user;
        }else {
            size++;
            last.setNextUser(user);
            last = user;
        }
        return true;
    }

    public boolean userExists(User user){
        begin();
        while (hasNext()){
            if(next().carnet == user.carnet){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(long  carnet){
        begin();
        while (hasNext()){
            if(next().carnet == carnet){
                return true;
            }
        }
        return false;
    }

    public void begin(){
        auxUser = backupUser;
        auxUser.setNextUser( first);
    }

    public boolean hasNext(){
        //if(auxUser == null){
          //  return false;
        //}else {
            auxUser = auxUser.getNextUser();
            if(auxUser == null){return false;}
        //}
        return true;
    }

    public void printLast(){
        System.out.println(last.carnet);
    }

    public User next(){
        return auxUser;
    }

    public boolean deleteUser(long carnet){
        if (!userExists(carnet)){
            return false;}
        if (carnet == first.carnet){
            if(first.getNextUser() == null){
                last = first = null;
            }else {
                first = first.getNextUser();
            }
        }

        User temp = first;
        begin();

        while (hasNext()){
            if(next().carnet == carnet){
                    temp.setNextUser(next().getNextUser());
                if(next() == last){last = temp;}
                    break;
            }
            temp = next();
        }
        size--;
        return true;
    }

    public void printGraph(){
        //TODO
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public static final class User{
        private long carnet;
        private String name;
        private String lastName;
        private String career;
        private String password;
        private User nextUser;

        public User(long carnet, String name, String lastName, String career, String password){
            this.setCarnet(carnet);
            this.setName(name);
            this.setLastName(lastName);
            this.setCareer(career);
            this.setPassword(password);
        }


        public long getCarnet() {
            return carnet;
        }

        public void setCarnet(long carnet) {
            this.carnet = carnet;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCareer() {
            return career;
        }

        public void setCareer(String career) {
            this.career = career;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = getMd5(password) ;
        }

        public static String getMd5(String input) {
            try {

                // Static getInstance method is called with hashing MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // digest() method is called to calculate message digest
                //  of an input digest() return array of byte
                byte[] messageDigest = md.digest(input.getBytes());

                // Convert byte array into signum representation
                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }

            // For specifying wrong message digest algorithms
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }



            public User getNextUser() {
            return nextUser;
        }

        public void setNextUser(User nextUser) {
            this.nextUser = nextUser;
        }

        @Override
        public String toString() {
            return "Carnet: " + carnet  + " Nombre: " + name + " Last Name" + lastName + ". pass " + password;
        }
    }




}
