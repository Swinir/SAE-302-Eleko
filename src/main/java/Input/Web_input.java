package Input;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Web_input {
    //This class is used to get the input data from the web

    private String serveur;

    public Web_input (String serveur) {
        this.serveur= serveur;
    }

    public URLConnection ouvrirURL (String s) {
        URLConnection c; //This is the connection to the web
        URL u;

        c= null;
        u= null;

        try {
            u= new URL(serveur + "?" + s); //This is the URL of the web page
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            c= u.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    public String lireURL (String s) {
        BufferedReader in;
        String inpuline;
        StringBuilder texte;
        URLConnection c;

        c= ouvrirURL(s);

        texte= new StringBuilder();
        try {
            in= new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8")); //This is the input stream
            while ((inpuline= in.readLine()) != null) {
                texte.append(inpuline + "\n"); //This is the input text
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texte.toString();
    }

}
