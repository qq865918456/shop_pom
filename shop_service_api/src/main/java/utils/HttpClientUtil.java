package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {

    public static String sendJsonPost(String url,String json){

        CloseableHttpClient httpClient = null;
        try {
            //1,获取http对象
            httpClient = HttpClientBuilder.create().build();

            //2,指定响应连接设置请求方式 post或者get
            HttpPost httpPost = new HttpPost(url);
            //请求头
            httpPost.setHeader(new BasicHeader("Content-Type","application/json"));
            //请求体
            httpPost.setEntity(new StringEntity(json,"utf-8"));

            //3,发送post请求
            CloseableHttpResponse response = httpClient.execute(httpPost);

            //4,获得响应体
            HttpEntity entity = response.getEntity();
            //将相应体转换为字符串
            String toString = EntityUtils.toString(entity);

            return toString;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
