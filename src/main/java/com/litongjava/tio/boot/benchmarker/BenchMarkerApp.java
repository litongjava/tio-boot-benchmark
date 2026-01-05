package com.litongjava.tio.boot.benchmarker;

import java.lang.Thread.Builder.OfVirtual;
import java.util.concurrent.ThreadFactory;

import com.litongjava.tio.boot.TioApplication;
import com.litongjava.tio.boot.benchmarker.config.BenchMarkerAppCconfig;
import com.litongjava.tio.boot.server.TioBootServer;

public class BenchMarkerApp {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		OfVirtual ofVirtual = Thread.ofVirtual();
		ThreadFactory factory = ofVirtual.name("tio-v-", 1).factory();
		TioBootServer server = TioBootServer.me();
		server.setThreadFactory(factory);

		TioApplication.run(BenchMarkerApp.class, new BenchMarkerAppCconfig(), args);
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}
}