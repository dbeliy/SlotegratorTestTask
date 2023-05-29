package org.slotegrator.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;
    private String email;
    private String name;
    private String surname;
    private String role;
    private String position;
    private String status;
    private String isReport;
    private String comment;
    private String crateBy;
    private String report;
    private String updatedAt;
    private String createdAt;
    private String feedback;
}
