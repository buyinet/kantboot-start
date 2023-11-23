package com.kantboot.functional.template.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DependencyDTO implements Serializable {

    private String groupId;

    private String artifactId;

    private String version;

    private String relativePath;

    /**
     * 描述
     */
    private String description;

}
