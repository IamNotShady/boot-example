package com.wtyt.util.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * 常用工具类
 *
 * @author zxx
 *
 */
public final class Tools {
	/**
	 * This mapper (or, data binder, or codec) provides functionality for
	 * converting between Java objects (instances of JDK provided core classes,
	 * beans), and matching JSON constructs.
	 */
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 如果第一个字符串为空使用第二个字符串替换
	 *
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String nvl(String v1, String v2) {
		return isNull(v1) ? v2 : v1;
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param v1
	 * @return
	 */
	public static boolean isNull(String v1) {
		return v1 == null || v1.trim().length() == 0;
	}

	/**
	 * 判断NULL加强版，判断了字符串NULL和JS中的undefined
	 *
	 * @param v1
	 * @return
	 */
	public static boolean superNull(String v1) {
		return isNull(v1) || v1.equals("null") || v1.equals("undefined");
	}

	/**
	 * 获取UUID(去除短连接线后的)
	 *
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String s_uuid = uuid.toString().replace("-", "");
		return s_uuid;
	}

	/**
	 * javaBean,list,array convert to json string
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String object2json(Object obj) throws Exception {
		return OBJECT_MAPPER.writeValueAsString(obj);
	}

	/**
	 * json string convert to javaBean
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz)
			throws Exception {
		return OBJECT_MAPPER.readValue(jsonStr, clazz);
	}

	/**
	 * map convert to javaBean
	 *
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T map2pojo(Map<?, ?> map, Class<T> clazz) {
		return OBJECT_MAPPER.convertValue(map, clazz);
	}


	/**
	 * 获取请求中的POST字符串数据
	 *
	 * @param request
	 * @return
	 */
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
				// ignore
			}
		}
		return "";
	}



}
