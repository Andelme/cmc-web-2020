package entity;

import javax.persistence.*;

@Entity
@Table(name = "position_type", schema = "public", catalog = "personnel")
public class PositionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postype_id")
    private Long postype_id;

    @Basic
    @Column(name = "postype_name", nullable = false, length = 40)
    private String postype_name;

    @Basic
    @Column(name = "responsibilities", nullable = false, length = 800)
    private String responsibilities;

    @Basic
    @Column(name = "salary", nullable = false)
    private Integer salary;

    public PositionType() {}
    public PositionType(String postype_name, String responsibilities, Integer salary) {
        this.postype_name = postype_name;
        this.responsibilities = responsibilities;
        this.salary = salary;
    }

    public void setPostype_id(Long postype_id) {
        this.postype_id = postype_id;
    }
    public Long getPostype_id() {
        return postype_id;
    }

    public void setPostype_name(String postype_name) {
        this.postype_name = postype_name;
    }
    public String getPostype_name() {
        return postype_name;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    public String getResponsibilities() {
        return responsibilities;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public Integer getSalary() {
        return salary;
    }
}
