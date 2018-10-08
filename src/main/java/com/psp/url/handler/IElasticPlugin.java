package com.psp.url.handler;

import org.elasticsearch.rest.RestRequest;

public interface IElasticPlugin {
  
  String getPath();
  
  RestRequest.Method getRequestMethod();
}
