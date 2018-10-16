package com.psp.handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psp.response.ResponseCarrier;

public abstract class AbstractElasticPlugin extends BaseRestHandler implements IElasticPlugin {
  
  public AbstractElasticPlugin(Settings settings)
  {
    super(settings);
  }
  
  protected abstract Map<String, Object> executePlugin(final NodeClient client,
      Map<String, Object> requestMap, Map<String, String> params) throws Throwable;
  
  @Override
  public RestChannelConsumer prepareRequest(final RestRequest request, final NodeClient client)
      throws IOException
  {
    return channel -> {
      try {
        
        Map<String, String> params = request.params();
        Map<String, Object> postData = new HashMap<>();
        if (request.method().equals(Method.GET)) {
          
        }
        else {
          BytesReference postBody = request.content();
          postData = new ObjectMapper().readValue(postBody.streamInput(),
              new TypeReference<Map<String, Object>>()
              {
              });
        }
        
        Map<String, Object> result = executePlugin(client, postData, params);
        ResponseCarrier.successResponse(channel, result);
      }
      catch (Exception e) {
        ResponseCarrier.failedResponse(channel, e);
      }
      catch (Throwable e) {
        ResponseCarrier.failedResponse(channel, e);
      }
      
    };
  }
  
  static Set<String> RESPONSE_PARAMS = Collections.unmodifiableSet(
      new HashSet<>(Arrays.asList("test")));
  
  @Override
  protected Set<String> responseParams()
  {
    return RESPONSE_PARAMS;
  }
  
}
