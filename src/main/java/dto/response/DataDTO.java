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

public class DataDTO {

    @SerializedName("id")
    int userId;

    @SerializedName("name")
    String userName;

    @SerializedName("year")
    int year;

    @SerializedName("color")
    String color;

    @SerializedName("pantone_value")
    String pantoneValue;
}
