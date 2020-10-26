package week5.section.handouts;

import acm.graphics.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import acm.graphics.GCanvas;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;

public class Week5_UsingInteractors extends GraphicsProgram {

	public void init() {
	//	setLayout(new GridLayout(1, 3)); // create and add GCanvas to a Console Layout

		// Console is first element of the layout
		hMap = new HashMap<String, GObject>();
		controlPanel();
		addActionListeners();
		addMouseListeners();
	}

	private void controlPanel() {
		// Adding to control bar
		Add = new JButton("Add");
		Remove = new JButton("Remove");
		Clear = new JButton("Clear");
		
		// Add Canvas
		//GCanvas myCanvas = new GCanvas();
		//add(myCanvas);
		
		textField = new JTextField(MAX_NAME); // Initialize TextField for nameField
		add(new JLabel("Name"), SOUTH); // Adding JLabel
		add(textField, SOUTH); // Adding NameField
		textField.addActionListener(this); // ActionListener for nameField

		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	}

	// GCompound contains other GObjects. Useful for one operation with multiple
	// GObjects
	// at the same time.

	private void addBox(String name) { // Must return a name in each box (whatever provided in the text field)
		GCompound box = new GCompound();
		GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel gLabel = new GLabel(name);
		box.add(rect, BOX_WIDTH / 2, BOX_HEIGHT / 2);
		box.add(gLabel, gLabel.getWidth() / 2, gLabel.getHeight() / 2);
		hMap.put(name, box); // associates value with key
		add(rect);
	}
	
	
	// Mouse clicked, and saves location of clicked point
		public void mouseClicked(MouseEvent e) {
			if (currentObject != null)
				currentObject.sendToFront();
		}
	
	// Location wherever mouse is pressed
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		currentObject = getElementAt(last);
	}
	
	// Method to drag mouse, detection from last location
	public void mouseDrag(MouseEvent e) {
		if (currentObject != null) {
			currentObject.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}

	private void removeBox(String name) {
		// GObject
		GObject obj = hMap.get(name);
		if (obj != null) {
			remove(obj);
		}
	}

	private void clearAll() {
		Iterator<String> it = hMap.keySet().iterator(); // returns set view of keys, cycles through elements
		while (it.hasNext()) {
			removeBox(it.next());
		}
		hMap.clear(); // Rids the objects within the HashMap
	}
	
	
	

	public void actionPerformed(ActionEvent e) {

		Object selection = e.getSource();
		if (selection == Add || selection == textField) {
			addBox(textField.getText());
		} else if (selection == Remove) {
			removeBox(textField.getText());
		} else if (selection == Clear) {
			clearAll();
		}

		/*
		 * // if source of interaction is the textfield, prints "you typed: ... " if
		 * (e.getSource() == textField) { println("You typed: " + textField.getText());
		 * }
		 */

	}

	// Private Constant(s)
	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	final JTextField tf = new JTextField();
	// tf.setBounds(100, 100, 150, 20);

	// Private iVars
	private GObject currentObject;
	private GPoint last;
	// private GCanvas myCanvas;
	private HashMap<String, GObject> hMap;
	private JTextField textField;
	private JButton Add, Remove, Clear;

}
