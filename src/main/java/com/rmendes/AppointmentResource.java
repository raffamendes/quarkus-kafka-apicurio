package com.rmendes;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.rmendes.model.Appointment;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("appointment")
public class AppointmentResource {
	
	@Inject
    @Channel("appointment")
    Emitter<Appointment> emitter;
	
	@POST
	@Path("/tentative")
	public Response createTentativeAppointment(Appointment a) {
		emitter.send(a);
		return Response.ok().build();
	}

}
