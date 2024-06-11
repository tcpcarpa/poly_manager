package es.polytex.integracionback.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
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

    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("DepartmentName")
    private String departmentName;
    @JsonProperty("title")
    private String title;
    @JsonProperty("effectiveLimitGroup")
    private String effectiveLimitGroup;
    @JsonProperty("futureInactive")
    private String futureInactive;
    @JsonProperty("cardId2")
    private String cardId2;
    @JsonProperty("email")
    private String email;
    @JsonProperty("site_id")
    private String site_id;
    @JsonProperty("favoritesSubTypes")
    private List<Integer> favoritesSubTypes;

    public Users() {
    }


    public String getKeyForComparison() {
        return this.userId + this.fullName;
    }
    public boolean hasNonNullValues() {
        return userId != null || cardId != null || fullName != null ||
                departmentName != null || title != null || effectiveLimitGroup != null;
    }

    public int getNumAttributes() {
        int numAttributes = 0;
        if (this.userId != null && !this.userId.isEmpty()) {
            numAttributes++;
        }
        if (this.cardId != null && !this.cardId.isEmpty()) {
            numAttributes++;
        }
        if (this.firstName != null && !this.firstName.isEmpty()) {
            numAttributes++;
        }
        return numAttributes;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFutureInactive() {
        return futureInactive;
    }

    public void setFutureInactive(String futureInactive) {
        this.futureInactive = futureInactive;
    }

    public List<Integer> getFavoritesSubTypes() {
        return favoritesSubTypes;
    }

    public void setFavoritesSubTypes(List<Integer> favoritesSubTypes) {
        this.favoritesSubTypes = favoritesSubTypes;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
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