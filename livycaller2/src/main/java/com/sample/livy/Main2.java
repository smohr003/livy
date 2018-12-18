package com.sample.livy;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.livy.shaded.apache.commons.codec.binary.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main2 {

    static private String clusterURL;
    static String userPassword;
    static String jarPath;

    public static void main(String[] args) throws Exception {
        assert args.length >= 2 : "Wrong Number of argument!!!\n Acceptable Format: ClusterURL user:password";
        System.out.println("Hello from caller App!");
        clusterURL = args[0];
        userPassword = args[1];
        jarPath = args [2];
        GetRequest();
    }
    //NOTE: the class has to be static
    @JsonDeserialize(as = SparkSessions.class)
    static public class SparkSessions {

        public int from;
        public int total;
        public List<Integer> sessions;
        // standard getters setters
        /*public SparkSessions() {
            super();
        }
        public SparkSessions(int from, int total, List<Integer> sessions) {
            this.from = from;
            this.total = total;
            this.sessions = sessions;
        }*/
    }

    public static void GetRequest() throws IOException {
        URL urlForGetRequest = new URL("https://".concat(clusterURL).concat("/livy/sessions"));
        String readLine = null;
        HttpsURLConnection connection = (HttpsURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");

        byte[] authEncBytes = Base64.encodeBase64(userPassword .getBytes());
        String authStringEnc = new String(authEncBytes);
        connection.setRequestProperty("Authorization", "Basic " + authStringEnc);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            System.out.println("JSON String Result " + response.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            SparkSessions s = objectMapper.readValue(response.toString(), SparkSessions.class);
            System.out.println(s.total);
            System.out.println(s.sessions);

        } else {
            System.out.println("GET NOT WORKED");
        }

    }



}
