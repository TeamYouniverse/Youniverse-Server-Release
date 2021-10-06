package server.youniverse.domain.dto;

import lombok.*;
import server.youniverse.domain.entity.User;

@Data
public class UserDTO {
    private String name;
    private String password;
}
