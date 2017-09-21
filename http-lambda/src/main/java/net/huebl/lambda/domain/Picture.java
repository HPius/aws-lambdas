package net.huebl.lambda.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Picture {
    String name;
    String thumbnailName;
}
