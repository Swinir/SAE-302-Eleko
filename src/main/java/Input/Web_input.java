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
        URLConnection c;
        URL u;

        c= null;
        u= null;

        try {
            u= new URL(serveur + "?" + s);
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
            in= new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            while ((inpuline= in.readLine()) != null) {
                texte.append(inpuline + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texte.toString();
    }

}
