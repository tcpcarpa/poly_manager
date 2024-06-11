package es.polytex.integracionback.userDefinition.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userDefinition")

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)public class ConfigDefinition {
    @Id
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("cardId")
    private int cardId;
    @JsonProperty("firstName")
    private int firstName;
    @JsonProperty("lastName")
    private int lastName;
    @JsonProperty("fullName")
    private int fullName;
    @JsonProperty("departmentName")
    private int departmentName;
    @JsonProperty("title")
    private int title;
    @JsonProperty("effectiveLimitGroup")
    private int effectiveLimitGroup;
    @JsonProperty("futureInactiveData")
    private int futureInactiveData;
    @JsonProperty("cardId2")
    private int cardId2;
    @JsonProperty("email")
    private int email;
    @JsonProperty("dateFormat")
    private String dateFormat;
    @JsonProperty("numberFormat")
    private String numberFormat;

    private String siteID;
    @JsonCreator
    public ConfigDefinition() {
    }

    public ConfigDefinition(int userId, int cardId, int firstName, int lastName, int fullName, int departmentName, int title, int effectiveLimitGroup, int futureInactiveData, int cardId2, int email, String dateFormat, String numberFormat, String siteID) {
        this.userId = userId;
        this.cardId = cardId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.departmentName = departmentName;
        this.title = title;
        this.effectiveLimitGroup = effectiveLimitGroup;
        this.futureInactiveData = futureInactiveData;
        this.cardId2 = cardId2;
        this.email = email;
        this.dateFormat = dateFormat;
        this.numberFormat = numberFormat;
        this.siteID = siteID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getFirstName() {
        return firstName;
    }

    public void setFirstName(int firstName) {
        this.firstName = firstName;
    }

    public int getLastName() {
        return lastName;
    }

    public void setLastName(int lastName) {
        this.lastName = lastName;
    }

    public int getFullName() {
        return fullName;
    }

    public void setFullName(int fullName) {
        this.fullName = fullName;
    }

    public int getDepartment() {
        return departmentName;
    }

    public void setDepartment(int department) {
        this.departmentName = department;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getLimit() {
        return effectiveLimitGroup;
    }

    public void setLimit(int limit) {
        this.effectiveLimitGroup = limit;
    }

    public int getEnddate() {
        return futureInactiveData;
    }

    public void setEnddate(int enddate) {
        this.futureInactiveData = enddate;
    }

    public int getCardId2() {
        return cardId2;
    }

    public void setCardId2(int cardId2) {
        this.cardId2 = cardId2;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    @Override
    public String toString() {
        return "ConfigDefinition{" +
                "userId=" + userId +
                ", cardId=" + cardId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", fullName=" + fullName +
                ", departmentName=" + departmentName +
                ", title=" + title +
                ", effectiveLimitGroup=" + effectiveLimitGroup +
                ", futureInactiveData=" + futureInactiveData +
                ", cardId2=" + cardId2 +
                ", email=" + email +
                ", dateFormat=" + dateFormat +
                ", numberFormat=" + numberFormat +
                ", siteID='" + siteID + '\'' +
                '}';
    }
}
