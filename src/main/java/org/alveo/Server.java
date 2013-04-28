package org.alveo;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {
  public void start() {
    // pull config and setup logging
    JsonObject config = container.getConfig();
    final Logger logger = container.getLogger();
    // example for pulling env and config variables
    logger.info("current directory: "+container.getEnv().get("PWD"));
    // create server
    /*
    NetServer server = vertx.createNetServer();
    server.connectHandler(new Handler<NetSocket>() {
      public void handle(NetSocket socket) {
        //Pump.createPump(socket, socket).start(); // basic echo functionality
        logger.info("client connected");
      }
    }).listen(config.getInteger("serverPort"), config.getString("server"));
    */
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(new Handler<HttpServerRequest>() {
      public void handle(HttpServerRequest request) {
        /*
        // http://localhost:8888/?param1=test2&param2=test2
        StringBuilder sb = new StringBuilder();
        // request headers
        sb.append("request headers:\n");
        for(Map.Entry<String, String> header: request.headers().entrySet()) {
          sb.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
        }
        // url params
        sb.append("url params:\n");
        for(Map.Entry<String, String> header: request.params().entrySet()) {
          sb.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
        }
        request.response.putHeader("content-type", "text/plain");
        request.response.end(sb.toString());
        */
        // serve static files for now (until CDN integration)
        String file = "";
        if (request.path.equals("/")) file = "index.html";
        else if (!request.path.contains("..")) file = request.path;
        request.response.sendFile("web/" + file);
      }
    });
    /*
    // websocket handler
    server.websocketHandler(new Handler<ServerWebSocket>() {
      public void handle(ServerWebSocket ws) {  
        if (ws.path.equals("/ws/echo")) {
          Pump pump = Pump.createPump(ws, ws);
          pump.start();                        
        } else {
          ws.reject();
        }
      }
    });
    */
    JsonObject jsockConfig = new JsonObject().putString("prefix", "/ws/echo");
    /*
    // sockjs handler
    SockJSServer sockJSServer = vertx.createSockJSServer(server);
    sockJSServer.installApp(jsockConfig, new Handler<SockJSSocket>() {
      public void handle(SockJSSocket sock) {
        Pump.createPump(sock, sock).start();
      }
    });
    */
    // create sockjs bus
    JsonArray permitted = new JsonArray();
    permitted.add(new JsonObject());
    vertx.createSockJSServer(server).bridge(jsockConfig, permitted, permitted);
    
    server.listen(config.getInteger("serverPort"), config.getString("server"));
    logger.info("running at "+config.getString("server")+":"+config.getInteger("serverPort"));
    // close server
    /*
    server.close(new SimpleHandler() {
      public void handle() {
          logger.info("server closed");
      }        
    });
    */
    /*
    // create bus
    EventBus bus = vertx.eventBus();
    // create handler (message can be several object types other than JsonObject)
    Handler<Message<JsonObject>> handler = new Handler<Message<JsonObject>>() {
      public void handle(Message<JsonObject> message) {
        JsonObject bodyJson = message.body;
        System.out.println("message received:\n"+bodyJson.toString());
        message.reply();
      }
    };
    // register handler with bus including callback
    bus.registerHandler("address", handler, new AsyncResultHandler<Void>() {
      @Override
      public void handle(AsyncResult<Void> arg0) {
        System.out.println("The handler has been registered across the cluster");
      }
    });
    //bus.unregisterHandler("address", handler);
    // send String to all handlers listening at an address
    //bus.publish("address", "hello world");
    // create sample json object
    JsonObject sampleData = new JsonObject();
    sampleData.putString("foo", "wibble");
    // send message to only one handler at an address
    bus.send("address", sampleData);
    */
  }

  // junit testing
  private int testVar = 1;
  public int test(){
    return testVar;
  }
}
