package com.github.boot.shiro.util;

import com.github.boot.util.Constants;
import com.github.boot.util.ShortUuid;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/11
 */
public class RedisCacheSessionDao extends CachingSessionDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RCE_SESSION_KEY_PREFIX = "session_";

    private long globalSessionTimeOut = Constants.GLOBAL_SESSION_TIMEOUT;

    @Resource(name = "redisTemplate")
    private RedisTemplate<Serializable, Session> redisTemplate;

    public RedisCacheSessionDao() {
        if (globalSessionTimeOut == 0) {
            globalSessionTimeOut = 604800000L;
        }
        logger.info("REDIS GLOBAL SESSION REAL TIMEOUT: {}", globalSessionTimeOut);
    }

    @Override
    protected void doUpdate(Session session) {
        String sessionKey = RCE_SESSION_KEY_PREFIX + session.getId();
        redisTemplate.opsForValue().set(sessionKey, session);
        redisTemplate.expire(sessionKey, globalSessionTimeOut, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        redisTemplate.delete(RCE_SESSION_KEY_PREFIX + session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        String sessionKey = RCE_SESSION_KEY_PREFIX + sessionId;
        redisTemplate.opsForValue().set(sessionKey, session);
        redisTemplate.expire(sessionKey, globalSessionTimeOut, TimeUnit.MILLISECONDS);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return redisTemplate.opsForValue().get(RCE_SESSION_KEY_PREFIX + sessionId);
    }

    /**
     * 自定义sessionID
     */
    @Override
    protected Serializable generateSessionId(Session session) {
        return new ShortUuid.Builder().build().toString();
    }
}

