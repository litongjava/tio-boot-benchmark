package com.litongjava.tio.boot.benchmarker.config;

import com.litongjava.db.activerecord.ActiveRecordPlugin;
import com.litongjava.db.activerecord.OrderedFieldContainerFactory;
import com.litongjava.db.activerecord.dialect.PostgreSqlDialect;
import com.litongjava.db.hikaricp.HikariCpPlugin;
import com.litongjava.model.dsn.JdbcInfo;
import com.litongjava.tio.utils.dsn.DbDSNParser;
import com.litongjava.tio.utils.environment.EnvUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresqlDbConfig {

  public void init() {
    String dsn = EnvUtils.get("DATABASE_DSN");
    if (dsn == null) {
      return;
    }

    JdbcInfo jdbc = new DbDSNParser().parse(dsn);

    // start active recored
    String jdbcUrl = jdbc.getUrl();
    String jdbcUser = jdbc.getUser();
    String jdbcPswd = jdbc.getPswd();

    HikariCpPlugin hikariCpPlugin = new HikariCpPlugin(jdbcUrl, jdbcUser, jdbcPswd);

    ActiveRecordPlugin arp = new ActiveRecordPlugin(hikariCpPlugin);
    if (EnvUtils.isDev()) {
      arp.setDevMode(true);
    }

    arp.setDialect(new PostgreSqlDialect());
    arp.setContainerFactory(new OrderedFieldContainerFactory());
    hikariCpPlugin.start();
    boolean isSuccess = arp.start();
    if (isSuccess) {
      log.info("connected to postgresql successful");
    }
  }
}
