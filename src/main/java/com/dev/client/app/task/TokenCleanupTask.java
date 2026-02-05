package com.dev.client.app.task;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TokenCleanupTask {

    private final JdbcTemplate jdbcTemplate;

    public TokenCleanupTask(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Old tokens will be deleted once every hour.
    @Scheduled(cron = "0 */5 * * * *")
    public void cleanExpiredTokens() {
        String sql = "DELETE FROM oauth2_authorized_client WHERE access_token_expires_at < CURRENT_TIMESTAMP";
        int rows = jdbcTemplate.update(sql);
        System.out.println("Old, expired tokens have been deleted.: " + rows);
    }
}