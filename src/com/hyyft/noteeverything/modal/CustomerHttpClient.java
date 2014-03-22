package com.hyyft.noteeverything.modal;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
public class CustomerHttpClient {
    private static final String CHARSET = "HTTP.UTF_8";
    private static HttpClient customerHttpClient;

    private CustomerHttpClient() {
    }

    public static synchronized HttpClient getHttpClient() {
        if (null== customerHttpClient) {
            HttpParams params =new BasicHttpParams();
            // ����һЩ��������
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params,
                    CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);
            HttpProtocolParams
                    .setUserAgent(
                            params,
                            "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                                    +"AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
            // ��ʱ����
/* �����ӳ���ȡ���ӵĳ�ʱʱ�� */
            ConnManagerParams.setTimeout(params, 1000);
            /* ���ӳ�ʱ */
            HttpConnectionParams.setConnectionTimeout(params, 1500);
            /* ����ʱ */
            HttpConnectionParams.setSoTimeout(params, 3000);
            
            // �������ǵ�HttpClient֧��HTTP��HTTPS����ģʽ
            SchemeRegistry schReg =new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 80));
            schReg.register(new Scheme("https", org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory(), 443));

            // ʹ���̰߳�ȫ�����ӹ���������HttpClient
            ClientConnectionManager conMgr =new ThreadSafeClientConnManager(
                    params, schReg);
            customerHttpClient =new DefaultHttpClient(conMgr, params);
        }
        return customerHttpClient;
    }
}
