package ntnu.oahjellj.calculator.backend.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ntnu.oahjellj.calculator.backend.model.Calculation;
import ntnu.oahjellj.calculator.backend.model.User;
import ntnu.oahjellj.calculator.backend.payload.request.CalculateRequest;
import ntnu.oahjellj.calculator.backend.payload.request.HistoryRequest;
import ntnu.oahjellj.calculator.backend.payload.response.HistoryResponse;
import ntnu.oahjellj.calculator.backend.payload.response.MessageResponse;
import ntnu.oahjellj.calculator.backend.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/calc")
public class CalcController {

    @Autowired
	UserRepository userRepository;

    @PostMapping("/history")
	public ResponseEntity<?> getUserHistory(@Valid @RequestBody HistoryRequest historyRequest) {
        String reqUsername = historyRequest.getUsername();

        if (!userRepository.existsByUsername(reqUsername)) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: No user found with the username: " + reqUsername));
        }

        User user = userRepository.findByUsername(reqUsername).get();
        Set<Calculation> history = user.getHistory();
        List<String> sendableList = new ArrayList<>();

        if (history == null) {
            return ResponseEntity.ok(new HistoryResponse(sendableList));
        }

        for (Calculation calculation : history) {
            sendableList.add(calculation.getResponseMessage());
        }
        return ResponseEntity.ok(new HistoryResponse(sendableList));
    }

    @PostMapping("/calc")
    public ResponseEntity<?> calculate(@Valid @RequestBody CalculateRequest calculateRequest) {
        String reqUsername = calculateRequest.getUsername();

        if (!userRepository.existsByUsername(reqUsername)) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: No user found with the username: " + reqUsername));
        }

        User user = userRepository.findByUsername(reqUsername).get();
        Set<Calculation> history = user.getHistory();

        String equation = calculateRequest.getEquation();
        String result = calc(equation);
        Calculation calculation = new Calculation(equation, result);

        history.add(calculation);

        return ResponseEntity.ok(new MessageResponse(calculation.getResponseMessage()));
    }

    private String calc(String equation) {
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
