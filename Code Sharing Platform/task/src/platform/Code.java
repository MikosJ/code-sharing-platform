package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Code {

    @JsonProperty("code")
    private String code;

    @JsonProperty("date")
    private LocalDateTime date;
    @Id
    @JsonIgnore
    private UUID id;
    @JsonIgnore
    @JsonProperty("isTimeRestricted")
    private boolean isTimeRestricted;
    @JsonIgnore
    @JsonProperty("isViewRestricted")
    private boolean isViewRestricted;
    @JsonProperty("time")
    private long timeRestriction;

    @JsonProperty("views")
    private int viewRestriction;

    @JsonIgnore
    @JsonProperty("toBeDeleted")
    private boolean toBeDeleted;


    public Code() {
    }


    public Code(String code) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("date")
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
    }

    @JsonIgnore
    public LocalDateTime getDateLDT() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public UUID getId() {
        return id;
    }

    public void setTimeRestriction(long timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public void setViewRestriction(int viewRestriction) {
        this.viewRestriction = viewRestriction;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        this.isTimeRestricted = timeRestricted;
    }

    public void setViewRestricted(boolean viewRestricted) {
        this.isViewRestricted = viewRestricted;
    }

    @JsonIgnore
    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    @JsonIgnore
    public boolean isViewRestricted() {
        return isViewRestricted;
    }

    public long getTimeRestriction() {
        return timeRestriction;
    }

    public int getViewRestriction() {
        return viewRestriction;
    }

    @JsonIgnore
    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void updateRestriction() {
        long secondsDiff = Duration.between(this.getDateLDT(), LocalDateTime.now()).toSeconds();

        if (this.isTimeRestricted || this.isViewRestricted) {
            this.timeRestriction -= secondsDiff;
            --this.viewRestriction;

            if (this.timeRestriction <= 0 && this.isTimeRestricted) {
                this.toBeDeleted = true;
            }

            if (this.viewRestriction <= 0L && this.isViewRestricted) {
                this.toBeDeleted = true;
            }

            if (this.viewRestriction < 0) {
                this.viewRestriction = 0;
            }
            if(!this.isTimeRestricted && this.timeRestriction <0) {
                this.timeRestriction = 0;
            }

        }
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", isTimeRestricted=" + isTimeRestricted +
                ", isViewRestricted=" + isViewRestricted +
                ", timeRestriction=" + timeRestriction +
                ", viewRestriction=" + viewRestriction +
                ", toBeDeleted=" + toBeDeleted +
                '}';
    }
}