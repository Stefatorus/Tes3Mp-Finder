package cx.sfy.tes3finder.map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import cx.sfy.tes3finder.LocationManager;
import cx.sfy.tes3finder.Main;
import cx.sfy.tes3finder.utils.ThreadUtils;

public class MapGenerator {

	private static Thread thr;
	private static JFrame j = new JFrame();
	private static Graphics g;
	
	public static void Enabler() {
		j.setUndecorated(true);
		
		Dimension max = Toolkit.getDefaultToolkit().getScreenSize();
		
		int min = (int) Math.min(max.getHeight(), max.getWidth());
		
		j.setSize(min/4, min/4);
		
		j.setLocation((int) max.getWidth()-min/3, (int) (max.getHeight()/2.5));
		
		
		thr = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					
					if (!Main.showmap) {
						ThreadUtils.sleep(1000);
						continue;
					}
					
					
					long curr = System.currentTimeMillis();
					
					int coords[] = LocationManager.getCoordonate(Main.target);
					
					BufferedImage shower = ImageUtils.getRawImage(coords[0], coords[1]);
					
					if (g == null || shower == null) {
						System.out.println("Nullino");
						ThreadUtils.sleep(10000);
						continue;
					}
					
					int xdiff = (int) (shower.getWidth()/2.5);
					int ydiff = (int) (shower.getHeight()/2.5);
					
					shower = shower.getSubimage(xdiff, ydiff, shower.getWidth()-xdiff, shower.getHeight()-ydiff);
					
					g.drawImage(shower, 0, 0, j.getWidth(), j.getHeight(),  null);
					
					long diff = System.currentTimeMillis() - curr;
					
					if (diff < Main.refreshrate*1000) {
						ThreadUtils.sleep(Main.refreshrate*1000-diff);
					}
					
				}
				
			}
		});
		
		thr.start();

	}
	
	public static void toggleMap(boolean activate) {
			j.setVisible(activate);
			
			if (activate) {
				g = j.getGraphics();
			}
	}
	
}
