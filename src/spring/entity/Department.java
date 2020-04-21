package spring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "department", schema = "public", catalog = "personnel")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long department_id;

    @Basic
    @NotBlank
    @Column(name = "department_name", nullable = false, length = 40)
    private String department_name;

    @ManyToOne
    @JoinColumn(name = "head_department")
    private Department head_department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "head_department")
    private Set<Department> child_departments;

    public Department() {}
    public Department (String department_name, Department head_department) {
        this.department_name = department_name;
        this.head_department = head_department;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    public String getDepartment_name() {
        return department_name;
    }

    public void setHead_department(Department head_department) {
        this.head_department = head_department;
    }
    public Department getHead_department() {
        return head_department;
    }

    public void setChild_departments(Set<Department> child_departments) {
        this.child_departments = child_departments;
    }
    public Set<Department> getChild_departments() {
        return child_departments;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department other = (Department) obj;
        return Objects.equals(department_id, other.department_id) &&
                Objects.equals(department_name, other.department_name) &&
                Objects.equals(head_department, other.head_department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department_id, department_name, head_department);
    }
}