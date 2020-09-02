package je.project.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
* id  编号
*name  名称
*stype   型号
*price   价格
*num  数量
*time  入库时间
*warnnum   警戒数量
*status   库存状态
 */
public class Hub {
	private  Integer id;
	private String name;
	private String stype;
	private BigDecimal  price;
	private Integer  num;
	private Timestamp time;
	private Integer warnnum;
	private Integer status;
	
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
	public void setNum(Integer num) {
		this.num = num;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getWarnnum() {
		return warnnum;
	}
	public void setWarnnum(Integer warnnum) {
		this.warnnum = warnnum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "spare [id=" + id + ", name=" + name + ", stype=" + stype + ", price=" + price + ", num=" + num
				+ ", time=" + time + ", warnnum=" + warnnum + ", status=" + status + "]";
	}
}
