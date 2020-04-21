package spring.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Objects;

@Entity
@TypeDef(name = "pgsql_enum", typeClass = spring.entity.PostgreSQLType.class)
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
    @NotBlank
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Basic
    @NotNull
    @Column(name = "birth_date", nullable = false)
    private Date birth_date;

    @Basic
    @NotBlank
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Basic
    @Pattern(regexp = "^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phone_number;

    @Basic
    @Column(name = "hire_date")
    private Date hire_date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "education_degree", nullable = false,
            columnDefinition = "enum('without_degree', 'bachelor', 'master', 'doctor')")
    private DegreeType education_degree;

    public Worker() {
        this.hire_date = Date.valueOf("1900-01-01");
    }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Worker other = (Worker) obj;
        return Objects.equals(worker_id, other.worker_id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(birth_date, other.birth_date) &&
                Objects.equals(address, other.address) &&
                Objects.equals(phone_number, other.phone_number) &&
                Objects.equals(hire_date, other.hire_date) &&
                Objects.equals(education_degree, other.education_degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker_id, name, birth_date, address, phone_number, hire_date, education_degree);
    }
}
