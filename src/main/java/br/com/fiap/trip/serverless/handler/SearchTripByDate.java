package br.com.fiap.trip.serverless.handler;

import java.util.Optional;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.fiap.trip.datastore.TripDatastore;
import br.com.fiap.trip.datastore.entity.TripEntity;
import br.com.fiap.trip.datastore.exception.TripNotFoundException;
import br.com.fiap.trip.serverless.model.HandlerResponse;

public class SearchTripByDate implements RequestHandler<TripEntity, HandlerResponse> {

	private TripDatastore datastore = new TripDatastore();

	@Override
	public HandlerResponse handleRequest(final TripEntity trip, final Context context) {

		final String id = trip.getId();
		context.getLogger().log("[#] - Searching Trip by Id: " + id);
		final Optional<TripEntity> tripRetrieved = datastore.search(id);

		if (tripRetrieved.isPresent()) {
			context.getLogger().log("[#] - Trip found " + tripRetrieved.get());
			return HandlerResponse.builder().setObjectBody(tripRetrieved.get()).build();
		}

		throw new TripNotFoundException("[NotFound] - Trip id: " + trip.getId() + " not found");
	}
}
