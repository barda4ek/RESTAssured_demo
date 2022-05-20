package my.responses;

import lombok.Data;

@Data
public class PatchUserResponse {

    private String name;
    private String job;
    private String updatedAt;
}
