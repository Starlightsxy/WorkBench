package com.work.bench.websocket.handler;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserWebSocketHandler extends TextWebSocketHandler {
    private final JwtUtil jwtUtil;

    /**
     * 保存用户ID和WebSocket连接
     */
    private static final Map<Integer, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 跟用户建立连接，这里不可以用spring security 或者当前线程的信息，因为websocket的连接不是和他们绑定在一起的，
     * 我们可以通过前端的token，来解析出用户的id
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println(session);
        String token = getToken(session);
        if (token == null) {
            try {

                session.close(CloseStatus.NOT_ACCEPTABLE);
            } catch (Exception e) {
                log.error("关闭 WebSocket 连接失败", e);
            }
        }
        // 校验JWT
        Claims claims = jwtUtil.validateToken(token);
        if (claims == null) {
            try {
                session.close(CloseStatus.NOT_ACCEPTABLE);
            } catch (IOException e) {
                log.error("关闭 WebSocket 连接失败", e);
            }
            return;
        }
        // 获取userId
        Integer userId = Integer.parseInt(claims.getSubject());
        // 保存用户和websocket 连接
        SESSION_MAP.put(userId, session);
        log.info("用户 {} 建立 WebSocket 连接", userId);
    }

    /**
     * 关闭连接后删除session
     *
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        SESSION_MAP.values().remove(session);
        log.info("WebSocket连接关闭");
    }

    /**
     * 给指定用户发送消息
     */
    public void sendToUser(Integer userId, String message) {

        WebSocketSession session = SESSION_MAP.get(userId);

        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("WebSocket消息发送失败", e);
            }
        }
    }

    /**
     * 获取前端建立连接时传递的token，例如：
     * ws://localhost:8080/ws?token=xxxxx
     *
     * @param session
     * @return
     */
    private String getToken(WebSocketSession session) {

        String query = Objects.requireNonNull(session.getUri()).getQuery();

        if (query == null) {
            return null;
        }

        for (String param : query.split("&")) {

            String[] pair = param.split("=");

            if (pair.length == 2 && pair[0].equals("token")) {
                return pair[1];
            }
        }

        return null;
    }
}