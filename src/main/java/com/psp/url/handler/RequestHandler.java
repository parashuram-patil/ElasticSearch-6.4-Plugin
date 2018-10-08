
package com.psp.url.handler;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.reflections.Reflections;

public class RequestHandler extends BaseRestHandler {
  
  @Inject
  public RequestHandler(final Settings settings, final RestController controller)
  {
    super(settings);
    
    Reflections reflections = new Reflections();
    Set<Class<? extends AbstractElasticPlugin>> subTypesOf = reflections.getSubTypesOf(AbstractElasticPlugin.class);
    for (Class<? extends AbstractElasticPlugin> elasticPlugin : subTypesOf) {
      AbstractElasticPlugin plugin = null;
      try {
        
        if (Modifier.isAbstract(elasticPlugin.getModifiers())) {
          continue;
        }
        
        Constructor<?> ctor = elasticPlugin.getConstructor(Settings.class);
        plugin = (AbstractElasticPlugin) ctor.newInstance(new Object[] { settings });

        controller.registerHandler(plugin.getRequestMethod(), plugin.getPath(), plugin);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  @Override
  public String getName()
  {
    return this.getClass().getSimpleName();
  }
  
  @Override
  protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client)
      throws IOException
  {
    /*return channel -> {
      try {
        if (request.path()
            .contains("ping")) {
          RestResponse response = new BytesRestResponse(RestStatus.OK, "pong");
          channel.sendResponse(response);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
        throw e;
      }
      
    };*/
    
    return null;
  }
}
