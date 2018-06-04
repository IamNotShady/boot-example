package com.github.boot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


public final class Tools {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String nvl(String v1, String v2) {
        return isNull(v1) ? v2 : v1;
    }


    public static boolean isNull(String v1) {
        return v1 == null || v1.trim().length() == 0;
    }

    public static boolean superNull(String v1) {
        return isNull(v1) || v1.equals("null") || v1.equals("undefined");
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String s_uuid = uuid.toString().replace("-", "");
        return s_uuid;
    }

    public static String object2json(Object obj) throws Exception {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }


    public static <T> T json2pojo(String jsonStr, Class<T> clazz)
        throws Exception {
        return OBJECT_MAPPER.readValue(jsonStr, clazz);
    }


    public static <T> T map2pojo(Map<?, ?> map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }


    public static String postData(HttpServletRequest request) {
        ServletInputStream inputStream = null;
        try {
            StringBuilder sPostData = new StringBuilder();
            byte[] bPostData = new byte[request.getContentLength()];
            int count;
            inputStream = request.getInputStream();
            while ((count = inputStream.read(bPostData)) > 0) {
                sPostData.append(new String(bPostData, 0, count));
            }
            return sPostData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
            }
        }
        return "";
    }


}
