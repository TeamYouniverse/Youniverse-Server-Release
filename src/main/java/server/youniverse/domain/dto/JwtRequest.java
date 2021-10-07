package server.youniverse.domain.dto;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID=5926468583005150707L;

    private String name;
    private String password;
}
