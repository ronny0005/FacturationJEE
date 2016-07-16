package Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class Util {

	public Util() {

	}

	public static String getJsonFromServer(String lien) throws IOException {
		final StringBuilder sb = new StringBuilder();
		InputStream is = (InputStream) new URL(lien).getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String result, line = reader.readLine();
		result = line;
		while((line=reader.readLine())!=null){
			result+=line;
		}
		return result;
		// read the JSON results into a string
	}


}