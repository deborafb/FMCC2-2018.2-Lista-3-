/*
 * @author Arthur Fernandes de Andrade  Matrícula: 116211155
 * @author Débora Ferreira de Barros  Matrícula: 116211210
 */

package fade;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fade extends JPanel implements ActionListener {
	boolean adicionar = false;
	Image myImage = new ImageIcon("input/marioVivo.png").getImage();
	Image myImage2 = new ImageIcon("input/marioSon.png").getImage();
	int image1or2 = 2;
	Image temp = myImage;

	Timer timer = new Timer(40, this);

	private float alpha = 1f;

	public Fade() {
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(temp, 10, 10, null);
	}

	public void actionPerformed(ActionEvent e) {
		alpha += somaDiminuiAlpha();
		if (alpha <= 0) {
			alpha = 0;
			adicionar = true;
			trocarImagem();
			// timer.stop();
		} else if (alpha >= 1f) {
			adicionar = false;

		}
		repaint();
	}

	private void trocarImagem() {
		if (image1or2 == 1) {
			temp = myImage;
			image1or2 = 2;

		} else {
			temp = myImage2;
			image1or2 = 1;
		}

	}

	public float somaDiminuiAlpha() {
		if (adicionar) {
			return +0.01f;

		} else {
			return -0.01f;
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Fade out");
		frame.add(new Fade());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 250);
		frame.setVisible(true);
	}

}