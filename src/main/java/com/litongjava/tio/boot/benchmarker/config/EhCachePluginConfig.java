package com.litongjava.tio.boot.benchmarker.config;



import com.litongjava.ehcache.EhCachePlugin;

public class EhCachePluginConfig {

  public EhCachePlugin ehCachePlugin() {
    EhCachePlugin ehCachePlugin = new EhCachePlugin();
    ehCachePlugin.start();
    return ehCachePlugin;
  }
}
