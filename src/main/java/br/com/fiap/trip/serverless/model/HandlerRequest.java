package br.com.fiap.trip.serverless.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HandlerRequest {

	private String body;
	private String path;
	private Map<String, String> pathParameters;
	private Map<String, String> queryStringParameters;
}
