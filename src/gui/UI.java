package gui;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import java.awt.*;
import java.awt.event.*;

import algorithms.LongestCommonSubsequence;
import algorithms.Naive;
import algorithms.KMP;

class UI extends Frame implements ActionListener
{
	Button b1,b2,b3,b4,b6,b7;
	Label  l1,l2,l3;
	TextField t1,t2,t3;
	JFileChooser filechooser;
	String path = "";
	static String filePath = "";
	static String dirPath = "";
	File documentCorpus = null;
	File potentialPlagiarisedFile = null;
	
	UI(String title)
	{
		super(title);
		setLayout(null);
		l1=new Label("Input directory:");
		l1.setBounds(50, 50, 90, 50);
		add(l1);
		b6=new Button("Directory");
		b6.setBounds(200, 50, 50, 40);
		add(b6);
		b6.addActionListener(this);
		t1=new TextField();
		t1.setBounds(300, 50, 300, 30);
		add(t1);
		t1.setText("/Users/Meghna/Desktop/directory.txt");
		
		l2=new Label("Input pattern:");
		l2.setBounds(50, 100, 70, 50);
		add(l2);
		b7=new Button("Pattern");
		b7.setBounds(200, 100, 50, 40);
		add(b7);
		b7.addActionListener(this);
		t2=new TextField();
		t2.setBounds(300, 100, 300, 30);
		add(t2);
		t2.setText("/Users/Meghna/Desktop/pattern.txt");
		
		l3=new Label("Choose:");
		l3.setBounds(50, 200, 50, 50);
		add(l3);
		
		t3=new TextField();
		t3.setBounds(100, 500, 500, 200);
		add(t3);
		
		b1=new Button("LCSS");
		b1.setBounds(100, 300, 50, 50);
		add(b1);
		b1.addActionListener(this);
		
		b2=new Button("Naive");
		b2.setBounds(200, 300, 50, 50);
		add(b2);
		b2.addActionListener(this);
		
		b3=new Button("BM");
		b3.setBounds(300, 300, 50, 50);
		add(b3);
		b3.addActionListener(this);
		
		b4=new Button("KMP");
		b4.setBounds(400, 300, 50, 50);
		add(b4);
		b4.addActionListener(this);
		
//		b5=new Button("RUN");
//		b5.setBounds(100, 400, 50, 50);
//		add(b5);
//		b5.addActionListener(this);

		filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//int Kmpflag = 0;
		//int Boyerflag = 0;
	//	int Lcssflag = 0;
		//int Naiveflag = 0;
		//int Rabinflag = 0;
		//int threshhold = 40;
		
		this.addWindowListener(new WindowHandler());
		
	}
	
	public void actionPerformed (ActionEvent ae)
	{
	try
	{
		String dir=t1.getText();
		String pat=t2.getText();
		String output=t3.getText();
		
		dirPath = t1.getText();
		filePath = t2.getText();
		documentCorpus = new File(dirPath);
		potentialPlagiarisedFile = new File(filePath);

		int Naiveflag = 0;
		
		if(ae.getSource()==b1)
		{
			LongestCommonSubsequence();
		
		}
		if(ae.getSource()==b2)
		{
			Naiveflag = 1;
			ArrayList<String> result = Naive.computeNaive(
					documentCorpus, potentialPlagiarisedFile);

			String final_res = "";
			for (String res : result)
				final_res += res + "\n";

			t3.setText(final_res);
		}
		if(ae.getSource()==b3)
		{
			BoyerMoore();
		}
		if(ae.getSource()==b4)
		{
			KMP();
		}
//		if(ae.getSource()==b5)
//		{
//			if (ae.getSource() == b5) {
//				dirPath = t1.getText();
//				filePath = t2.getText();
//				documentCorpus = new File(dirPath);
//				potentialPlagiarisedFile = new File(filePath);
//			//Run
//		}
		if(ae.getSource()==b6)
		{
			int returnVal = filechooser.showOpenDialog(UI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = filechooser.getSelectedFile();
				path = file.getAbsolutePath();
				if (ae.getSource() == b6) {
					t1.setText(path);
					dirPath = path;
				} 

			} else {
				System.out.println("Select proper file" + "\n");
			}
		}
		if(ae.getSource()==b7)
		{
			int returnVal = filechooser.showOpenDialog(UI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				File file = filechooser.getSelectedFile();
				path = file.getAbsolutePath();
			 if (ae.getSource() == b7) {
					t2.setText(path);
					filePath = path;
				}

			} else {
				System.out.println("Select proper file." + "\n");
			}
		}
	}
	catch(Exception e)
	{
		System.out.println("Error="+e);
	}
	}
	public static void main(String args[])
	{
		Frame f=new UI("User Interace");
		f.setSize(1000, 1000);
		f.setVisible(true);
	}
}
class WindowHandler extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{	
		System.exit(0);
}
}