package name.lxm.targets.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Font;

public class Huitupanel extends JPanel {
	/**
	 * 绘制地图的界面
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon icon1 = new ImageIcon("image/10_u835.png");// 靶子
	ImageIcon icon2 = new ImageIcon("image/u686.png");// 人员
	ImageIcon icon3 = new ImageIcon("image/_3-10_u844.png");// 动作
	ImageIcon icon4 = new ImageIcon("image/10_u835_selected.png");// 选中
	ImageIcon icon5 = new ImageIcon("image/_3-10_u842.png");// 未打中
	ImageIcon icon6 = new ImageIcon("image/旗子1-3_u926.png");// 打中

	Image image1 = icon1.getImage();
	Image image2 = icon2.getImage();
	Image image3 = icon3.getImage();
	Image image4 = icon4.getImage();
	Image image5 = icon5.getImage();
	Image image6 = icon6.getImage();
	private int nums;
	private int painwitch;

	public int getPainwitch() {
		return painwitch;
	}

	private Tubiao[] tubiaos;
	public void setPainwitch(int painwitch) {
		this.painwitch = painwitch;
	}

	private int shadowx, shadowy, shadowx1, shadowy1;
	private int tlpanex, tlpaney;
	private int bahao;
	private JLabel[] labels = new JLabel[1000];

	public Huitupanel() {
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		// 调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
//		super.paint(g);
//		removeAll();
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.black);// 设置画图的颜色
//		for (int i = 0; i < nums; i++) {
//			// add(jLabel);
//			labels[i] = new JLabel();
//			if (datas[i][2] == 1) {
//				labels[i].setText("成绩:");
//			} else {
//				labels[i].setText("   " + String.valueOf(datas[i][3]));
//			}
//			labels[i].setBackground(SystemColor.inactiveCaption);
//			labels[i].setOpaque(true);
//			labels[i].setBorder(new LineBorder(new Color(0, 0, 0)));
//			switch (datas[i][2]) {
//			case 1: {
//				if (datas[i][6] == 1) {
//					labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//					add(labels[i]);
//					g2d.drawImage(image5, datas[i][0], datas[i][1], null);
//				} else if (datas[i][7] == 1) {
//					labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//					add(labels[i]);
//					g2d.drawImage(image6, datas[i][0], datas[i][1] - 3, null);
//				} else if (datas[i][5] == 1) {
//					labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//					add(labels[i]);
//					g2d.drawImage(image4, datas[i][0], datas[i][1], null);
//				} else if (datas[i][4] == 1) {
//					labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//					add(labels[i]);
//					g2d.drawImage(image3, datas[i][0] + 10, datas[i][1], null);
//				} else {
//					labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//					add(labels[i]);
//					g2d.drawImage(image1, datas[i][0], datas[i][1], null);
//				}
//			}
//				break;
//			case 0: {
//				labels[i].setBounds(datas[i][0] + 5, datas[i][1] - 24, 44, 24);
//				add(labels[i]);
//				g2d.drawImage(image2, datas[i][0] + 10, datas[i][1], null);
//				// }
//			}
//				break;
//			}
//		}
//		revalidate();
//		repaint();
//		if (painwitch == 5) {
//			new Font("宋体", Font.PLAIN, 12);
//			g2d.setColor(Color.blue);// 设置当前绘图颜色
//			g2d.fill3DRect(tlpanex - 3, tlpaney - 60, 60, 60, false);
//			g2d.setColor(Color.black);
//			g2d.drawString("组：", tlpanex - 3 + 2, tlpaney - 54 + 12 - 6);// 绘制文本
//			g2d.drawString("靶号：" + String.valueOf(bahao), tlpanex - 3 + 2, tlpaney - 54 + 24 + 2 - 6);// 绘制文本
//			g2d.drawString("气压：", tlpanex - 3 + 2, tlpaney - 54 + 36 + 4 - 6);// 绘制文本
//			g2d.drawString("电量：", tlpanex - 3 + 2, tlpaney - 54 + 48);// 绘制文本
//		}
//		if (painwitch == 6) {
//			new Font("宋体", Font.PLAIN, 12);
//			g2d.setColor(Color.blue);// 设置当前绘图颜色
//			g2d.fill3DRect(tlpanex - 3, tlpaney - 40, 60, 40, false);
//			g2d.setColor(Color.black);
//			g2d.drawString("姓名：", tlpanex - 3 + 2, tlpaney - 40 + 12 + 4);// 绘制文本
//			g2d.drawString("编号：" + String.valueOf(bahao), tlpanex - 3 + 2, tlpaney - 40 + 12 + 6 + 12 + 2);// 绘制文本
//		}
//		Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10, },
//				0f);
//		g2d.setStroke(dash);
//		if (painwitch == 1) {
//			g2d.drawRect(shadowx, shadowy, shadowx1 - shadowx, shadowy1 - shadowy);
//
//		} else if (painwitch == 2) {
//			g2d.drawRect(shadowx, shadowy1, shadowx1 - shadowx, shadowy - shadowy1);
//		} else if (painwitch == 3) {
//			g2d.drawRect(shadowx1, shadowy1, shadowx - shadowx1, shadowy - shadowy1);
//		} else if (painwitch == 4) {
//			g2d.drawRect(shadowx1, shadowy, shadowx - shadowx1, shadowy1 - shadowy);
//		}
//		this.validate();		
/////////////////////////////////////////////////////////////////////////////
		// 调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
				super.paint(g);
				removeAll();
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.black);// 设置画图的颜色
				for (int i = 0; i < nums; i++) {
					// add(jLabel);
					labels[i] = new JLabel();
					if (tubiaos[i] instanceof Bazhi) {
						labels[i].setText("成绩:");
					} else if(tubiaos[i] instanceof Dabarenyuan){
						labels[i].setText("   " + String.valueOf(((Dabarenyuan)(tubiaos[i])).getBianhao()));
					}
					labels[i].setBackground(SystemColor.inactiveCaption);
					labels[i].setOpaque(true);
					labels[i].setBorder(new LineBorder(new Color(0, 0, 0)));

					if (tubiaos[i] instanceof Bazhi) {
						if (((Bazhi)(tubiaos[i])).isIsweidazhong()==true) {
							labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
							add(labels[i]);
							g2d.drawImage(image5, tubiaos[i].getPointx(), tubiaos[i].getPointy(), null);
						} else if (((Bazhi)(tubiaos[i])).isIsdazhong()==true) {
							labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
							add(labels[i]);
							g2d.drawImage(image6, tubiaos[i].getPointx(), tubiaos[i].getPointy() - 3, null);
						} else if (((Bazhi)(tubiaos[i])).isIsxuanzhong()==true) {
							labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
							add(labels[i]);
							g2d.drawImage(image4, tubiaos[i].getPointx(), tubiaos[i].getPointy(), null);
						} else if (((Bazhi)(tubiaos[i])).isIsdongzuo()==true) {
							labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
							add(labels[i]);
							g2d.drawImage(image3, tubiaos[i].getPointx() + 10, tubiaos[i].getPointy(), null);
						} else {
							labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
							add(labels[i]);
							g2d.drawImage(image1, tubiaos[i].getPointx(), tubiaos[i].getPointy(), null);
						}
					} else if (tubiaos[i] instanceof Dabarenyuan) {
						labels[i].setBounds(tubiaos[i].getPointx() + 5, tubiaos[i].getPointy() - 24, 44, 24);
						add(labels[i]);
						g2d.drawImage(image2, tubiaos[i].getPointx() + 10, tubiaos[i].getPointy(), null);
					}
				}
				revalidate();
				repaint();
				if (painwitch == 5) {
					new Font("宋体", Font.PLAIN, 12);
					g2d.setColor(Color.blue);// 设置当前绘图颜色
					g2d.fill3DRect(tlpanex - 3, tlpaney - 60, 60, 60, false);
					g2d.setColor(Color.black);
					g2d.drawString("组：", tlpanex - 3 + 2, tlpaney - 54 + 12 - 6);// 绘制文本
					g2d.drawString("靶号：" + String.valueOf(bahao), tlpanex - 3 + 2, tlpaney - 54 + 24 + 2 - 6);// 绘制文本
					g2d.drawString("气压：", tlpanex - 3 + 2, tlpaney - 54 + 36 + 4 - 6);// 绘制文本
					g2d.drawString("电量：", tlpanex - 3 + 2, tlpaney - 54 + 48);// 绘制文本
				}
				if (painwitch == 6) {
					new Font("宋体", Font.PLAIN, 12);
					g2d.setColor(Color.blue);// 设置当前绘图颜色
					g2d.fill3DRect(tlpanex - 3, tlpaney - 40, 60, 40, false);
					g2d.setColor(Color.black);
					g2d.drawString("姓名：", tlpanex - 3 + 2, tlpaney - 40 + 12 + 4);// 绘制文本
					g2d.drawString("编号：" + String.valueOf(bahao), tlpanex - 3 + 2, tlpaney - 40 + 12 + 6 + 12 + 2);// 绘制文本
				}
				Stroke dash = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10, },
						0f);
				g2d.setStroke(dash);
				if (painwitch == 1) {
					g2d.drawRect(shadowx, shadowy, shadowx1 - shadowx, shadowy1 - shadowy);

				} else if (painwitch == 2) {
					g2d.drawRect(shadowx, shadowy1, shadowx1 - shadowx, shadowy - shadowy1);
				} else if (painwitch == 3) {
					g2d.drawRect(shadowx1, shadowy1, shadowx - shadowx1, shadowy - shadowy1);
				} else if (painwitch == 4) {
					g2d.drawRect(shadowx1, shadowy, shadowx - shadowx1, shadowy1 - shadowy);
				}
				this.validate();	
		
	}

	public void display(int[][] data, int num) {
		nums = num;
		this.repaint();

	}

	public void display2(int x, int y, int x1, int x2, int[][] data) {

		this.shadowx = x;
		this.shadowy = y;
		this.shadowx1 = x1;
		this.shadowy1 = x2;
		this.repaint();
	}

	public void display3(int x, int y, int[][] data, int z) {

		this.tlpanex = x;
		this.tlpaney = y;
		this.bahao = z;
		this.repaint();
	}

	public void displayzhengti(Tubiao[] tubiaos,int num) {
		this.tubiaos = tubiaos;
		this.nums=num;
		this.repaint();
	}
	public void displayjuxing(int x, int y, int x1, int x2, Tubiao[] tubiaos) {

		this.shadowx = x;
		this.shadowy = y;
		this.shadowx1 = x1;
		this.shadowy1 = x2;
		this.tubiaos=tubiaos;
		this.repaint();
	}
	public void displayxinxi(int x, int y,Tubiao[] tubiaos,int z) {

		this.tlpanex = x;
		this.tlpaney = y;
		this.tubiaos=tubiaos;
		this.bahao=z;
		this.repaint();
	}
}
