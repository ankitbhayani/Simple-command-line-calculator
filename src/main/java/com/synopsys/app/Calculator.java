

package com.synopsys.app;

import org.apache.log4j.Logger;

public class Calculator {
	
	private static final String ADD = "add";
	private static final String SUB = "sub";
	private static final String MULT = "mult";
	private static final String DIV = "div";
	private static final String LET = "let";
	private static final String illegalArgumentMessage = "Input arguments are not in proper order. Correct the input format: java Calculator \"add(1, 2)\"";

	final static Logger logger = Logger.getLogger(Calculator.class);
	
	/* 1. Main method */
	public static void main(String[] args) {

		/* Input arguments length check conditions */
		try {
			if (args.length < 1 || args.length > 1) {
				throw new IllegalArgumentException(illegalArgumentMessage);
			}
		} catch (Exception e) {
			logger.error(e);
			return;
		}

		Calculator calcObj = new Calculator();
		/* Removing unnecessary spaces from the input string */
		String input = args[0].replaceAll("\\s", "");
		calcObj.checkInitialSyntax(input);

	}

	/* 2. Initial Syntax checking of an expression */
	private void checkInitialSyntax(String expr) {

		try {
			if (expr.matches("[a-zA-z]+")) {

			} else if (isNumeric(expr)) {

			} else if (expr.startsWith(ADD)) {
				singleExpressionSyntaxCheck(expr, ADD);

			} else if (expr.startsWith(SUB)) {
				System.out.println("Inside ADD");

			} else if (expr.startsWith(MULT)) {
				System.out.println("Inside ADD");

			} else if (expr.startsWith(DIV)) {
				System.out.println("Inside ADD");

			} else if (expr.startsWith(LET)) {
				System.out.println("Inside ADD");

			} else {
				throw new IllegalArgumentException("unknown operation provided -- need add/sub/mult/div/let");
			}

			if (!checkMatchedParantheses(expr))
				throw new IllegalArgumentException("Paranthesis not matching");

		} catch (Exception e) {
			logger.error(e);
			return;
		}

	}

	/* 3. Check if the expression is numeric in nature */
	private static boolean isNumeric(String expr) {
		String eval = expr;
		if (expr.startsWith("-")) {
			eval = expr.substring(1, expr.length());
		}

		for (Character c : eval.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	/* 4. Method checks for matching parentheses in given expression */
	private boolean checkMatchedParantheses(String expr) {
		try {
			int paranCounter = 0;
			for (int i = 0; i < expr.length(); i++) {

				if (expr.charAt(i) == '(')
					paranCounter++;

				if (expr.charAt(i) == ')') {
					if (paranCounter == 0)
						throw new IllegalArgumentException(illegalArgumentMessage);
					paranCounter--;
				}

			}

			return paranCounter == 0;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}
	
	/* 5. Syntax check for each of the two expressions for add/sub/mult/div */
	private void singleExpressionSyntaxCheck(String expression, String operation) {

		checkBeginParentheses(expression, operation.length());

		int commaPos = checkMatchedParansAndReturnNextDelim(expression, operation.length() + 1, ',');
		String expr1 = expression.substring(operation.length() + 1, commaPos);
		checkInitialSyntax(expr1);

		int endPos = checkMatchedParansAndReturnNextDelim(expression, commaPos + 1, ')');
		assert (endPos == expression.length() - 1);
		String expr2 = expression.substring(commaPos + 1, endPos);
		checkInitialSyntax(expr2);
	}

	/* 6. To check if there is a parentheses at the beginning */
	private static void checkBeginParentheses(String exoression, int prefix) {
		try {
			if (!exoression.startsWith("(", prefix)) {
				throw new IllegalArgumentException(illegalArgumentMessage);
			}
		} catch (Exception e) {
			logger.error(e);
			return;
		}
	}

	/* 7. Method checks for matching parentheses starting at input prefix
	 * 
	 * @param expr - input string
	 * 
	 * @param prefix
	 * 
	 * @return index after the parentheses match E.g.: add(add(1,2), 3) then
	 * prefix: 4 and should return: 12
	 */
	private static int checkMatchedParansAndReturnNextDelim(String expression, int prefix, Character delimiter) {

		if (logger.isDebugEnabled()) {
			logger.debug("String expession = " + expression);
			logger.debug("prefix value = " + prefix);
			logger.debug("delimiter = " + delimiter);
		}
		int i = prefix;
		try {
			int paranCounter = 0;
			for (; i < expression.length(); i++) {

				if (paranCounter == 0 && expression.charAt(i) == delimiter)
					return i;

				if (expression.charAt(i) == '(')
					paranCounter++;

				if (expression.charAt(i) == ')') {
					if (paranCounter == 0)
						throw new IllegalArgumentException(illegalArgumentMessage);
					paranCounter--;
				}

			}

			if (paranCounter > 0)
				throw new IllegalArgumentException(illegalArgumentMessage);

		} catch (Exception e) {
			logger.error(e);
		}
		return i;
	}
		
}
