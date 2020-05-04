package br.com.fiap.trip.serverless.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.trip.datastore.TripDatastore;
import br.com.fiap.trip.datastore.entity.TripEntity;
import br.com.fiap.trip.serverless.model.HandlerRequest;
import br.com.fiap.trip.serverless.model.HandlerResponse;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {

	private TripDatastore repository = new TripDatastore();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		TripEntity trip = null;
		try {
			trip = new ObjectMapper().readValue(request.getBody(), TripEntity.class);
		} catch (IOException e) {
			return HandlerResponse.builder()
					.setStatusCode(400)
					.setRawBody("There is a error in your TripEntity!")
					.build();
		}

		context.getLogger().log("Creating a new trip: " + trip);
		final TripEntity tripRecorded = repository.save(trip);
		return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripRecorded).build();
	}
}
