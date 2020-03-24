package entity;

import javax.persistence.*;

@Entity
@Table(name = "department", schema = "public", catalog = "personnel")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long department_id;

    @Basic
    @Column(name = "department_name", nullable = false, length = 40)
    private String department_name;

    @ManyToOne
    @JoinColumn(name = "head_department")
    private Department head_department;

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
}