package pl.shop.plugin.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public final class MySQL {

    private final HikariDataSource dataSource;
    private static MySQL instance;

    public MySQL() {
        MySQL.instance = this;
        this.dataSource = new HikariDataSource();
        int poolSize = 5;
        this.dataSource.setMaximumPoolSize(poolSize);
        this.dataSource.setConnectionTimeout(30000L);
        this.dataSource.setJdbcUrl("jdbc:mysql://" + "localhost" + ":" + 3306 + "/" + "test" + "?useSSL=" + false);
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("");
        this.dataSource.addDataSourceProperty("cachePrepStmts", true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", true);
        executeUpdate("CREATE TABLE IF NOT EXISTS users(`uuid` text not null, `name` text not null, `coins` BIGINT(20));");
    }

    public void executeQuery(final String query, final Consumer<ResultSet> action) {
        try (final Connection connection = this.dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(query);
             final ResultSet result = statement.executeQuery()) {
            action.accept(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(final String query) {
        try (final Connection connection = this.dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(query)) {
            if (statement == null) {
                return;
            }
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MySQL getInstance() {
        return instance;
    }
}
