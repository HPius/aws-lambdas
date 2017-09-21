package net.huebl.lambda.transactions;

import java.util.Map;

import net.huebl.lambda.transactions.domain.Response;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<String, Response> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public Response handleRequest(String input, Context context) {
		//LOG.info("received: " + input);
		Response response = Response.builder().text("Go Serverless v1.x! Your function executed successfully!").build();
		return response;
	}
}
