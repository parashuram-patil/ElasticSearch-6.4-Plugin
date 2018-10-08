package com.psp.response;

import java.io.IOException;

import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestStatus;

public class ResponseCarrier {
  
  public static void successResponse(RestChannel channel, Object response) throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    channel.sendResponse(
        new BytesRestResponse(RestStatus.OK, mapper.writeValueAsString(response)));
    
  }
  
  public static void failedResponse(RestChannel channel, Throwable e) throws IOException
  {
    RestStatus status = RestStatus.INTERNAL_SERVER_ERROR;
    channel.sendResponse(
        new BytesRestResponse(status, new ObjectMapper().writeValueAsString(e)));
  }
}
