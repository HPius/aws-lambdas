package net.huebl.lambda.transactions;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import net.huebl.lambda.transactions.domain.Picture;
import net.huebl.lambda.transactions.domain.Response;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<String, Response> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    private AmazonS3 s3client;
    String bucketNameIn = "picture-in.lambda-picture-farm";
    String bucketNameOut = "picture-out.lambda-picture-farm";

    public Handler() {
        super();
        s3client = AmazonS3ClientBuilder.defaultClient();

    }

    @Override
    public Response handleRequest(String input, Context context) {
        //LOG.info("received: " + input);
        List<Picture> pictures = new ArrayList<>();


        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(bucketNameIn)
                .withMaxKeys(10); //TODO

        ObjectListing result;
        do {
            result = s3client.listObjects(listObjectsRequest);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {

                String key = objectSummary.getKey();
                long originalSize = objectSummary.getSize();

                S3Object thumbnail = getElemFromS3ByKey(key);

                //TODO

                pictures.add(Picture.builder().name(key).size(originalSize).path("urlGross").thumbnail("urlklein").build());

                System.out.println(" - " + objectSummary.getKey() + "  " +
                        "(size = " + objectSummary.getSize() +
                        ")");
            }
        } while (result.isTruncated());


        Response response = Response.builder().pictures(pictures).build();
        return response;
    }

    private S3Object getElemFromS3ByKey(String key) {

        return s3client.getObject(new GetObjectRequest(bucketNameOut, key));

    }
}
