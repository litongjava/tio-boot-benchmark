package com.litongjava.tio.boot.benchmarker.handler;

import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HeaderName;
import com.litongjava.tio.http.common.HeaderValue;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.handler.HttpRequestHandler;

public class PlaintextHandler implements HttpRequestHandler {

  private static final String HELLO_WORLD = "Hello, World!";

  private static final byte[] HELLO_WORLD_BYTES = HELLO_WORLD.getBytes();

  @Override
  public HttpResponse handle(HttpRequest httpRequest) throws Exception {
    HttpResponse response = TioRequestContext.getResponse();
    response.setBody(HELLO_WORLD_BYTES);
    response.addHeader(HeaderName.Content_Type, HeaderValue.Content_Type.TEXT_PLAIN_TXT);
    return response;
  }

}
