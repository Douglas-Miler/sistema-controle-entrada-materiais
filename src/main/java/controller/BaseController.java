package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseController {

	public String name;
	public String number;

	public boolean inputNumberValidator(String number) {
		String nonNumbersCharacters = "[^0-9]";
		Pattern pattern = Pattern.compile(nonNumbersCharacters);
		Matcher matcher = pattern.matcher(number);
		number = number.trim();

		if (!number.isEmpty())
			while (matcher.find())
				return false;
		else
			return false;

		this.number = number;
		return true;
	}

	public boolean inputNumberFloatingPointValidator(String number) {

		number = number.trim();

		if (!number.isEmpty()) {

			if (number.contains(","))
				number = number.replace(",", ".");

			String stringPattern = "[0-9]+((.|,)[0-9]{2})?";
			Pattern pattern = Pattern.compile(stringPattern);
			Matcher matcher = pattern.matcher(number);

			if (matcher.matches()) {
				this.number = number;
				return true;
			}
		}
		return false;
	}

	public boolean inputStringFieldHandler(String name) {

		String nonWordsCharacters = "[^A-Za-z|ãÃáÁéÉêÊíÍôÔúÚçÇ\\s]";
		Pattern pattern = Pattern.compile(nonWordsCharacters);
		name = name.trim();

		if (!name.isEmpty()) {
			name = name.replaceAll("\\s+", " ");
			Matcher matcher = pattern.matcher(name);
			while (matcher.find())
				return false;

			this.name = name;
			return true;
		}
		return false;
	}
}
