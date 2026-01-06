package com.litongjava.tio.boot.benchmarker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.benchmarker.config.BenchMarkerAppCconfig;
import com.litongjava.tio.boot.server.TioBootServer;

public class BenchMarkerApp {
  public static void main(String[] args) {

    long start = System.currentTimeMillis();

    TioBootServer me = TioBootServer.me();
    ThreadFactory vf = Thread.ofVirtual().name("tio-biz-", 0).factory();
    ExecutorService executor = Executors.newThreadPerTaskExecutor(vf);
    me.setBizExecutor(executor);

    TioApplication.run(BenchMarkerApp.class, new BenchMarkerAppCconfig(), args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}