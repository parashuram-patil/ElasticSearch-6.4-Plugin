package com.psp.handler;

import org.elasticsearch.rest.RestRequest.Method;

public interface IElasticPlugin {
  
  String getPath();
  
  Method getRequestMethod();
}
