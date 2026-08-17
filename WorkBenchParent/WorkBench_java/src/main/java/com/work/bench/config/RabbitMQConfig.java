package com.work.bench.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq 的config 配置
 *
 * @author 洁心未眠
 * @Package com.work.bench.config
 * @date 2026/8/17 15:28
 */
@Configuration
public class RabbitMQConfig {
    // 交换机名称
    public static final String USER_EXCHANGE = "user.exchange";
    // 用户登录队列和key
    public static final String USER_LOGIN_QUEUE = "user.login.queue";
    public static final String USER_LOGIN_ROUTING_KEY = "user.login";
    // 纪念日队列和key
    public static final String ANNIVERSARY_QUEUE = "anniversary.queue";
    public static final String ANNIVERSARY_ROUTING_KEY = "anniversary";

    /**
     * 创建 Exchange
     * @return
     */
    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    /**
     * 创建 用户登录Queue
     * @return
     */
    @Bean
    public Queue userLoginQueue() {
        return new Queue(USER_LOGIN_QUEUE);
    }

    /**
     * 创建 Binding 绑定用户登录
     * @param userLoginQueue
     * @param userExchange
     * @return
     */
    @Bean
    public Binding userLoginBinding(Queue userLoginQueue, DirectExchange userExchange) {
        return BindingBuilder
                .bind(userLoginQueue).to(userExchange)
                .with(USER_LOGIN_ROUTING_KEY);

    }

    /**
     * 创建 纪念日 Queue
     * @return
     */
    @Bean
    public Queue anniversaryQueue() {
        return new Queue(ANNIVERSARY_QUEUE);
    }

    /**
     * 创建 Binding 绑定纪念日
     * @param anniversaryQueue
     * @param userExchange
     * @return
     */
    @Bean
    public Binding anniversaryBinding(Queue anniversaryQueue, DirectExchange userExchange) {
        return BindingBuilder
                .bind(anniversaryQueue).to(userExchange)
                .with(ANNIVERSARY_ROUTING_KEY);

    }
}
