package je.project.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

//id,cost,materialcost,promise,note,time
public class Cost {
    private Integer id;
    private BigDecimal cost;
    private BigDecimal materialcost;
    private String promise;
    private String note;
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getMaterialcost() {
        return materialcost;
    }

    public void setMaterialcost(BigDecimal materialcost) {
        this.materialcost = materialcost;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", cost=" + cost +
                ", materialcost=" + materialcost +
                ", promise='" + promise + '\'' +
                ", note='" + note + '\'' +
                ", time=" + time +
                '}';
    }
}
