package br.com.fiap.trip.datastore.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@DynamoDBTable(tableName = "trip")
public class TripEntity {

	@DynamoDBAutoGeneratedKey
	@DynamoDBHashKey(attributeName = "id")
	private String id;

	@DynamoDBRangeKey(attributeName = "dateTrip")
	private String dateTrip;

	@DynamoDBIndexRangeKey(attributeName = "country", localSecondaryIndexName = "countryIndex")
	private String country;

	@DynamoDBIndexRangeKey(attributeName = "city", localSecondaryIndexName = "cityIndex")
	private String city;

	@DynamoDBAttribute(attributeName = "urlRepository")
	private String urlRepository;
}
