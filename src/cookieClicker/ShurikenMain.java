package cookieClicker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import cookieClicker.Music;

public class ShurikenMain {

	JLabel counterLabel, perSecLabel, title;
	JButton button1, button2, button3, button4;
	int shurikenCounter, timerSpeed, cursorNumber, cursorPrice, blacksmithPrice, blacksmithNumber, smithyPrice,
			smithyNumber, sakePrice, sakeNumber;
	double perSecond;
	boolean timerOn, blacksmithUnlocked, smithyUnlocked, sakeUnlocked;
	Font font1, font2;
	ShurikenHandler cHandler = new ShurikenHandler();
	Timer timer;
	JTextArea messageText;
	MouseHandler mHandler = new MouseHandler();

	public static void main(String[] args) {

		new ShurikenMain();

	}

	public ShurikenMain() {

		timerOn = false;
		blacksmithUnlocked = false;
		smithyUnlocked = false;
		sakeUnlocked = false;
		perSecond = 0;
		shurikenCounter = 0;
		cursorNumber = 0;
		blacksmithNumber = 0;
		cursorPrice = 10;
		blacksmithPrice = 50;
		smithyPrice = 100;
		smithyNumber = 0;
		sakePrice = 200;
		sakeNumber = 0;

		createFont();
		createUI();
		
		Music sound = new Music();
        sound.play();
		

	}

	

	public void createFont() {

		font1 = new Font("Maku", Font.BOLD, 32);
		font2 = new Font("Comic Sans MS", Font.PLAIN, 15);

	}
	
	

	public void createUI() {

		try {
			BufferedImage img = ImageIO
					.read(new File("/Users/oskarivilen/eclipse-workspace/cookieClicker/res/house.jpg"));

			JFrame window = new JFrame();
			window.setSize(800, 600);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setContentPane(new JLabel(new ImageIcon(img)));
			window.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;

			
			
			
			
			window.setLayout(null);

			JPanel shurikenPanel = new JPanel();
			shurikenPanel.setBounds(100, 200, 200, 215);
			shurikenPanel.setBorder(null);
			shurikenPanel.setBackground(Color.white);
			shurikenPanel.setOpaque(false);

			window.add(shurikenPanel);

			ImageIcon shurikens = new ImageIcon(getClass().getClassLoader().getResource("shuriken.png"));

			JButton shurikenButton = new JButton();
			shurikenButton.setBackground(Color.white);
			shurikenButton.setFocusPainted(false);
			shurikenButton.setOpaque(false);

			shurikenButton.setBorder(null);
			shurikenButton.setIcon(shurikens);
			shurikenButton.addActionListener(cHandler);
			shurikenButton.setActionCommand("shuriken");
			shurikenPanel.add(shurikenButton);

			JPanel titlePanel = new JPanel();
			titlePanel.setBounds(50, 0, 400, 100);
			titlePanel.setBackground(new Color(5, 2, 3, 1));
			titlePanel.setLayout(new GridLayout(2, 1));
			window.add(titlePanel);

			title = new JLabel("Shuriken Shop");
			title.setForeground(Color.white);

			title.setFont(font1);
			titlePanel.add(title);

			JPanel counterPanel = new JPanel();
			counterPanel.setBounds(100, 100, 200, 100);
			counterPanel.setBackground(new Color(224, 139, 62));

			counterPanel.setLayout(new GridLayout(2, 1));
			window.add(counterPanel);

			counterLabel = new JLabel(shurikenCounter + " shurikens");
			counterLabel.setForeground(Color.black);

			counterLabel.setFont(font1);
			counterPanel.add(counterLabel);

			perSecLabel = new JLabel();
			perSecLabel.setForeground(Color.black);
			perSecLabel.setFont(font2);
			counterPanel.add(perSecLabel);

			JPanel itemPanel = new JPanel();
			itemPanel.setBounds(500, 170, 250, 250);
			itemPanel.setBackground(new Color(5, 2, 3, 1));
			itemPanel.setLayout(new GridLayout(4, 1));
			window.add(itemPanel);

			button1 = new JButton("Apprentice");
			button1.setFont(font1);
			button1.setFocusPainted(false);
			button1.setForeground(new Color(224, 139, 62));
			button1.addActionListener(cHandler);
			button1.setActionCommand("Apprentice");
			button1.addMouseListener(mHandler);
			itemPanel.add(button1);

			button2 = new JButton("?");
			button2.setFont(font1);
			button2.setFocusPainted(false);
			button2.addActionListener(cHandler);
			button2.setForeground(new Color(181, 101, 29));
			button2.setActionCommand("Blacksmith");
			button2.addMouseListener(mHandler);
			itemPanel.add(button2);

			button3 = new JButton("?");
			button3.setFont(font1);
			button3.setFocusPainted(false);
			button3.setForeground(new Color(137, 76, 22));
			button3.addActionListener(cHandler);
			button3.setActionCommand("Forge");
			button3.addMouseListener(mHandler);
			itemPanel.add(button3);

			button4 = new JButton("?");
			button4.setFont(font1);
			button4.setFocusPainted(false);
			button3.setForeground(new Color(71, 39, 11));
			button4.addActionListener(cHandler);
			button4.setActionCommand("Sake");
			button4.addMouseListener(mHandler);
			itemPanel.add(button4);

			JPanel messagePanel = new JPanel();
			messagePanel.setBounds(500, 70, 250, 150);
			messagePanel.setBackground(new Color(137, 76, 22));
			window.add(messagePanel);

			messageText = new JTextArea();
			messageText.setBounds(500, 70, 250, 150);
			messageText.setForeground(Color.white);
			messageText.setBackground(new Color(137, 76, 22));
			messageText.setFont(font2);
			messageText.setLineWrap(true);
			messageText.setWrapStyleWord(true);
			messageText.setEditable(false);
			messagePanel.add(messageText);

			window.setVisible(true);

		} catch (IOException exp) {
			exp.printStackTrace();
		}

	}

	public void setTimer() {

		timer = new Timer(timerSpeed, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shurikenCounter++;
				counterLabel.setText(shurikenCounter + " shurikens");

				if (blacksmithUnlocked == false) {

					if (shurikenCounter >= 100) {
						blacksmithUnlocked = true;
						button2.setText("Blacksmith " + "(" + blacksmithNumber + ")");
					}
				}
				if (smithyUnlocked == false) {
					if (shurikenCounter >= 250) {
						smithyUnlocked = true;
						button3.setText("Forge " + "(" + smithyNumber + ")");
					}

				}
				if (sakeUnlocked == false) {
					if (shurikenCounter >= 500) {
						sakeUnlocked = true;
						button4.setText("Sake " + "(" + sakeNumber + ")");
					}

				}

			}
		});
	}

	public void timerUpdate() {

		if (timerOn == false) {
			timerOn = true;
		}

		else if (timerOn == true) {
			timer.stop();
		}
		double speed = 1 / perSecond * 1000;
		timerSpeed = (int) Math.round(speed);

		String s = String.format("%.1f", perSecond);
		perSecLabel.setText("per second: " + s);

		setTimer();
		timer.start();

	}

	public class ShurikenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			String action = event.getActionCommand();

			switch (action) {
			case "shuriken":
				shurikenCounter++;
				counterLabel.setText(shurikenCounter + " shurikens");
				break;
			case "Apprentice":
				if (shurikenCounter >= cursorPrice) {
					shurikenCounter = shurikenCounter - cursorPrice;
					cursorPrice = cursorPrice + 5;
					counterLabel.setText(shurikenCounter + " shurikens");

					cursorNumber++;

					button1.setText("Apprentice" + "(" + cursorNumber + ")");
					messageText
							.setText("Cursor \n[price: " + cursorPrice + "]Produces shuriken once every 10 seconds.");
					perSecond = perSecond + 0.1;
					timerUpdate();
				}

				else {
					messageText.setText("You need more shurikens!");
				}
				break;
			case "Blacksmith":
				if (shurikenCounter >= blacksmithPrice) {
					shurikenCounter = shurikenCounter - blacksmithPrice;
					blacksmithPrice = blacksmithPrice + 50;
					counterLabel.setText(shurikenCounter + " shurikens");

					blacksmithNumber++;

					button2.setText("Blacksmith" + "( " + blacksmithNumber + ")");
					messageText.setText("Blacksmith\n[price: " + blacksmithPrice
							+ "]\nEach Blacksmith produces 1 shuriken per second");
					perSecond = perSecond + 1;
					timerUpdate();
				} else {
					messageText.setText("You need more shurikens!");
				}
				break;
			case "Forge":
				if (shurikenCounter >= smithyPrice) {
					shurikenCounter = shurikenCounter - smithyPrice;
					smithyPrice = smithyPrice + 100;
					counterLabel.setText(shurikenCounter + " shurikens");

					smithyNumber++;

					button3.setText("Forge" + "( " + smithyNumber + ")");
					messageText
							.setText("Forge\n[price: " + smithyPrice + "]\nEach Forge produces 5 shuriken per second");
					perSecond = perSecond + 5;
					timerUpdate();
				} else {
					messageText.setText("You need more shurikens!");
				}
				break;
			case "Sake":
				if (shurikenCounter >= sakePrice) {
					shurikenCounter = shurikenCounter - sakePrice;
					sakePrice = sakePrice + 200;
					counterLabel.setText(shurikenCounter + " shurikens");

					sakeNumber++;
					int drunk = ThreadLocalRandom.current().nextInt(5, 20 + 1);

					button4.setText("Sake" + "( " + sakeNumber + ")");
					messageText.setText("Sake\n[price: " + sakePrice
							+ "]\nEach Sake may produces 5 - 20 shuriken per second depening on worker sobriety");
					perSecond = perSecond + drunk;
					timerUpdate();
				} else {
					messageText.setText("You need more shurikens!");
				}
				break;
			}

		}
	}

	public class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton button = (JButton) e.getSource();

			if (button == button1) {
				messageText
						.setText("Apprentice \n[price: " + cursorPrice + "]\nProduces shuriken once every 10 seconds.");
			} else if (button == button2) {
				if (blacksmithUnlocked == false) {
					messageText.setText("this is item is currently locked");
				} else {
					messageText.setText("Blacksmith\n[price: " + blacksmithPrice
							+ "]\nEach blacksmith produces 1 shuriken per second");
				}
			} else if (button == button3) {
				if (smithyUnlocked == false) {
					messageText.setText("this is item is currently locked");
				} else {
					messageText
							.setText("Forge\n[price: " + smithyPrice + "]\nEach Forge produces 5 shurikens per second");
				}
			} else if (button == button4) {
				if (sakeUnlocked == false) {
					messageText.setText("this is item is currently locked");
				} else {
					messageText.setText("Sake\n[price: " + sakePrice
							+ "]\nEach Sake may produces 5 - 20 shuriken per second depening on worker mood");
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton button = (JButton) e.getSource();

			if (button == button1) {
				messageText.setText(null);
			} else if (button == button2) {
				messageText.setText(null);
			} else if (button == button3) {
				messageText.setText(null);
			} else if (button == button4) {
				messageText.setText(null);
			}

		}
	}

}
