package io.ara.remittance.active.mqConfig;

//import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

//import javax.jms.ConnectionFactory;
import java.util.Arrays;

@Configuration
public class AcMQConsumerConfig {
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        try {
            activeMQConnectionFactory.setBrokerURL("tcp://mq-node:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600");
        } catch (JMSException e) {
            e.printStackTrace();
        }
//        activeMQConnectionFactory.sett.setTrustedPackages(Arrays.asList("com.mailshine.springbootstandaloneactivemq"));
        return  activeMQConnectionFactory;
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory( connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }
}
