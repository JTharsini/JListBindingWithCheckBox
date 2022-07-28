import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JListBindingWithCheckBox {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new GridBagLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;

		p.add(new MyPanel(), gbc);

		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.anchor = GridBagConstraints.NORTHWEST;
		gbc1.gridy = 0;
		gbc1.gridx = 0;
		gbc1.weightx = 1;
		gbc1.weighty = 1;

		f.add(p, gbc1);
		f.setSize(340, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
