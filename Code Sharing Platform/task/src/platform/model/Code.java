package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
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
    private Long timeRestriction;

    @JsonProperty("views")
    private Integer viewRestriction;

    @JsonIgnore
    @JsonProperty("toBeDeleted")
    private boolean toBeDeleted;

    @JsonIgnore
    private LocalDateTime modified;


    public Code() {
    }


    public Code(String code) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public Code(String code, Long time, Integer views) {
        if (time == null || time <= 0L) {
            this.timeRestriction = 0L;
            this.isTimeRestricted = false;
        } else {
            this.timeRestriction = time;
            this.isTimeRestricted = true;
        }
        if (views == null || views <= 0) {
            this.viewRestriction = 0;
            this.isViewRestricted = false;
        } else {
            this.viewRestriction = views;
            this.isViewRestricted = true;
        }
        this.id = UUID.randomUUID();
        this.code = code;
        this.date = LocalDateTime.now();
        this.modified = LocalDateTime.now();
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


    public UUID getId() {
        return id;
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
        long secondsDiff = Duration.between(modified, LocalDateTime.now()).toSeconds();

        if (this.isTimeRestricted || this.isViewRestricted) {
            this.timeRestriction -= secondsDiff;
            this.modified = LocalDateTime.now();
            if (this.isViewRestricted) {
                --this.viewRestriction;
            }

            if (this.viewRestriction < 0) {
                this.viewRestriction = 0;
            }

            if (this.timeRestriction < 0L) {
                this.timeRestriction = 0L;
            }

            if (this.timeRestriction == 0L && this.isTimeRestricted) {
                this.toBeDeleted = true;
            }

            if (this.viewRestriction == 0 && this.isViewRestricted) {
                this.toBeDeleted = true;
            }


        }
    }

    @Override
    public String toString() {
        return "Code{" + "code='" + code + '\'' + ", date=" + date + ", id=" + id + ", isTimeRestricted=" + isTimeRestricted + ", isViewRestricted=" + isViewRestricted + ", timeRestriction=" + timeRestriction + ", viewRestriction=" + viewRestriction + ", toBeDeleted=" + toBeDeleted + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Code code = (Code) o;
        return id != null && Objects.equals(id, code.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}