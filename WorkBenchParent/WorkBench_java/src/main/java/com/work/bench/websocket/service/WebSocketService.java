package com.work.bench.websocket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.bench.websocket.handler.UserWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 提供给业务代码调用，负责推送消息给前端
 *
 * @author 洁心未眠
 * @Package com.work.bench.websocket.service
 * @date 2026/8/17 18:35
 */
@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final UserWebSocketHandler userWebSocketHandler;

    private final ObjectMapper objectMapper;

    public void sendToUser(Integer userId, Object message) {

        try {

            String json = objectMapper.writeValueAsString(message);

            userWebSocketHandler.sendToUser(userId, json);

        } catch (JsonProcessingException e) {

            throw new RuntimeException("WebSocket消息转换失败", e);
        }
    }
}
