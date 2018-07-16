package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name;
	@Persistent
	private String date;
	@Persistent
	private boolean status;
	public Role(String name, boolean status) {
		super();
		this.name = name;
		Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd MMMMM yyyy hh:mm aaa");
		this.date = formateador.format(ahora);
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd MMMMM yyyy hh:mm aaa");
		this.date = formateador.format(date);
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
