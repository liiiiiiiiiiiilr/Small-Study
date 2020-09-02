package je.project.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
* id  编号
*name  名称
*stype   型号
*price   单价
*num  数量
*time  出库时间
*rid  维修编号
 */
public class Detail {
	private Integer id;
	private String name;
	private String stype;
	private BigDecimal  price;
	private Integer  num;
	private Timestamp time;
	private Integer rid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "detail [id=" + id + ", name=" + name + ", stype=" + stype + ", price=" + price + ", num=" + num
				+ ", time=" + time + ", rid=" + rid + "]";
	}


}
