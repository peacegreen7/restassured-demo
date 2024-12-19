package dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @SerializedName("name")
    String userName;

    @SerializedName("job")
    String jobName;

    public UserRequest(String name, boolean isEmptyJob) {
        this.userName = name;
    }

    public UserRequest(String job) {
        this.jobName = job;
    }
}
