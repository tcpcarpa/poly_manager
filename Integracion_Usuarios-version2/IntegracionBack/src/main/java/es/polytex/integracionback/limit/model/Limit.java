package es.polytex.integracionback.limit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Limit {
    @Id
    private String departmentName;
    private String title;
    private String effectiveLimitGroup;
    private String site_id;

    public Limit() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEffectiveLimitGroup() {
        return effectiveLimitGroup;
    }

    public void setEffectiveLimitGroup(String effectiveLimitGroup) {
        this.effectiveLimitGroup = effectiveLimitGroup;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }
}

