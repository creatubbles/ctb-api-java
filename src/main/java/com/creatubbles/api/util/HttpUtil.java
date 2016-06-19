package com.creatubbles.api.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class HttpUtil {

    private static final FileNameMap FILE_NAME_MAP = URLConnection.getFileNameMap();

    public static class Response {
        public String message;
        public int code;
    }

    public static final List<String> allowedFileTypes = Arrays.asList("png", "jpg", "jpeg", "h264", "mpeg4", "wmv", "webm", "flv", "ogg", "ogv", "mp4", "m4v", "f4v", "mov", "zip");

    public static Response uploadObject(byte[] data, String url, String mimeType) throws IOException {
        HttpURLConnection connection = null;
        Response ret = new Response();
        try {
            connection = (HttpURLConnection) (new URL(url).openConnection());
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(HttpMethod.PUT.name());
            connection.setRequestProperty("Content-Type", mimeType);
            OutputStream out = null;
            try {
                out = connection.getOutputStream();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }
        } finally {
            if (connection != null) {
                ret.code = connection.getResponseCode();
                ret.message = connection.getResponseMessage();
                connection.disconnect();
            }
        }
        return ret;
    }

    public static String getExtension(String path) {
        return path.substring(path.indexOf('.', path.lastIndexOf('/')) + 1).toLowerCase();
    }

}
