package com.creatubbles.api.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static final String IMAGE_JPEG_CONTENT_TYPE = "image/jpeg";

    public static int uploadObject(byte[] data, String url, String  contentType) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(HttpMethod.PUT.name());
        connection.setRequestProperty("Content-Type", contentType);
        OutputStream out = connection.getOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
        in.close();
        out.close();
        return connection.getResponseCode();
    }

}
