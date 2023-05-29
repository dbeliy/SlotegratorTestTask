package org.slotegrator.dto.registration;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRegistrationData {

    private String currencyCode;
    private String email;
    private String name;
    private String passwordChange;
    private String passwordRepeat;
    private String surname;
    private String username;
}
