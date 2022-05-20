package my.responses;

import lombok.Data;

@Data
public class PutUserResponse {

    private String name;
    private String job;
    private String updatedAt;
}
