

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton chooseFileButton;
	private JButton countWordsButton;
	private JButton clearButton;
	private JScrollPane scrollPane;
	private JTextPane txtArea;
	private File path;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Word Counter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		chooseFileButton = new JButton("Choose File");
		chooseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		panel.add(chooseFileButton);
		
		countWordsButton = new JButton("Count the Words");
		countWordsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_countWordsButton_mouseClicked(e);
			}
		});
		countWordsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_countWordsButton_actionPerformed(e);
			}
		});
		panel.add(countWordsButton);
		
		clearButton = new JButton("Clear Text Area");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_clearButton_mouseClicked(e);
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_clearButton_actionPerformed(e);
			}
		});
		panel.add(clearButton);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txtArea = new JTextPane();
		scrollPane.setViewportView(txtArea);
	}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(chooseFileButton);
		setPath(chooser.getSelectedFile());
		
		
		
	}
	protected void do_countWordsButton_actionPerformed(ActionEvent e) {
	}

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}
	
	

	
	protected void do_countWordsButton_mouseClicked(MouseEvent e) {
		
		TreeMap<String, Integer> newMap = new TreeMap<>();

		try(BufferedReader reader = new BufferedReader(new FileReader(getPath()));) {
			
			String lines="";
			while((lines=reader.readLine())!=null) {
				
				String[] words=lines.split(" ");
			
			for (String replacedWords : words) {
				
				String replace=replacedWords.replaceAll("\\p{Punct}|\\d","");
				String lower=replace.toLowerCase();
				
				if(lower.trim().length() == 0)
					continue;
				
				if(newMap.containsKey(lower)) {
					newMap.put(lower, newMap.get(lower)+1);
				}
				else {
					newMap.put(lower, 1);
				}
				
			}
		}
			List <MapObject> objectsOfMap = new ArrayList<>();
			Set<Entry<String,Integer>> toConvertArrayList = newMap.entrySet();
			
			for (Entry<String, Integer> entry : toConvertArrayList) {
				
				MapObject mapObject = new MapObject(entry.getKey(), entry.getValue());
				objectsOfMap.add(mapObject);
				
			}
			
			Collections.sort(objectsOfMap, new Comparator());
			StringBuilder result = new StringBuilder();
			for (int x=objectsOfMap.size()-1; x>=0 ;x--) {
				result.append(objectsOfMap.get(x)+"\n");
			}
		
			txtArea.setText(result.toString());

			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	protected void do_clearButton_actionPerformed(ActionEvent e) {
	}
	protected void do_clearButton_mouseClicked(MouseEvent e) {
		txtArea.setText("");
	}
}
