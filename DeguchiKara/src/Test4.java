/**
 * 数列から最長上昇列と最長下降列を探すプログラム (Swing版)
 *
 * @author  Yoshihiro Mizoguchi
 * @version 0.1, 2013/10/23
 * @since   JDK1.6
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Random;

public class Test4 extends JFrame implements ActionListener {
	static JTextArea sizearea,viewarea;
	static JRadioButton radio1;
	static int x[] = new int[256], a[][] = new int[256][256], s, N;

	  Test4(String title){
	    setTitle(title);
	    setBounds(100, 100, 640, 480);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel p = new JPanel();
	    JLabel label = new JLabel("サイズ指定:");
	    sizearea = new JTextArea(3, 20);
	    sizearea.setRows(1); sizearea.setColumns(5);
	    sizearea.setText("16");
	    viewarea = new JTextArea();
	    viewarea.setRows(25); viewarea.setColumns(40);	
	    viewarea.setLineWrap(true);
	    viewarea.setWrapStyleWord(true);
	    viewarea.setEditable(false);
	    JScrollPane scrollpane = new JScrollPane(viewarea);
	    JButton button = new JButton("実行");
	    radio1 = new JRadioButton("詳細表示");
	    button.addActionListener(this);
	    p.add(label);
	    p.add(sizearea);
	    p.add(button);
	    p.add(radio1);
	    p.add(scrollpane);    
	    Container contentPane = getContentPane();
	    contentPane.add(p, BorderLayout.CENTER);
	  }

public void actionPerformed(ActionEvent event) {
	initialx();
	s=0; assend(true);
	printa("最長上昇列",radio1.isSelected());
	s=0; assend(false);
	printa("最長下降列",radio1.isSelected());
	viewarea.append("----------\n\n");
}

static void initialx(){
	Random rnd = new Random();
	N = Integer.valueOf(sizearea.getText());
	for(int i=0; i<N; i++) x[i]=i+1;
	for(int i=0; i<N; i++) {
		int ix,iy,t;
		ix=rnd.nextInt(N);iy=rnd.nextInt(N);
		if (ix != iy) { t=x[ix];x[ix]=x[iy];x[iy]=t; }
	}
	viewarea.append("長さ"+N+"の入力データ: ");
	for(int i=0; i<N-1; i++) {
		viewarea.append(x[i]+", ");
	}
	viewarea.append(x[N-1]+".\n\n");
}

static void assend(boolean as) {
	for(int i=0; i<N; i++){
		if (i==0) {
			a[0][0]=x[0];
		} else {
			int j=0;
			boolean exitflag = false;
			do {
				if ((as && (x[i]<a[j][j])||((!as) && (x[i]>a[j][j])))) {
					if (j==0) {
						a[j][j]=x[i];
						exitflag = true;
					} else {
					for(int k=0; k<j; k++) { a[j][k]=a[j-1][k]; }
					a[j][j]=x[i];
					exitflag = true;
					}
				} else {
					if (j==s) {
						for(int k=0; k<s+1; k++) { a[s+1][k]=a[s][k]; }
						a[s+1][s+1]=x[i];
						s = s+1;
						exitflag = true;
					} else {
						j=j+1;
					}
				}
			} while (exitflag==false);
		}
	}
}

static void printa(String text,boolean debug) {
	int i0;
	if (debug) {i0=0;} else {i0=s;}
	viewarea.append(text+"(Max Length="+(s+1)+")\n");
	for(int i=i0; i<s+1; i++){
		viewarea.append((i+1)+": ");
		for(int j=0; j<i; j++) {
			viewarea.append(a[i][j]+",");
		}
		viewarea.append(a[i][i]+".\n");
	}
}

public static void main(String args[]){	

		Test4 frame = new Test4("最長上昇列と下降列");
		frame.setVisible(true);
	
	}
}
