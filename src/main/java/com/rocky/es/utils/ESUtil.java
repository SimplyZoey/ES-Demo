package com.rocky.es.utils;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

/**
 * @Author: shtian
 * @Description:
 * @Date: Create in 2018/5/27 20:30
 */
public class ESUtil {

    public static Client getClient() {
        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/es.properties");
        Properties properties = new Properties();
        Client client = null;
        try {
            properties.load(inputStream);
            Settings settings = Settings.builder().put("cluster.name", properties.getProperty("cluster.name")).put
                    ("client.transport.sniff", properties.getProperty("client.transport.sniff")).build();
            client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress
                    .getByName(properties.getProperty("es.host")), 9300));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}
