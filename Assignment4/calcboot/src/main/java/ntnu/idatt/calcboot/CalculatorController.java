package ntnu.idatt.calcboot;

import java.text.DecimalFormat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.zalando.logbook.Logbook;

@CrossOrigin
@RestController
public class CalculatorController {
	Logbook logbook = Logbook.create();

	@PostMapping("/calculator")
	public String calculate(@RequestParam String equation) {
		String[] parts = equation.split(" ");
		int firstNumber = Integer.parseInt(parts[0]);
		String symbol = parts[1];
		int secondNumber = Integer.parseInt(parts[2]);
	
		switch(symbol){
			case "+":
				return (firstNumber + secondNumber) + "";

			case "-":
				return (firstNumber - secondNumber) + "";

			case "*":
				return (firstNumber * secondNumber) + "";
				
			case "/":
				if(secondNumber == 0){
					return "nAn";
				}

				double result = ((double)firstNumber / (double)secondNumber);
				
				return removeZero(result) + "";

			default:
				return "Something went wrong in the calculation process.";
		}
	}

	private String removeZero(double result) {
		DecimalFormat format = new DecimalFormat("#.#");
		return format.format(result);
	}
}
