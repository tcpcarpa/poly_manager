package es.polytex.integracionback.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("cardId")
    private String cardId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("DepartmentName")
    private String departmentName;

    @JsonProperty("effectiveLimitGroup")
    private String effectiveLimitGroup;

    @JsonProperty("isDisabledOrDisabledDate")
    private String isDisabledOrDisabledDate;

    @JsonProperty("simpleOrExtendedModeQuantityBalance")
    private String simpleOrExtendedModeQuantityBalance;

    @JsonProperty("rfidModeItemNameGroupSHORTQuantitybalance")
    private String rfidModeItemNameGroupSHORTQuantitybalance;

    @JsonProperty("cardId2")
    private String cardId2;

    @JsonProperty("email")
    private String email;

    @JsonProperty("title")
    private String title;


    public Users() {
    }

    public Users(String userId, String cardId, String firstName, String lastName, String departmentName, String effectiveLimitGroup, String isDisabledOrDisabledDate, String simpleOrExtendedModeQuantityBalance, String rfidModeItemNameGroupSHORTQuantitybalance, String cardId2, String email, String title) {
        this.userId = userId;
        this.cardId = cardId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
        this.effectiveLimitGroup = effectiveLimitGroup;
        this.isDisabledOrDisabledDate = isDisabledOrDisabledDate;
        this.simpleOrExtendedModeQuantityBalance = simpleOrExtendedModeQuantityBalance;
        this.rfidModeItemNameGroupSHORTQuantitybalance = rfidModeItemNameGroupSHORTQuantitybalance;
        this.cardId2 = cardId2;
        this.email = email;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.departmentName = DepartmentName;
    }

    public String getEffectiveLimitGroup() {
        return effectiveLimitGroup;
    }

    public void setEffectiveLimitGroup(String effectiveLimitGroup) {
        this.effectiveLimitGroup = effectiveLimitGroup;
    }

    public String getIsDisabledOrDisabledDate() {
        return isDisabledOrDisabledDate;
    }

    public void setIsDisabledOrDisabledDate(String isDisabledOrDisabledDate) {
        this.isDisabledOrDisabledDate = isDisabledOrDisabledDate;
    }

    public String getSimpleOrExtendedModeQuantityBalance() {
        return simpleOrExtendedModeQuantityBalance;
    }

    public void setSimpleOrExtendedModeQuantityBalance(String simpleOrExtendedModeQuantityBalance) {
        this.simpleOrExtendedModeQuantityBalance = simpleOrExtendedModeQuantityBalance;
    }

    public String getRfidModeItemNameGroupSHORTQuantitybalance() {
        return rfidModeItemNameGroupSHORTQuantitybalance;
    }

    public void setRfidModeItemNameGroupSHORTQuantitybalance(String rfidModeItemNameGroupSHORTQuantitybalance) {
        this.rfidModeItemNameGroupSHORTQuantitybalance = rfidModeItemNameGroupSHORTQuantitybalance;
    }

    public String getCardId2() {
        return cardId2;
    }

    public void setCardId2(String cardId2) {
        this.cardId2 = cardId2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(userId, users.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}