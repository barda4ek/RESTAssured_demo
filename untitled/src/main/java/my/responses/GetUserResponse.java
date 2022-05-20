package my.responses;

import lombok.Data;
import my.pojos.UserPojo;

@Data
public class GetUserResponse {

   private UserPojo.UserPojoData data;
   private UserPojo.SupportPojo support;
}
