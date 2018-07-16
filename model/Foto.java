package model;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Foto {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	@Persistent
	private String name;
	@Persistent
	private String description;
	@Persistent
	private String photo;
	@Persistent
	private String date;
	
	public Foto(String nombre, String descripcion, String imagen) {
		this.name = nombre;
		this.description = descripcion;
		this.photo = imagen;
		Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd MMMMM yyyy hh:mm aaa");
		this.date = formateador.format(ahora);
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long id) {
		this.key = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
