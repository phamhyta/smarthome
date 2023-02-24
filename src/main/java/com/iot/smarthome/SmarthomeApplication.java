package com.iot.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmarthomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthomeApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		final String topic = "/topic/event";
//
//		messagingService.subscribe(topic);
//
//		messagingService.publish(topic, "Hi\nThis is a sample message published to topic roytuts/topic/event", 0, true);
//
//		Thread.sleep(3000);
//		messagingService.publish(topic, "Xin chào nha!", 0, true);
//
//		context.close();
//	}
}
