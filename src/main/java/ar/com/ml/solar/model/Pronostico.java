package ar.com.ml.solar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pronostico", schema = "solar")
public class Pronostico {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;
    
	Long dia;
    String clima;

    
    public int getId() {
        return id;
    }

	public void setId(int id) {
        this.id = id;
    }

	public Long getDia() {
		return dia;
	}

	public void setDia(Long dia) {
		this.dia = dia;
	}

	public String getClima()	{
		return this.clima;
	}
	
	public void setClima(String clima) {
		this.clima = clima;
	}

}
