package net.huebl.lambda.transactions.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String text;
}
