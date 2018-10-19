package com.psp.plugin.demo;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestRequest.Method;

import com.psp.handler.AbstractElasticPlugin;

public class CheckMultiGet extends AbstractElasticPlugin {

  public CheckMultiGet(Settings settings)
  {
    super(settings);
  }

  @Override
  public String getPath()
  {
    return "checkMultiGet";
  }

  @Override
  public Method getRequestMethod()
  {
    return Method.GET;
  }

  @Override
  protected Map<String, Object> executePlugin(NodeClient client, Map<String, Object> requestMap,
      Map<String, String> params) throws Throwable
  {
    
    Map<String, Object> returnMap = new HashMap<>();
    MultiGetRequestBuilder prepareMultiGet = client.prepareMultiGet();
    
    
    GetResponse response = prepareMultiGet.add("myindex", "_doc", "EMP-003").get().getResponses()[0].getResponse();

    if(response.isExists()) {
      returnMap = response.getSource();
    }

    return returnMap;
  }

  @Override
  public String getName()
  {
    return "Testing MutliGet";
  }
  
}
