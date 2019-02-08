package cx.sfy.tes3finder.map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage getRawImage(int rx, int rz) {
		return getImage(rx * 8092 + 4048, rz * 8092 + 4048);
	}

	public static BufferedImage getImage(int x, int z) {
		try {

			String rurl = "https://image.thum.io/get/width/500/"
					+ "https://mwmap.uesp.net/?locx=" + x + "&locy=" + z + "&zoom=19&format=jpg&width=500";


			return ImageIO.read(getInputStream(rurl));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	private static InputStream getInputStream(String url) {

		HttpURLConnection cnn;
		try {
			URL ur = new URL(url);
			cnn = (HttpURLConnection) ur.openConnection();
			cnn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			cnn.addRequestProperty("Referer", "google.com");
			cnn.connect();
			String header = cnn.getHeaderField("location");
			if (header != null) {
				HttpURLConnection redur = (HttpURLConnection) new URL(header.replaceAll("http", "https"))
						.openConnection();
				redur.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
				redur.connect();

				return redur.getInputStream();
			} else {
				return cnn.getInputStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
