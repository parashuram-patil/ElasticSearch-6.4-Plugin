package com.psp.plugin.demo;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestRequest.Method;

import com.psp.url.handler.AbstractElasticPlugin;

public class DingDong extends AbstractElasticPlugin {
  
  public DingDong(Settings settings)
  {
    super(settings);
  }
  
  @Override
  public String getPath()
  {
    return "dingdong";
  }
  
  @Override
  public Method getRequestMethod()
  {
    return Method.GET;
  }
  
  @Override
  protected Map<String, Object> execute(NodeClient client, Map<String, Object> requestMap,
      Map<String, String> params) throws Throwable
  {
    Map<String, Object> returnMap = new HashMap<>();
    returnMap.put("Response", getName());
    return returnMap;
  }
  
  @Override
  public String getName()
  {
    return "Ping Pong";
  }
  
}
