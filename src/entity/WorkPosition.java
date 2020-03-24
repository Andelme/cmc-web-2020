package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work_position", schema = "public", catalog = "personnel")
public class WorkPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long position_id;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker_id;

    @ManyToOne
    @JoinColumn(name = "postype_id", nullable = false)
    private PositionType postype_id;

    @Basic
    @Column(name = "appointment_date")
    private Date appointment_date;

    @Basic
    @Column(name = "retire_date")
    private Date retire_date;

    @Basic
    @Column(name = "work_rate", nullable = false, precision = 4)
    private Double work_rate;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department_id;

    public WorkPosition() {}
    public WorkPosition(Worker worker_id, PositionType postype_id, Date appointment_date, Date retire_date, Double work_rate, Department department_id) {
        this.worker_id = worker_id;
        this.postype_id = postype_id;
        this.appointment_date = appointment_date;
        this.retire_date = retire_date;
        this.work_rate = work_rate;
        this.department_id = department_id;
    }

    public void setPosition_id(Long position_id) {
        this.position_id = position_id;
    }
    public Long getPosition_id() {
        return position_id;
    }

    public void setWorker_id(Worker worker_id) {
        this.worker_id = worker_id;
    }
    public Worker getWorker_id() {
        return worker_id;
    }

    public void setPostype_id(PositionType postype_id) {
        this.postype_id = postype_id;
    }
    public PositionType getPostype_id() {
        return postype_id;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }
    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setRetire_date(Date retire_date) {
        this.retire_date = retire_date;
    }
    public Date getRetire_date() {
        return retire_date;
    }

    public void setWork_rate(Double work_rate) {
        this.work_rate = work_rate;
    }
    public Double getWork_rate() {
        return work_rate;
    }

    public void setDepartment_id(Department department_id) {
        this.department_id = department_id;
    }
    public Department getDepartment_id() {
        return department_id;
    }
}