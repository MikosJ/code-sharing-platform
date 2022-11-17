package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CodeUserRepresentation {
    @JsonProperty("code")
    private String code;
    @JsonProperty("date")
    private String date;

    @JsonProperty("time")
    private long time;
    @JsonProperty("views")
    private int view;
    CodeUserRepresentation(String code, LocalDateTime date, long time, int view) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
        this.code = code;
        this.time = time;
        this.view = view;

    }
}
