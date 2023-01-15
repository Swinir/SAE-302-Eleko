package SAE302.eleko.Input;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Web extends AppCompatActivity {

    private EditText inputURL;
    private EditText output;
    private ExecutorService exe;
    private Future<String> todo;
    private Context context;

    public String onCreate(String s, Activity a) {
        context = a;

        URL u;

        try {
            u = new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur de récupéraiton des données sur internet1", Toast.LENGTH_LONG).show();
            u = null;
        }


        // On crée l'objet qui va gérer la thread
        exe = Executors.newSingleThreadExecutor();
        // On lance la thread
        todo = lireURL(u, context);
        // On attend le résultat
        try {
            s = todo.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur de récupéraiton des données sur internet2", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur de récupéraiton des données sur internet3", Toast.LENGTH_LONG).show();
        }
        // On retourne le résultat
        return s;
    }

    public Future<String> lireURL(URL u,Context context) {
        return exe.submit(() -> {
            URLConnection c;
            String inputline;
            StringBuilder codeHTML = new StringBuilder("");

            try {
                c = u.openConnection();
                //temps maximun alloué pour se connecter
                c.setConnectTimeout(60000);
                //temps maximun alloué pour lire
                c.setReadTimeout(10000);
                //flux de lecture avec l'encodage des caractères UTF-8
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(c.getInputStream(), "UTF-8"));
                while ((inputline = in.readLine()) != null) {
                    //concaténation+retour à la ligne avec \n
                    codeHTML.append(inputline + "\n");
                }
                //il faut bien fermer le flux de lecture
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Erreur de récupéraiton des données sur internet", Toast.LENGTH_LONG).show();
            }
            return codeHTML.toString();
        });
    }
}
