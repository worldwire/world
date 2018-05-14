package com.spring.worldwire.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpReqUtil {

	private static Logger log = LoggerFactory.getLogger(HttpReqUtil.class);

	public static String reqHttpClient(String path, String param) throws Exception {
		String results = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			// 目标地址
			HttpPost httppost = new HttpPost(path);
			log.info("request: " + httppost.getRequestLine());
			// 构造最简单的字符串数据
			StringEntity reqEntity = new StringEntity(param, "UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			log.info("param :" + param);
			// 执行
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			// log.info(response.getStatusLine());
			if (entity != null) {
				log.info("Response content length: "
						+ entity.getContentLength());
			}
			// 显示结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				results = line;
			}
			reader.close();
			log.info("results----:" + results);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return results;
	}

	public static String reqHttpJson(String path) throws Exception{
		String results = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(path);
		try{
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			response = httpclient.execute(httpPost);
			if(response!=null&&response.getStatusLine().getStatusCode()==200){
				results = EntityUtils.toString(response.getEntity());
			}
		}catch (Exception e) {
			httpPost.abort();
			throw new Exception(e);
		}finally {
			try{
				if (response != null) {
					response.close();
				}
			} catch (Exception e){
				log.error("发送失败 exception",e);
			}
			try{
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (Exception e){
				log.error("发送失败 exception",e);
			}
		}
		return results;
	}

	/**
	 * 发送Http get请求
	 *
	 */
	public static String sendHttpGetRequest(String reURL,Map<String,String> headers) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resStr = "";
		try {
			HttpGet httpget = new HttpGet(reURL);
			if(!CollectionUtils.isEmpty(headers)){
				for (Map.Entry<String, String> entry: headers.entrySet()) {
					httpget.setHeader(entry.getKey(),entry.getValue());
				}
			}
			response = httpclient.execute(httpget);
			log.info("", response.getStatusLine());

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				log.info("返回数据长度: " + entity.getContentLength());
				resStr = EntityUtils.toString(entity);
				log.info("resStr:  " + resStr);
			}
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return resStr;
	}

}
