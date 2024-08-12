package com.rmendes;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import com.rmendes.model.Appointment;

import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppointmentConsumer {
	
	private static final Logger LOG = Logger.getLogger(AppointmentConsumer.class);



	@Incoming("appnt-consumer")
	public CompletionStage<Void> consumeAppointment(Message<Appointment> msg){
		System.out.println("teste-debug");
		LOG.info(msg.getPayload().getMotive()+" - "+msg.getPayload().getLocation().toString());//+" - "+msg.getPayload().isConfirmed());
		var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
		metadata.getHeaders().forEach(x -> System.out.println(x));
		return msg.ack();
	}
	

  }
