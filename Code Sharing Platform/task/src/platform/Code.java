package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    @JsonIgnore
    @JsonProperty("view")
    private int viewRestriction;
    @JsonIgnore
    @JsonProperty("time")
    private long timeRestriction;
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

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSSSSS"));
    }
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

    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    public boolean isViewRestricted() {
        return isViewRestricted;
    }

    public long getTimeRestriction() {
        return timeRestriction;
    }

    public int getViewRestriction() {
        return viewRestriction;
    }

    public Code updateRestriction() {
        long secondsDiff = Math.abs(ChronoUnit.SECONDS.between(this.date, LocalDateTime.now()));
        if (this.isTimeRestricted || this.isViewRestricted) {
            this.timeRestriction = Math.max(0L, this.timeRestriction - secondsDiff);
            System.out.println(this.timeRestriction);
            --this.viewRestriction;
            if (this.timeRestriction == 0L && this.isTimeRestricted) {
                this.toBeDeleted = true;
            }
            if (this.viewRestriction < 0L && this.isViewRestricted) {
                this.toBeDeleted = true;
            }
            if (this.viewRestriction < 0) {
                this.viewRestriction = 0;
            }
        }
        return this;
    }
}