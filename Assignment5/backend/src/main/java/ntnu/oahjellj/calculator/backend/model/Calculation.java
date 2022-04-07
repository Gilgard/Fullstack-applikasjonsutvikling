package ntnu.oahjellj.calculator.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(	name = "calculation")
public class Calculation {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String equation;

    private String result;

    public Calculation() {}

    public Calculation(String equation, String result) {
        this.equation = equation;
        this.result = result;
    }

    public Long getId() {
        return this.id;
    }

    public String getEquation() {
        return this.equation;
    }

    public String getResult() {
        return this.result;
    }

    public String getResponseMessage() {
        return this.equation + " = " + this.result;
    }
}
