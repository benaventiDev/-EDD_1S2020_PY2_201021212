package gui;

import edd.HashTable;
import edd.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.exit;

public class Window extends javax.swing.JFrame {
private HashTable userList;

    public Window(){
        super("EDD Projecto 2");
        setSize(1500, 800);
        setVisible(true);
        userList = new HashTable();

        //TODO Buscar userlist if existsto add to the current hash
        showWelcomeOptions(this);


    }


    private void showWelcomeOptions(JFrame frame){
        String[] options = {"Log in", "Load Users", "Exit"};
        int option = JOptionPane.showOptionDialog(this, "Log In or load Users",
                "Acceder a libreria",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);


        switch (option){
            case 0:
                //String m = JOptionPane.showInputDialog("Anyone there?", "");
                //Look for user in database
                String m = JOptionPane.showInputDialog(null, "Usuario",
                        "Log In", JOptionPane.INFORMATION_MESSAGE);
                //Buscar si M existe si si, pedir password
                System.out.println(m);
                //TODO
                //showLogin(this);
                break;
            case 1:
                showFileOpenDialog();
                //TODO if file is valid load and show success message and load  welcome options again
                break;
            case 2:
                System.out.println("3");
                exit(0);
                break;
        }
    }

    private boolean showFileOpenDialog(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File usersFile = fileChooser.getSelectedFile();
            try (FileReader reader = new FileReader(usersFile.getAbsolutePath()))
            {

                JSONParser jsonParser = new JSONParser();
                Object obj = null;
                obj = jsonParser.parse(reader);
                userList.add(obj);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private void showLogin(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("User Name", SwingConstants.RIGHT));
        labels.add(new JLabel("Password", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        JTextField username = new JTextField("");
        username.addAncestorListener(new RequestFocusListener(false));
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        p.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog( frame, p, "Log In", JOptionPane.QUESTION_MESSAGE);


    }






    class RequestFocusListener implements AncestorListener
    {
        private boolean removeListener;

        /*
         *  Convenience constructor. The listener is only used once and then it is
         *  removed from the component.
         */
        public RequestFocusListener()
        {
            this(true);
        }

        /*
         *  Constructor that controls whether this listen can be used once or
         *  multiple times.
         *
         *  @param removeListener when true this listener is only invoked once
         *                        otherwise it can be invoked multiple times.
         */
        public RequestFocusListener(boolean removeListener)
        {
            this.removeListener = removeListener;
        }

        @Override
        public void ancestorAdded(AncestorEvent e)
        {
            JComponent component = e.getComponent();
            component.requestFocusInWindow();

            if (removeListener)
                component.removeAncestorListener( this );
        }

        @Override
        public void ancestorMoved(AncestorEvent e) {}

        @Override
        public void ancestorRemoved(AncestorEvent e) {}
    }

}
