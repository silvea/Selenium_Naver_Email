package selenium_;

import java.util.regex.Pattern;

public class Value {
	public boolean emt_check(String value) {
		if(value.length() == 0) {
			return false;
		}
		return true;
	}
	
	public boolean email_check(String email) {
		boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
		return b;
	}
}
