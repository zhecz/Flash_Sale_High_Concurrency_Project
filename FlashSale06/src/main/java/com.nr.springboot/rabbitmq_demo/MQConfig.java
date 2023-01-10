package com.nr.springboot.rabbitmq_demo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {
	
	public static final String MIAOSHA_QUEUE = "miaosha.queue";
	public static final String QUEUE = "queue";

	public static final String TOPIC_QUEUE1 = "topic.queue01";
	public static final String TOPIC_QUEUE2 = "topic.queue02";
	public static final String TOPIC_EXCHANGE = "topicExchage";
	public static final String ROUTINGKEY_D01 = "#.queue.#";
	public static final String ROUTINGKEY_D02 = "*.queue.#";

	public static final String HEADER_QUEUE = "header.queue";
	public static final String HEADERS_EXCHANGE = "headersExchage";

	public static final String FANOUT_EXCHANGE = "fanoutexchage";
	public static final String FANOUT_QUEUE1 = "fanout.queue1";
	public static final String FANOUT_QUEUE2 = "fanout.queue2";

	public static final String DIRECT_EXCHANGE = "directexchange";
	public static final String DIRECT_QUEUE01 = "direct.queue01";
	public static final String DIRECT_QUEUE02 = "direct.queue02";
	public static final String ROUTINGKEY01 = "queue.red";
	public static final String ROUTINGKEY02 = "queue.green";
	
	/**
	 * Direct模式 交换机Exchange
	 * */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}
	
	/**
	 * Topic模式 交换机Exchange
	 * */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with(ROUTINGKEY_D01);
	}
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with(ROUTINGKEY_D02);
	}

	/**
	 * Direct模式 交换机Exchange
	 * */
	@Bean
	public DirectExchange directExchage(){
		return new DirectExchange(DIRECT_EXCHANGE);
	}

	@Bean
	public Queue directQueue01() {
		return new Queue(DIRECT_QUEUE01, true);
	}
	@Bean
	public Queue directQueue02() {
		return new Queue(DIRECT_QUEUE02, true);
	}

	@Bean
	public Binding DirectBinding1() {
		return BindingBuilder.bind(directQueue01()).to(directExchage()).with(ROUTINGKEY01);
	}
	@Bean
	public Binding DirectBinding2() {
		return BindingBuilder.bind(directQueue02()).to(directExchage()).with(ROUTINGKEY02);
	}

	/**
	 * Fanout模式 交换机Exchange
	 * */

	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Queue fanoutQueue1() {
		return new Queue(FANOUT_QUEUE1, true);
	}
	@Bean
	public Queue fanoutQueue2() {
		return new Queue(FANOUT_QUEUE2, true);
	}

	@Bean
	public Binding FanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchage());
	}
	@Bean
	public Binding FanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchage());
	}


	/**
	 * Header模式 交换机Exchange
	 * */
	@Bean
	public HeadersExchange headersExchage(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}
	@Bean
	public Queue headerQueue1() {
		return new Queue(HEADER_QUEUE, true);
	}
	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}
	
	
}
