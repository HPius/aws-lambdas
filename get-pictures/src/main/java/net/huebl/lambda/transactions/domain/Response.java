package net.huebl.lambda.transactions.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Response {
    List<Picture> pictures;
}
