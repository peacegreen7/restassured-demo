package dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateUserResponse {
    @SerializedName("name")
    String userName;

    @SerializedName("job")
    String jobName;

    @SerializedName("id")
    String ID;

    @SerializedName("createdAt")
    String createTime;

}
