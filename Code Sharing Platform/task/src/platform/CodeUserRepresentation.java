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
    private Long time = 0L;
    @JsonProperty("views")
    private Integer view = 0;


    CodeUserRepresentation(String code, LocalDateTime date, Long time, Integer view) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
        this.code = code;
        this.view = view;
        this.time = time;

    }

    CodeUserRepresentation(String code, LocalDateTime date, Long time) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
        this.code = code;
        this.time = time;

    }

    CodeUserRepresentation(String code, LocalDateTime date, Integer view) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
        this.code = code;
        this.view = view;
    }

    public CodeUserRepresentation() {

    }

    public CodeUserRepresentation(String code, LocalDateTime date) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
        this.code = code;
    }

}
