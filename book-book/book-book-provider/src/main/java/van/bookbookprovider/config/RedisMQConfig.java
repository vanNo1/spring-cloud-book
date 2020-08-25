package van.bookbookprovider.config;

import base.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import van.bookbookprovider.receiver.MessageReceiver;
@Configuration
public class RedisMQConfig {


    /**
     * 注入消息监听容器
     *
     * @param connectionFactory 连接工厂
     * @param listenerAdapter   监听处理器1
     * @param listenerAdapter   监听处理器2 (参数名称需和监听处理器的方法名称一致，因为@Bean注解默认注入的id就是方法名称)
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅一个叫deleteChannel 的信道
        container.addMessageListener(listenerAdapter, new PatternTopic(Const.DELETE_CHANNEL));
        //订阅一个叫mq_02 的信道
        container.addMessageListener(listenerAdapter2, new PatternTopic("mq_02"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 消息监听处理器1
     *
     * @param receiver 处理器类
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        //给messageListenerAdapter 传入一个消息接收的处理器，利用反射的方法调用“receiveMessage”
        return new MessageListenerAdapter(receiver, "delete"); //receiveMessage：接收消息的方法名称
    }

    /**
     * 消息监听处理器2
     *
     * @param receiver 处理器类
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
        //给messageListenerAdapter 传入一个消息接收的处理器，利用反射的方法调用“receiveMessage2”
        return new MessageListenerAdapter(receiver, "receiveMessage2"); //receiveMessage：接收消息的方法名称
    }

}
