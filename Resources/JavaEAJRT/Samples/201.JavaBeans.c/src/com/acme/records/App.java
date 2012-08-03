package com.acme.records;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) {
		BeanRecord r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven",
				"dude", "haydn");
		BeanRecord r2 = new BeanRecord("coyote@acme.com", "Wiley Coyote",
				"coyote", "lovebird");
		BeanRecord r3 = new BeanRecord("runner@acme.com", "Road Runner",
				"runner", "meepbeep");

		VJPanelBeanRecord panel = new VJPanelBeanRecord();
		panel.setModel(r1);
		JFrame frame = new JFrame();
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
