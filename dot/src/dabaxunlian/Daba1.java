package dabaxunlian;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Daba1 extends JFrame implements Datainterface {

	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image imgfangda = tk.getImage("image/magnifying39.png");
	private Cursor cufaangda = tk.createCustomCursor(imgfangda, new Point(16, 16), "stick1");
	private Image imgsuoxiao = tk.getImage("image/zoom29.png");
	private Cursor cusuoxiao = tk.createCustomCursor(imgsuoxiao, new Point(16, 16), "stick2");
	Cursor cutuozhuai = new Cursor(Cursor.HAND_CURSOR);
	private ImageIcon icon5 = new ImageIcon("image/u656.png");// 全屏
	private ImageIcon icon6 = new ImageIcon("image/u965.png");
	private ImageIcon icon7 = new ImageIcon("image/u648.png");// 放大
	private ImageIcon icon8 = new ImageIcon("image/u650.png");// 缩小

	private static Reflesh reflesh = new Reflesh("reflesh");
	public static int[][] data = new int[1000][10];
	public static int[][] datacurrent = new int[1000][10];
	public static int yunxingflag = 10;
	private boolean tuozhuai = false;
	private boolean dianjifangda = false;
	private boolean dianjisuoxiao = false;
	private int[] tuozhuaibuffx = new int[1000];
	private int[] tuozhuaibuffy = new int[1000];
	private int x, y, bfx, bfy;
	private int buffbianhao = 0;
	private boolean biaoqianstate = false;
	public static int pointnum = 0;
	private int zinout = 0;
	static Tubiao[] tubiaos = new Tubiao[1000];
	static Bazhi[] bazhis = new Bazhi[600];
	static Dabarenyuan[] dabarenyuans = new Dabarenyuan[400];

	private CommandlistenerInterface commandlistenerInterface;

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	static TMUIControlPanel tmuiControlPanel = new TMUIControlPanel();
	static TMUITargetStatusPanel tmuiTargetStatusPanel = new TMUITargetStatusPanel();
	DefineJpanel defineJpanel;
	DefineJpanel[] defineJpanels = new DefineJpanel[20];
	static Huitupanel huitupanel = new Huitupanel();
	JTextArea dataarea = new JTextArea();

	JButton button1 = new JButton("复位");
	JButton button2 = new JButton("");
	JButton button3 = new JButton("");
	JButton button4 = new JButton("拖拽");
	JButton button5 = new JButton("点击放大");
	JButton button6 = new JButton("点击缩小");
	JButton button7 = new JButton("");

	JToolBar jToolBar = new JToolBar("工具");
	PopupMenu popupMenu = new PopupMenu("菜单");
	MenuItem menuItem1 = new MenuItem("动作");
	MenuItem menuItem2 = new MenuItem("添加分组");
	MenuItem menuItem3 = new MenuItem("添加到组");
	MenuItem menuItem4 = new MenuItem("退出分组");
	private final JButton button = new JButton("初始化");
	private final JButton button_1 = new JButton("启动");
	private final JButton button_2 = new JButton("停止");

	public Daba1(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		container.setLayout(borderLayout);
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
		// 取得底部边界高度，即任务栏高度
		int taskHeight = screenInsets.bottom;
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - taskHeight);

		panel1.setLayout(new BorderLayout(0, 0));
		panel1.setBorder(BorderFactory.createLineBorder(Color.white));
		button2.setIcon(icon7);
		jToolBar.setBorder(null);
		jToolBar.setOrientation(SwingConstants.VERTICAL);

		jToolBar.add(button2);
		button3.setIcon(icon8);
		jToolBar.add(button3);
		jToolBar.add(button7);
		jToolBar.add(button4);
		jToolBar.add(button5);
		jToolBar.add(button6);
		jToolBar.add(button1);
		button7.setIcon(icon6);// 全屏

		jToolBar.setFloatable(true);
		panel1.add(jToolBar, BorderLayout.EAST);

		jToolBar.add(button);
		button.addActionListener(new Chushihualistener());
		jToolBar.add(button_1);
		button_1.addActionListener(new Qidonglistener());
		jToolBar.add(button_2);
		button_2.addActionListener(new Tingzhilistener());
		panel1.add(huitupanel, BorderLayout.CENTER);

		tmuiControlPanel.setPreferredSize(new Dimension(313, 0));
		tmuiTargetStatusPanel.setPreferredSize(new Dimension(0, 100));

		container.add(panel1, BorderLayout.CENTER);
		container.add(tmuiControlPanel, BorderLayout.EAST);
		container.add(tmuiTargetStatusPanel, BorderLayout.SOUTH);

		button1.addActionListener(new Fuweilistener());
		button2.addActionListener(new Fangdalistener());
		button3.addActionListener(new Suoxiaolistener());
		button4.addActionListener(new Tuozhuailistener());
		button5.addActionListener(new Dianjifangdalistener());
		button6.addActionListener(new Dianjisuoxiaolistener());
		button7.addActionListener(new Suofanglistener());
		// button.addActionListener(commandlistenerInterface);

		popupMenu.add(menuItem1);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);
		popupMenu.add(menuItem4);
		huitupanel.add(popupMenu);

		menuItem1.addActionListener(new Dongzuolistener());
		menuItem2.addActionListener(new TianjiafenzuListener());
		menuItem3.addActionListener(new Tianjiadaozulistener());
		menuItem4.addActionListener(new Tuichufenzulistener());

		huitupanel.addMouseListener(new Mymouse());
		huitupanel.addMouseMotionListener(new Mymouse());

		this.setVisible(true);
	}

	public void addlistener(CommandlistenerInterface commandlistener) {
		this.commandlistenerInterface = commandlistener;
	}

	private class Tingzhilistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			yunxingflag = 3;
			commandlistenerInterface.syatemStop();
		}
	}

	private class Qidonglistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			yunxingflag = 2;
			commandlistenerInterface.systemStart();
		}

	}

	private class Chushihualistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			yunxingflag = 1;
			String filePath = "TXTfile/data.txt";
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = null;
				try {
					read = new InputStreamReader(new FileInputStream(file), encoding);
				} catch (UnsupportedEncodingException | FileNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int i = 0;
				String[] linetxts = new String[1000];
				try {
					while ((lineTxt = bufferedReader.readLine()) != null) {
						linetxts[i] = lineTxt;
						i++;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				pointnum = i;
				for (i = 0; i < pointnum; i++) {
					String[] m = linetxts[i].split(",");// 例如第一组就是{1，0，1}
					for (int j = 0; j < m.length; j++) {
						int a = Integer.parseInt(m[j]);// 文子转为数字，然后把a存到数组
						data[i][j] = a;
					}
				}
				try {
					read.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			datacurrent = data;
			for (int i = 0, j = 0, k = 0; i < pointnum; i++) {
				data[i][5] = 0;
				data[i][4] = 0;
				data[i][6] = 0;
				data[i][7] = 0;
				data[i][8] = 1;
				if (data[i][2] == 1) {
					bazhis[j] = new Bazhi(data[i][3], 0, 0, 0, false, false, false, false, "", "");
					bazhis[j].setBianhao(data[i][3]);
					bazhis[j].setSuodingyf(1);
					bazhis[j].setPointx(data[i][0]);
					bazhis[j].setPointy(data[i][1]);
					bazhis[j].setIsdongzuo(false);
					bazhis[j].setIsxuanzhong(false);
					bazhis[j].setIsweidazhong(false);
					bazhis[j].setIsdazhong(false);
					tubiaos[i] = bazhis[j];
					j++;
				} else {
					dabarenyuans[k] = new Dabarenyuan();
					dabarenyuans[k].setBianhao(k);
					dabarenyuans[k].setPointx(data[i][0]);
					dabarenyuans[k].setPointy(data[i][1]);
					tubiaos[i] = dabarenyuans[k];
					k++;
				}
			}
			huitupanel.displayzhengti(tubiaos, pointnum);
			tmuiControlPanel.getPanel_7().removeAll();
			tmuiControlPanel.getPanel_7().repaint();
			if (reflesh.isAlive()) {
			} else {
				reflesh.start();
			}
			Datatest datatest = new Datatest();
			datatest.setActionListener(Main.getMainform());
			datatest.start();
			commandlistenerInterface.systemInitialize();
			Bazhiz[] bazhizs = new Bazhiz[Guanlifenzu.getZushu()];
			for (int i = 0; i < bazhizs.length; i++) {
				bazhizs[i] = Guanlifenzu.getBazhizs()[i + 1];
			}
			commandlistenerInterface.groupinfoUpdate(bazhizs);
		}
	}

	private class Tuichufenzulistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// *********退出分组***********//
			if (yunxingflag == 1) {
				String strbazhi = getXuanzhong();
				int[] a = Toolsfunction.str2num(strbazhi);
				Bazhi[] bazhisijk = new Bazhi[a.length];
				for (int i = 0; i < a.length; i++) {
					bazhisijk[i] = bazhis[a[i] - 1];
				}
				Guanlifenzu.tuichuFenzhu(bazhisijk);
				Guanlifenzu.showpanel();
			}
		}
	}

	private class TianjiafenzuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1) {
				String strbazhi = getXuanzhong();
				Defineframe defineframe = new Defineframe();
				defineframe.getTextField().setText(strbazhi);
				defineframe.setVisible(true);
				defineframe.getButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						defineframe.dispose();
					}
				});
				defineframe.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] mStrings = defineframe.getTextField().getText().split(",");
						int j = mStrings.length;
						int[] a = new int[mStrings.length];
						if (!mStrings[0].equals("")) {
							for (int i = 0; i < mStrings.length; i++) {
								a[i] = Integer.parseInt(mStrings[i]);
							}
							Bazhi[] bazhiijk = new Bazhi[j];
							for (int i = 0; i < j; i++) {
								bazhiijk[i] = bazhis[a[i] - 1];
							}
							System.out.println(bazhiijk.length);
							Guanlifenzu.creatfenzu(bazhiijk);
							Guanlifenzu.showpanel();
						} else {
							Bazhi[] bazhikji = new Bazhi[0];
							Guanlifenzu.creatfenzu(bazhikji);
							// Guanlifenzu.creatnewpanel();
							Guanlifenzu.showpanel();
						}
						defineframe.dispose();
					}
				});
			}
		}
	}

	private class Fuweilistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

	private class Tianjiadaozulistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1) {
				String string = getXuanzhong();
				Defineframe1 defineframe1 = new Defineframe1(Guanlifenzu.getZushu(), string);
				defineframe1.setVisible(true);
				defineframe1.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] mStrings = defineframe1.getTextField().getText().split(",");
						int j = mStrings.length;
						int[] a = new int[mStrings.length];
						if (!mStrings[0].equals("")) {
							for (int i = 0; i < mStrings.length; i++) {
								a[i] = Integer.parseInt(mStrings[i]);
							}
							Bazhi[] bazhiijk = new Bazhi[j];
							for (int i = 0; i < j; i++) {
								bazhiijk[i] = bazhis[a[i] - 1];
							}
							Guanlifenzu.tianjiaDaozu((int) defineframe1.getComboBox().getSelectedItem(), bazhiijk);
							Guanlifenzu.showpanel();
						} else {
							Bazhi[] bazhikji = new Bazhi[0];
							Guanlifenzu.tianjiaDaozu((int) defineframe1.getComboBox().getSelectedItem(), bazhikji);
							Guanlifenzu.showpanel();
						}
						defineframe1.dispose();
					}
				});
				defineframe1.getBtnNewButton_1().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						defineframe1.dispose();
					}
				});
			}
		}
	}

	private class Fangdalistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1 || yunxingflag == 2) {
				for (int i = 0; i < pointnum; i++) {
					tubiaos[i].setPointx((int) (1.1 * (tubiaos[i].getPointx() - huitupanel.getWidth() / 2)
							+ huitupanel.getWidth() / 2));
					tubiaos[i].setPointy((int) (1.1 * (tubiaos[i].getPointy() - huitupanel.getWidth() / 2)
							+ huitupanel.getWidth() / 2));
				}
				huitupanel.displayzhengti(tubiaos, pointnum);
			}
		}
	}

	private class Suoxiaolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1 || yunxingflag == 2) {
				for (int i = 0; i < pointnum; i++) {
					tubiaos[i].setPointx((int) ((tubiaos[i].getPointx() - huitupanel.getWidth() / 2) / 1.1
							+ huitupanel.getWidth() / 2));
					tubiaos[i].setPointy((int) ((tubiaos[i].getPointy() - huitupanel.getWidth() / 2) / 1.1
							+ huitupanel.getWidth() / 2));
				}
				huitupanel.displayzhengti(tubiaos, pointnum);
			}
		}
	}

	private class Tuozhuailistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1 || yunxingflag == 2) {
				tuozhuai = !tuozhuai;
				dianjifangda = false;
				dianjisuoxiao = false;

				if (tuozhuai) {

					huitupanel.setCursor(cutuozhuai);
					button4.setBackground(Color.red);
					button5.setBackground(null);
					button6.setBackground(null);
				} else {
					huitupanel.setCursor(null);
					button4.setBackground(null);
				}
				for (int i = 0; i < pointnum; i++) {
					tuozhuaibuffx[i] = tubiaos[i].getPointx();
					tuozhuaibuffy[i] = tubiaos[i].getPointy();
				}
			}
		}
	}

	private class Dianjifangdalistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1 || yunxingflag == 2) {
				dianjifangda = !dianjifangda;
				dianjisuoxiao = false;
				tuozhuai = false;
				if (dianjifangda) {
					huitupanel.setCursor(cufaangda);
					button5.setBackground(Color.red);
					button6.setBackground(null);
					button4.setBackground(null);
				} else {
					huitupanel.setCursor(null);
					button5.setBackground(null);
				}
			}
		}
	}

	private class Dianjisuoxiaolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (yunxingflag == 1 || yunxingflag == 2) {
				dianjisuoxiao = !dianjisuoxiao;
				dianjifangda = false;
				tuozhuai = false;
				if (dianjisuoxiao) {
					huitupanel.setCursor(cusuoxiao);
					button6.setBackground(Color.red);
					button5.setBackground(null);
					button4.setBackground(null);
				} else {
					huitupanel.setCursor(null);
					button6.setBackground(null);
				}
			}
		}
	}

	private class Dongzuolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 动作
			String string = getXuanzhong();
			int[] a = Toolsfunction.str2num(string);
			Bazhi[] bazhisijk = new Bazhi[a.length];
			for (int i = 0; i < a.length; i++) {
				bazhisijk[i] = bazhis[a[i] - 1];
			}
			commandlistenerInterface.bazhisAction(bazhisijk);
		}
	}

	private String getXuanzhong() {
		int j = 0;
		int[] k = new int[100];
		for (int i = 0; i < pointnum; i++) {
			if (tubiaos[i] instanceof Bazhi) {
				if (((Bazhi) (tubiaos[i])).isIsxuanzhong() == true) {
					k[j] = i;
					j++;
				}
			}
		}
		String str = "";
		String strbazhi = "";
		for (int i = 0; i < j; i++) {
			str = str + String.valueOf(((Bazhi) (tubiaos[k[i]])).getBianhao()) + ",";
		}
		if (j > 0) {
			strbazhi = str.substring(0, str.length() - 1);
		}
		return strbazhi;
	}

	// 分组
	private class Suofanglistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (zinout == 0) {
				tmuiControlPanel.setVisible(false);
				tmuiTargetStatusPanel.setVisible(false);
				button7.setIcon(icon5);
				zinout = 1 - zinout;
			} else {
				tmuiControlPanel.setVisible(true);
				tmuiTargetStatusPanel.setVisible(true);
				button7.setIcon(icon6);
				zinout = 1 - zinout;
			}
		}
	}

	private class Mymouse extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {

			super.mouseReleased(e);
			huitupanel.setPainwitch(0);
			huitupanel.displayzhengti(tubiaos, pointnum);
			for (int i = 0; i < pointnum; i++) {
				tuozhuaibuffx[i] = tubiaos[i].getPointx();
				tuozhuaibuffy[i] = tubiaos[i].getPointy();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (tuozhuai) {
				for (int i = 0; i < pointnum; i++) {
					tubiaos[i].setPointx(tuozhuaibuffx[i] + e.getX() - x);
					tubiaos[i].setPointy(tuozhuaibuffy[i] + e.getY() - y);
				}
				huitupanel.displayzhengti(tubiaos, pointnum);
			} else if (dianjifangda == false && dianjisuoxiao == false) {
				if ((e.getX() - x > 0) && (e.getY() - y > 0)) {
					huitupanel.setPainwitch(1);
					for (int i = 0; i < pointnum; i++) {
						if (tubiaos[i] instanceof Bazhi) {
							if ((tubiaos[i].getPointx() > x - 32) && (tubiaos[i].getPointx() < e.getX())
									&& (tubiaos[i].getPointy() > y - 32) && (tubiaos[i].getPointy() < e.getY())
									&& (((Bazhi) (tubiaos[i])).getSuodingyf() == 1)) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(true);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							}
						}
					}
				} else if ((e.getX() - x > 0) && (e.getY() - y < 0)) {
					huitupanel.setPainwitch(2);
					for (int i = 0; i < pointnum; i++) {
						if (tubiaos[i] instanceof Bazhi) {
							if ((tubiaos[i].getPointx() > x - 32) && (tubiaos[i].getPointx() < e.getX())
									&& (tubiaos[i].getPointy() > e.getY() - 32) && (tubiaos[i].getPointy() < y)
									&& (((Bazhi) (tubiaos[i])).getSuodingyf() == 1)) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(true);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							}
						}
					}
				} else if ((e.getX() - x < 0) && (e.getY() - y < 0)) {
					huitupanel.setPainwitch(3);
					for (int i = 0; i < pointnum; i++) {
						if (tubiaos[i] instanceof Bazhi) {
							if ((tubiaos[i].getPointx() > e.getX() - 32) && (tubiaos[i].getPointx() < x)
									&& (tubiaos[i].getPointy() > e.getY() - 32) && (tubiaos[i].getPointy() < y)
									&& (((Bazhi) (tubiaos[i])).getSuodingyf() == 1)) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(true);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							}
						}
					}
				} else if ((e.getX() - x < 0) && (e.getY() - y > 0)) {
					huitupanel.setPainwitch(4);
					for (int i = 0; i < pointnum; i++) {
						if (tubiaos[i] instanceof Bazhi) {
							if ((tubiaos[i].getPointx() > e.getX() - 32) && (tubiaos[i].getPointx() < x)
									&& (tubiaos[i].getPointy() > y - 32) && (tubiaos[i].getPointy() < e.getY())
									&& (((Bazhi) (tubiaos[i])).getSuodingyf() == 1)) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(true);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							}
						}
					}
				}
				huitupanel.displayjuxing(x, y, e.getX(), e.getY(), tubiaos);
			}
		}

		// 鼠标点击事件若是靶则动作
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (e.getButton() == MouseEvent.BUTTON3) {
				// 弹出右键菜单
				popupMenu.show(huitupanel, e.getX(), e.getY());
			} else {
				if (dianjifangda) {
					for (int i = 0; i < pointnum; i++) {
						tubiaos[i].setPointx((int) (1.1 * (datacurrent[i][0] - x) + x));
						tubiaos[i].setPointy((int) (1.1 * (datacurrent[i][1] - y) + y));
					}
					huitupanel.displayzhengti(tubiaos, pointnum);
				} else if (dianjisuoxiao) {

					for (int i = 0; i < pointnum; i++) {
						tubiaos[i].setPointx((int) ((tubiaos[i].getPointx() - x) / 1.1 + x));
						tubiaos[i].setPointy((int) (((tubiaos[i].getPointy() - y)) / 1.1 + y));

					}
					huitupanel.displayzhengti(tubiaos, pointnum);
				} else {
					int i;
					for (i = 0; i < pointnum; i++) {
						if (tubiaos[i] instanceof Bazhi) {
							if ((e.getX() > tubiaos[i].getPointx()) && (e.getX() < (tubiaos[i].getPointx() + 54))
									&& (e.getY() > tubiaos[i].getPointy()) && (e.getY() < (tubiaos[i].getPointy() + 54))
									&& ((((Bazhi) (tubiaos[i])).getSuodingyf() == 1)
											|| (((Bazhi) (tubiaos[i])).getSuodingyf() == 2))) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(true);
								commandlistenerInterface.bazhiAction(((Bazhi) (tubiaos[i])));
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
								((Bazhi) (tubiaos[i])).setSuodingyf(0);
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setIsxuanzhong(false);
								((Bazhi) (tubiaos[i])).setIsdongzuo(false);
								((Bazhi) (tubiaos[i])).setIsdazhong(false);
								((Bazhi) (tubiaos[i])).setIsweidazhong(false);
							}
						}
					}
					huitupanel.displayzhengti(tubiaos, pointnum);
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (biaoqianstate) {
				if (tubiaos[buffbianhao] instanceof Bazhi) {
					if ((e.getX() > (bfx - 3)) && (e.getX() < (bfx + 57)) && (e.getY() > bfy - 62)
							&& (e.getY() < bfy)) {

					} else {
						huitupanel.setPainwitch(0);
						bfx = 0;
						bfy = 0;
						huitupanel.displayzhengti(tubiaos, pointnum);
					}
				} else if (tubiaos[buffbianhao] instanceof Dabarenyuan) {
					if ((e.getX() > (bfx - 3)) && (e.getX() < (bfx + 57)) && (e.getY() > bfy - 42)
							&& (e.getY() < bfy)) {

					} else {
						huitupanel.setPainwitch(0);
						bfx = 0;
						bfy = 0;
						huitupanel.displayzhengti(tubiaos, pointnum);
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			int i;
			int bianhao = 0;
			buffbianhao = 0;
			for (i = 0; i < pointnum; i++) {
				if ((e.getX() > (tubiaos[i].getPointx() + 5)) && (e.getX() < (tubiaos[i].getPointx() + 54 - 5))
						&& (e.getY() > tubiaos[i].getPointy() - 24) && (e.getY() < (tubiaos[i].getPointy()))) {
					if ((tubiaos[i] instanceof Bazhi)) {
						huitupanel.setPainwitch(5);
						bfx = tubiaos[i].getPointx();
						bfy = tubiaos[i].getPointy();
						bianhao = ((Bazhi) (tubiaos[i])).getBianhao();
						buffbianhao = i;
						biaoqianstate = true;
						break;
					} else if (tubiaos[i] instanceof Dabarenyuan) {
						huitupanel.setPainwitch(6);
						bfx = tubiaos[i].getPointx();
						bfy = tubiaos[i].getPointy();
						bianhao = ((Dabarenyuan) (tubiaos[i])).getBianhao();
						buffbianhao = i;
						biaoqianstate = true;
						break;
					}
				} else {
					huitupanel.setPainwitch(0);
					biaoqianstate = false;
				}
			}
			huitupanel.displayxinxi(tubiaos[buffbianhao].getPointx(), tubiaos[buffbianhao].getPointy(), tubiaos,
					bianhao);
		}
	}

	@Override
	public void dataInitial(Bazhi[] bazhis, Dabarenyuan[] dabarenyuans) {
		// TODO 自动生成的方法存根
		System.out.println("数据初始化");
	}

	@Override
	public void dataInitial(Tubiao[] tubiaos) {
		// TODO 自动生成的方法存根
		System.out.println("数据初始化");
	}

	@Override
	public void dataUpdate(Bazhi[] bazhis, Dabarenyuan[] dabarenyuans) {
		// TODO 自动生成的方法存根
		System.out.println("数据更新");
		
	}

	@Override
	public void dataUpdate(Tubiao[] tubiaos) {
		// TODO 自动生成的方法存根
		System.out.println("数据更新");
		System.out.println();
	}
}

class Reflesh extends Thread {
	private Huitupanel huitupanel = new Huitupanel();
	private boolean w = true;
	Random random = new Random();

	public Reflesh(String name) {
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			for (int i = 0; i < Daba1.pointnum; i++) {
				if (Daba1.tubiaos[i] instanceof Bazhi) {
					if (((Bazhi) (Daba1.tubiaos[i])).getSuodingyf() == 0) {
						if (((Bazhi) (Daba1.tubiaos[i])).getBianhao() % 2 == 0) {
							((Bazhi) (Daba1.tubiaos[i])).setIsweidazhong(((random.nextInt(10)) < 5 ? true : false));
							((Bazhi) (Daba1.tubiaos[i])).setIsdazhong(!((Bazhi) (Daba1.tubiaos[i])).isIsweidazhong());
							((Bazhi) (Daba1.tubiaos[i])).setIsxuanzhong(false);
							((Bazhi) (Daba1.tubiaos[i])).setIsdongzuo(false);
						} else {
							((Bazhi) (Daba1.tubiaos[i])).setIsweidazhong(!w);
							((Bazhi) (Daba1.tubiaos[i])).setIsdazhong(!((Bazhi) (Daba1.tubiaos[i])).isIsweidazhong());
							((Bazhi) (Daba1.tubiaos[i])).setIsxuanzhong(false);
							((Bazhi) (Daba1.tubiaos[i])).setIsdongzuo(false);
						}
						((Bazhi) (Daba1.tubiaos[i])).setSuodingyf(2);
					}
				}
			}
			w = !w;
			huitupanel.displayzhengti(Daba1.tubiaos, Daba1.pointnum);
		}
	}
}
