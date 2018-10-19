package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String link) throws IOException, JSONException {
		URL url = new URL(link);
		URLConnection urlConn = url.openConnection();
		urlConn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
		InputStream is = urlConn.getInputStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		// JSONObject json = readJsonFromUrl("https://pokeapi.co/api/v2/pokemon/1/");
		// System.out.println(json.toString());
		// System.out.println(json.get("name"));

		JSONObject json = readJsonFromUrl(
				"http://confiraloterias.com.br/api0/json.php?loteria=megasena&token=PCCkY33kFrfQBFv");
		System.out.println(json.toString());
		System.out.println(json.get("mega_virada_valor_acumulado"));
	}
}
