package com.litongjava.tio.boot.benchmarker.config;

import com.litongjava.context.BootConfiguration;
import com.litongjava.tio.boot.benchmarker.controller.CacheHandler;
import com.litongjava.tio.boot.benchmarker.controller.DbHandler;
import com.litongjava.tio.boot.benchmarker.controller.IndexHandler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.common.HttpConfig;
import com.litongjava.tio.http.server.router.HttpRequestRouter;
import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.utils.environment.EnvUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenchMarkerAppCconfig implements BootConfiguration {

  @Override
  public void config() {

    HttpRequestRouter r = TioBootServer.me().getRequestRouter();
    // add route
    IndexHandler controller = new IndexHandler();

    r.add("/", controller::index);
    r.add("/plaintext", controller::plaintext);
    r.add("/json", controller::json);

    DbHandler dbQueryController = new DbHandler();
    r.add("/db", dbQueryController::db);
    r.add("/queries", dbQueryController::queries);
    r.add("/updates", dbQueryController::updates);
    r.add("/fortunes", dbQueryController::fortunes);

    CacheHandler cacheController = new CacheHandler();
    r.add("/cacheQuery", cacheController);

    // config server
    HttpConfig httpConfig = TioBootServer.me().getHttpConfig();
    httpConfig.setWelcomeFile(null);
    httpConfig.setCompatible1_0(false);

    ServerTioConfig serverTioConfig = TioBootServer.me().getServerTioConfig();
    serverTioConfig.statOn = false;

    // start config
    try {
      if (!EnvUtils.getBoolean("native", false)) {
        new PostgresqlDbConfig().init();
        new EnjoyEngineConfig().engine();
        new EhCachePluginConfig().ehCachePlugin();
      } else {
        log.info("native mode,only start server");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

  }
}