package com.work.bench.rabbitmq.consumer;

import com.work.bench.config.RabbitMQConfig;
import com.work.bench.pojo.logo.LoginLog;
import com.work.bench.rabbitmq.message.UserLoginMessage;
import com.work.bench.service.logservice.LoginLogService;
import com.work.bench.websocket.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author 洁心未眠
 * @Package com.work.bench.rabbitmq.consumer
 * @date 2026/8/17 15:45
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserLoginConsumer {

    private final LoginLogService loginLogService;
    private final WebSocketService webSocketService;

    /**
     * 监听 login 队列的消息
     * 记录日志
     *
     * @param message
     */

    @RabbitListener(queues = RabbitMQConfig.USER_LOGIN_QUEUE)
    public void receiveLogin(UserLoginMessage message) {

        // 这里处理消息
        LoginLog loginLog = new LoginLog();
        BeanUtils.copyProperties(message, loginLog);
        loginLogService.save(loginLog);
    }


}
