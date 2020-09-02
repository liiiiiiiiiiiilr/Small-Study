package je.project.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Report {
    Integer id;
    Integer uid;
    Integer type;//产品类型1台式机2笔记本3投影仪4打印机5其他
    String brand;
    String model;
    String serialnum;
    String lack;
    String fault;
    Integer faluttype;
    String apper;
    String password;
    String important;
    String hdd;
    String memory;
    String outerpc;
    String ac;
    String battery;
    String cdrom;
    String soft;
    String other;
    Timestamp time;
    BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Report(Integer id, Integer uid, Integer type, String brand, String model, String serialnum, String lack, String fault, Integer faluttype, String apper, String password, String important, String hdd, String memory, String outerpc, String stringac, String battery, String cdrom,
                  String soft, String other, Timestamp time, BigDecimal price, int status) {
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.serialnum = serialnum;
        this.lack = lack;
        this.fault = fault;
        this.faluttype = faluttype;
        this.apper = apper;
        this.password = password;
        this.important = important;
        this.hdd = hdd;
        this.memory = memory;
        this.outerpc = outerpc;
        this.ac = stringac;
        this.battery = battery;
        this.cdrom = cdrom;
        this.soft = soft;
        this.other = other;
        this.time = time;
        this.price = price;
        this.status = status;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getCdrom() {
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom;
    }

    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getLack() {
        return lack;
    }

    public void setLack(String lack) {
        this.lack = lack;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public Integer getFaluttype() {
        return faluttype;
    }

    public void setFaluttype(Integer faluttype) {
        this.faluttype = faluttype;
    }

    public String getApper() {
        return apper;
    }

    public void setApper(String apper) {
        this.apper = apper;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getOuterpc() {
        return outerpc;
    }

    public void setOuterpc(String outerpc) {
        this.outerpc = outerpc;
    }

    int status;//0未打印1打印2提交

    @Override
    public String toString() {
        return "Report [Stringac=" + ac + ", apper=" + apper + ", battery=" + battery + ", brand=" + brand
                + ", cdrom=" + cdrom + ", faluttype=" + faluttype + ", fault=" + fault + ", hdd=" + hdd + ", id=" + id
                + ", important=" + important + ", lack=" + lack + ", memory=" + memory + ", model=" + model + ", other="
                + other + ", outerpc=" + outerpc + ", password=" + password + ", price=" + price + ", serialnum="
                + serialnum + ", soft=" + soft + ", status=" + status + ", time=" + time + ", type=" + type + ", uid="
                + uid + "]";
    }

    public Report() {
    }
}
