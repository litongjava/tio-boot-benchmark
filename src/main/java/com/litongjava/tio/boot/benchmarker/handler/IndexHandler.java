package com.litongjava.tio.boot.benchmarker.handler;

import com.alibaba.fastjson2.JSON;
import com.litongjava.tio.boot.benchmarker.model.Message;
import com.litongjava.tio.http.common.HeaderName;
import com.litongjava.tio.http.common.HeaderValue;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.util.Resps;

import lombok.extern.slf4j.Slf4j;

/**
 * ab -k -n1000000 -c100 http://127.0.0.1:8080/json ab -k -n1000000 -c100
 * http://127.0.0.1:8080/plaintext
 */
@Slf4j
public class IndexHandler {
  private static final String HELLO_WORLD = "Hello, World!";

  public HttpResponse index(HttpRequest request) {
    return Resps.txt(request, "tio-server");
  }

  // 在IndexController中添加
  public HttpResponse json(HttpRequest request) {
    // 更高性能的写法
    HttpResponse ret = new HttpResponse(request);
    ret.setBody(JSON.toJSONString(new Message(HELLO_WORLD)).getBytes());
    ret.addHeader(HeaderName.Content_Type, HeaderValue.Content_Type.TEXT_PLAIN_JSON);
    return ret;
  }
}
