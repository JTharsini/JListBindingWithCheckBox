import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class MyPanel extends JPanel {
	private MyPanelModel model;
	private JList jlist;

	public MyPanel() {
		model = new MyPanelModel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 50, 50 };
		setLayout(gridBagLayout);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 300));
		scrollPane.setMinimumSize(new Dimension(300, 300));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.gridx = 0;
		add(scrollPane, gbc_scrollPane);

		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<RecipientWrapper> list = new ArrayList<>();
				list.add(new RecipientWrapper("<Name on appointed care contact>"));
				list.add(new RecipientWrapper("<Municipality connected to public address marketing>"));
				list.add(new RecipientWrapper("<GP patient is listed on>"));
				list.add(new RecipientWrapper("<Unit>"));
				list.add(new RecipientWrapper("<Name on appointed care contact>"));
				model.setGroups(list);

			}
		});

		scrollPane.setViewportView(getList());

		JButton show = new JButton("Show");
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				printSelected();
			}

			protected void printSelected() {
				for (int i = 0; i < jlist.getModel().getSize(); i++) {
					if (((RecipientWrapper) (jlist.getModel().getElementAt(i))).isSelected()) {
						System.out.println(jlist.getModel().getElementAt(i).toString());
					}
				}
			}
		});

		GridBagConstraints gbc_add = new GridBagConstraints();
		gbc_add.gridy = 1;
		gbc_add.gridx = 0;
		gbc_add.anchor = GridBagConstraints.EAST;
		add(add, gbc_add);
		GridBagConstraints gbc_show = new GridBagConstraints();
		gbc_show.anchor = GridBagConstraints.EAST;
		gbc_show.gridy = 1;
		gbc_show.gridx = 1;
		add(show, gbc_show);
		initBinding();
	}

	private JList getList() {
		if (jlist == null) {
			jlist = new JList();
			jlist.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					JList list = (JList) event.getSource();
					// Get index of item clicked
					int index = list.locationToIndex(event.getPoint());
					RecipientWrapper item = (RecipientWrapper) list.getModel().getElementAt(index);
					// Toggle selected state
					item.setSelected(!item.isSelected());
					// Repaint cell
					list.repaint(list.getCellBounds(index, index));
				}
			});
			jlist.setCellRenderer(new CheckboxListRenderer());
		}
		return jlist;
	}

	private void initBinding() {
		BeanProperty<MyPanelModel, List<RecipientWrapper>> phoneGroupsBeanProperty = BeanProperty.create("groups");
		JListBinding<RecipientWrapper, MyPanelModel, JList> jListBinding = SwingBindings.createJListBinding(
				AutoBinding.UpdateStrategy.READ, model, phoneGroupsBeanProperty, getList());
		jListBinding.bind();
	}
}

class CheckboxListRenderer extends JCheckBox implements ListCellRenderer<RecipientWrapper> {

	public Component getListCellRendererComponent(final JList list, final RecipientWrapper value, final int index,
			final boolean isSelected, final boolean cellHasFocus) {
		JCheckBox component = new JCheckBox();
		Component c;
		if (value != null) {
			component.setEnabled(list.isEnabled());
			component.setSelected(value.isSelected());
			component.setFont(list.getFont());
			component.setBackground(list.getBackground());
			component.setForeground(list.getForeground());
			component.setText(value.getString());
			c = component;
		} else {
			c = new JLabel();
		}
		return c;
	}
}