package je.project.domain;

import java.sql.Timestamp;

/**
 * 维修表记录（实体类）
 * @author 刘海鑫
 */
public class Repair{
    Integer id;
    Integer engineerid;
    String scan;
    String repair;
    Timestamp time;
    String workload;
    String parts;
    Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEngineerid() {
        return engineerid;
    }

    public void setEngineerid(Integer engineerid) {
        this.engineerid = engineerid;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair [engineerid=" + engineerid + ", id=" + id + ", parts=" + parts + ", repair=" + repair + ", scan="
                + scan + ", status=" + status + ", time=" + time + ", workload=" + workload + "]";
    }
}