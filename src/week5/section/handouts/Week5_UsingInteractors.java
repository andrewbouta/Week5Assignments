package week5.section.handouts;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;
import acm.program.*;

public class Week5_UsingInteractors extends GraphicsProgram {

	public void init() {
		// setLayout(new GridLayout(1, 3)); // create and add GCanvas to a Console
		// Layout

		// Console is first element of the layout
		hMap = new HashMap<String, GObject>();
		controlPanel();
		
		addActionListeners();
		addMouseListeners();
	}

	private void controlPanel() {
		// Left side of Panel added
		textField = new JTextField(MAX_NAME); // Initialize TextField for nameField
		textField.addActionListener(this); // ActionListener for nameField
		add(new JLabel("Name"), SOUTH); // Adding JLabel
		add(textField, SOUTH); // Adding NameField

		// Right side of Panel added
		Add = new JButton("Add");
		Remove = new JButton("Remove");
		Clear = new JButton("Clear");

		add(Add, SOUTH);
		add(Remove, SOUTH);
		add(Clear, SOUTH);
	}

	// GCompound contains other GObjects. Useful for one operation with multiple GObjects
	// at the same time.

	private void addBox(String name) { // Must return a name in each box (whatever provided in the text field)
		GCompound box = new GCompound();
		GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		box.add(rect, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
		add(box, getWidth() / 2, getHeight() / 2);
		hMap.put(name, box);
	}

	private void removeBox(String name) {
		// GObject
		GObject obj = hMap.get(name);
		if (obj != null) {
			remove(obj);
		}
	}
	
	// Location wherever mouse is pressed
	public void mousePressed(MouseEvent e) {
		click = new GPoint(e.getPoint());
		currentObject = getElementAt(click);
	}
	
	// On ActionEvent perform whatever is called (Add, Remove, Clear)
	public void actionPerformed(ActionEvent e) {
		Object selection = e.getSource();
		if (selection == Add || selection == textField) {
			addBox(textField.getText());
		} else if (selection == Remove) {
			removeBox(textField.getText());
		} else if (selection == Clear) {
			clearAll();
		}
	}

	// Method to drag mouse, detection from last location
	public void mouseDragged(MouseEvent e) {
		if (currentObject != null) {
			currentObject.move(e.getX() - click.getX(), e.getY() - click.getY());
			click = new GPoint(e.getPoint());
		}
	}

	// Mouse clicked, and saves location of clicked point
	public void mouseClicked(MouseEvent e) {
		if (currentObject != null)
			currentObject.sendToFront();
	}
	
	private void clearAll() {
		Iterator<String> it = hMap.keySet().iterator(); // returns set view of keys, cycles through elements
		while (it.hasNext()) {
			removeBox(it.next());
		}
		hMap.clear(); // Rids the objects within the HashMap
	}

	// Private Constant(s)
	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	// tf.setBounds(100, 100, 150, 20);

	// Private iVars
	private GObject currentObject;
	private GPoint click;
	// private GCanvas myCanvas;
	private HashMap<String, GObject> hMap;
	private JTextField textField;
	private JButton Add;
	private JButton Remove;
	private JButton Clear;
}
