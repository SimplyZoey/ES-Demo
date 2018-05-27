package com.rocky.es.biz;

import com.rocky.es.utils.ESUtil;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

import java.util.Date;

/**
 * @Author: shtian
 * @Description:
 * @Date: Create in 2018/5/27 20:18
 */
public class OperatorES {

    public String addIndex() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("name", "hexu").field
                ("postDate", new Date()).field("gender", "male").endObject();
        Client client = ESUtil.getClient();
        IndexResponse response = client.prepareIndex("study", "person").setSource(builder).get();
        return response.getId();
    }

    public String getIndex(String id) throws Exception {
        Client client = ESUtil.getClient();
        GetResponse response = client.prepareGet("study", "person", id).get();
        return response.toString();
    }

    public String deleteIndex(String id) throws Exception {
        Client client = ESUtil.getClient();
        DeleteResponse response = client.prepareDelete("study", "person", id).get();
        return response.toString();
    }

    public long deleteByQuery() throws Exception {
        Client client = ESUtil.getClient();
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))//source节点，过滤出gender为male
                .source("study")//index
                .get();
        return response.getDeleted();
    }

    public void deleteByQueryWithListener() throws Exception {
        Client client = ESUtil.getClient();
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("gender", "male"))
                .source("study1")
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse response) {
                        response.getDeleted();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    public String updateIndex(String id) throws Exception{
        Client client = ESUtil.getClient();
        //source对应的属性不存在则新增
        UpdateRequest request = new UpdateRequest("study", "person", id).doc(XContentFactory.jsonBuilder()
                .startObject().field("name1", "shitian100").endObject());
        UpdateResponse response = client.update(request).get();
        return response.toString();
    }

}
