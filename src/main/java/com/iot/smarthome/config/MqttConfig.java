package com.iot.smarthome.config;

import com.google.gson.Gson;
import com.iot.smarthome.dto.SensorDataDTO;
import com.iot.smarthome.entity.SensorDataEntity;
import com.iot.smarthome.service.SensorDataService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.time.LocalDateTime;

@Configuration
public class MqttConfig {

    @Autowired
    private SensorDataService sensorDataService;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[] { "tcp://broker.hivemq.com:1883" });
        options.setUserName("hello");
        String pass = "abc";
        options.setPassword(pass.toCharArray());
        options.setCleanSession(true);

        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("clientId-A8E6QfKzNh",
                mqttClientFactory(), "iot-g5");

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                if(topic.equals("iot-g5")) {
                    System.out.println("This is the topic");
                }
                SensorDataDTO sensorDataDTO = new Gson().fromJson(message.getPayload().toString(), SensorDataDTO.class);
                System.out.println("======");
                SensorDataEntity sensorDataEntity = new SensorDataEntity();
                sensorDataEntity.setHumid(sensorDataDTO.getHumid());
                sensorDataEntity.setTemp(sensorDataDTO.getTemp());
                sensorDataEntity.setDeviceId(sensorDataDTO.getDeviceId());
                sensorDataEntity.setLocation(sensorDataDTO.getLocation());
                System.out.println(sensorDataEntity.getTemp());
                System.out.println(sensorDataEntity.getHumid());
                System.out.println(sensorDataEntity.getDeviceId());
                System.out.println(sensorDataEntity.getLocation());
                sensorDataEntity.setTime(LocalDateTime.now());
                sensorDataService.saveData(sensorDataEntity);
                System.out.println("======");
                System.out.println(message.getPayload());
            }

        };
    }


    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        //clientId is generated using a random number
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("myIOT");
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }
}
