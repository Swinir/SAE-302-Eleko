package Input;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class OAuth2API {

    private String accessToken;
    private String base64;


    public OAuth2API(String base64) {
        this.base64 = base64;
    }

    public void getAccessToken() throws IOException {
        // Build the credentials for basic authentication

        // Make a request to the token endpoint
        URL url = new URL("https://digital.iservices.rte-france.com/token/oauth2");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + base64);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Send the request
        String data = "grant_type=client_credentials";
        connection.getOutputStream().write(data.getBytes());

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Extract the access token from the response
        //You should use json library to extract the access_token
        JSONObject jsonObject = new JSONObject(response.toString());
        accessToken = jsonObject.getString("access_token");

    }

    public String getData(String endpoint) throws IOException {
        // Make a request to the endpoint
        URL url = new URL("https://digital.iservices.rte-france.com/open_api/ecowatt/v4" + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}

