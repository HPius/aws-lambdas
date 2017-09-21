package net.huebl.lambda.transactions.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Picture {
    private String name;
    private String path;
    private long size;
    private String thumbnail;
    private long thumbnailSize;
}
