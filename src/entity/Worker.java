package entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@TypeDef(name = "pgsql_enum", typeClass = entity.PostgreSQLType.class)
@Table(name = "worker", schema = "public", catalog = "personnel")
public class Worker {
    public enum DegreeType {
        without_degree,
        bachelor,
        master,
        doctor
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long worker_id;

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Basic
    @Column(name = "birth_date", nullable = false)
    private Date birth_date;

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Basic
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phone_number;

    @Basic
    @Column(name = "hire_date", nullable = false)
    private Date hire_date;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "education_degree", nullable = false,
            columnDefinition = "enum('without_degree', 'bachelor', 'master', 'doctor')")
    private DegreeType education_degree;

    public Worker() {}
    public Worker(String name, Date birth_date, String address,
                  String phone_number, Date hire_date, DegreeType education_degree) {
        this.name = name;
        this.birth_date = birth_date;
        this.address = address;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.education_degree = education_degree;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
    }
    public Long getWorker_id() {
        return worker_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public Date getBirth_date() {
        return birth_date;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getPhone_number() {
        return phone_number;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }
    public Date getHire_date() {
        return hire_date;
    }

    public void setEducation_degree(DegreeType education_degree) {
        this.education_degree = education_degree;
    }
    public DegreeType getEducation_degree() {
        return education_degree;
    }
}
