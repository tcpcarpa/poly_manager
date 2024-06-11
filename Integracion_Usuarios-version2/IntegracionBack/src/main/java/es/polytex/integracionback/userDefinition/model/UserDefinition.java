package es.polytex.integracionback.userDefinition.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "user_definition")

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDefinition {
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
//    private boolean isActive = true;
    @JsonProperty("futureInactiveData")
    private int futureInactiveData;
    @JsonProperty("cardId2")
    private int cardId2;
    @JsonProperty("email")
    private int email;

    //private int favoriteSubTypes;

    @JsonCreator
    public UserDefinition() {
    }

    public UserDefinition(int userId, int cardId, int fullName, int departmentName, int title, int effectiveLimitGroup, int futureInactiveData, int cardId2, int email) {
        this.userId = userId;
        this.cardId = cardId;
        this.fullName = fullName;
        this.departmentName = departmentName;
        this.title = title;
        this.effectiveLimitGroup = effectiveLimitGroup;
        this.futureInactiveData = futureInactiveData;
        this.cardId2 = cardId2;
        this.email = email;
    }

    public UserDefinition(int userId, int cardId, int firstName, int lastName, int departmentName, int title, int effectiveLimitGroup, int futureInactiveData, int cardId2, int email) {
        this.userId = userId;
        this.cardId = cardId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
        this.title = title;
        this.effectiveLimitGroup = effectiveLimitGroup;
        this.futureInactiveData = futureInactiveData;
        this.cardId2 = cardId2;
        this.email = email;
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

    public int getFullName() {
        return fullName;
    }

    public void setFullName(int fullName) {
        this.fullName = fullName;
    }

    public int getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(int departmentName){this.departmentName = departmentName;}

    public int getEffectiveLimitGroup() {
        return effectiveLimitGroup;
    }

    public void setEffectiveLimitGroup(int effectiveLimitGroup) {
        this.effectiveLimitGroup = effectiveLimitGroup;
    }

    public int getFutureInactiveData() {
        return futureInactiveData;
    }

    public void setFutureInactiveData(int futureInactiveData) {
        this.futureInactiveData = futureInactiveData;
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

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "UserDefinition{" +
                "userId=" + userId +
                ", cardId=" + cardId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", fullName=" + fullName +
                ", DepartmentName=" + departmentName +
                ", title=" + title +
                ", effectiveLimitGroup=" + effectiveLimitGroup +
                ", futureInactiveData=" + futureInactiveData +
                ", cardId2=" + cardId2 +
                ", email=" + email +
                '}';
    }
}