package ntnu.oahjellj.calculator.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "calculations")
public class Calculation {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String equation;

    private String result;

    @ManyToOne
    private User user;

    public Calculation() {}

    public Calculation(String equation, String result, User user) {
        this.equation = equation;
        this.result = result;
        this.user = user;
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
