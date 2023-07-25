package com.ClashStatsAPI;


import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import spark.Route;
import static spark.Spark.port;
import static spark.Spark.get;

public class Server {
    private static final String playerInfo = "/playerinfo";
    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjIwY2RmZDA1LWNmMzgtNDZlOS1hNWY2LTM3OTRjZDkxZThkOCIsImlhdCI6MTY5MDMwMDQ3Niwic3ViIjoiZGV2ZWxvcGVyLzViMTc3NTZlLWQ5ZmMtOTVhNy0zMGMyLTA1YWZlNjc3YTU0MCIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIxMDkuODkuMjI4LjE0Il0sInR5cGUiOiJjbGllbnQifV19.kprNY3zkOu-lx4yCMSR0yfSEFYc_Fn2dspHbeHX0Lpl_f_gdggsneNj8CMZ9E3aXJQ4VlWbhqr1yHivN1ofpew";
    private static final String baseUrl = "https://api.clashroyale.com/v1";

    public static void main(String[] args) {
        port(8080);

        Route playerInfoRoute = (req, res) -> {
            String id = "%232RC2C9Q2"; //req.queryParams("id");

            String url = baseUrl + "/players/" + id; // Use a local variable for playerUrl

            URIBuilder uriBuilder = new URIBuilder(url); // Build URI using playerUrl

            URI uri = uriBuilder.build();

            HttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(uri);

            httpGet.setHeader("Authorization", "Bearer " + token);

            HttpResponse response = httpClient.execute(httpGet);

            int status = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();

            return EntityUtils.toString(responseEntity, ContentType.getOrDefault(responseEntity).getCharset());
        };

        get(playerInfo, playerInfoRoute);
    }
}
