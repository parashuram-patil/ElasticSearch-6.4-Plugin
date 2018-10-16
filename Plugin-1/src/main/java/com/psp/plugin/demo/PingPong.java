package com.psp.plugin.demo;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestRequest.Method;

import com.psp.handler.AbstractElasticPlugin;

public class PingPong extends AbstractElasticPlugin {
  
  public PingPong(Settings settings)
  {
    super(settings);
  }
  
  @Override
  public String getPath()
  {
    return "pingpong";
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
    returnMap.put("Response", getName());
    return returnMap;
  }
  
  @Override
  public String getName()
  {
    return "Ping Pong";
  }
}
