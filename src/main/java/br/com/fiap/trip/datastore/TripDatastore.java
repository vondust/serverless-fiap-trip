package br.com.fiap.trip.datastore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fiap.trip.datastore.config.DynamoDBManager;
import br.com.fiap.trip.datastore.entity.TripEntity;

public class TripDatastore {

	private static final DynamoDBMapper MAPPER = DynamoDBManager.mapper();

	public Optional<TripEntity> search(final String id) {
		final TripEntity trip = MAPPER.load(TripEntity.class, id);
		return Optional.ofNullable(trip);
	}

	public TripEntity save(final TripEntity trip) {
		MAPPER.save(trip);
		return trip;
	}

	public List<TripEntity> findByPeriod(final String starts, final String ends) {
		final Map<String, AttributeValue> params = new HashMap<String, AttributeValue>();
		params.put(":val1", new AttributeValue().withS(starts));
		params.put(":val2", new AttributeValue().withS(ends));

		final DynamoDBQueryExpression<TripEntity> queryExpression = new DynamoDBQueryExpression<TripEntity>()
				.withKeyConditionExpression("date between :val1 and :val2")
				.withExpressionAttributeValues(params);

		final List<TripEntity> studies = MAPPER.query(TripEntity.class, queryExpression);

		return studies;
	}

	public void delete(TripEntity input) {
		MAPPER.delete(input);
	}
}
