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
        URL url = new URL("https://digital.iservices.rte-france.com/token/oauth2"); // URL of the token endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open the connection
        connection.setRequestMethod("POST"); // POST is used to send data
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + base64); // Set the authorization header
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // Set the content type header

        // Send the request
        String data = "grant_type=client_credentials"; // The data to send
        connection.getOutputStream().write(data.getBytes()); // Write the data to the request body

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine); // Read the response body
        }
        in.close(); // Close the reader

        // Extract the access token from the response
        //You should use json library to extract the access_token
        JSONObject jsonObject = new JSONObject(response.toString()); // Convert the response body to a JSON object
        accessToken = jsonObject.getString("access_token"); // Extract the access token from the JSON object

    }

    public String getData(String endpoint) throws IOException {
        // Make a request to the endpoint
        URL url = new URL("https://digital.iservices.rte-france.com/open_api/ecowatt/v4" + endpoint); // URL of the endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open the connection
        connection.setRequestMethod("GET"); // GET is used to request data
        connection.setRequestProperty("Authorization", "Bearer " + accessToken); // Set the authorization header

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Read the response
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine); // Read the response body
        }
        in.close(); // Close the reader

        return response.toString();
    }
}

