package ru.example.sbertest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class AuthenticationApiRequest extends BaseRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
