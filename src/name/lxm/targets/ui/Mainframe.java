package name.lxm.targets.ui;

import java.awt.*;
import javax.swing.*;

import name.lxm.targets.interfaces.Commandstatelistener;
import name.lxm.targets.interfaces.Groupinfoupdatelistener;
import name.lxm.targets.interfaces.WirelessListener;
import name.lxm.targets.model.TargetsCollection;
import name.lxm.targets.model.TraineeEntityCollection;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author Administrator
 * 这是主界面类
 *
 */
public class Mainframe extends JFrame implements WirelessListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159741947428856082L;
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

	private static Reflesh reflesh = new Reflesh("reflesh");// 线程刷新界面
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
	public static boolean chushihuastate = false;
	public static boolean jiuxustate = false;
	public static boolean shuimianstate = false;
	public static boolean yunxinstate = false;
	public static boolean tuichustate = true;
	public static final int chushihuastaten = 1;
	public static final int jiuxustaten = 2;
	public static final int shuimianstaten = 3;
	public static final int yunxinstaten = 4;
	public static final int tuichustaten = 5;
	public static int bazhishu;
	public static int dabarenyuanshu;
	public static UIEventListener uiEventListener;
	public static Commandstatelistener commandstatelistener;
	public static Groupinfoupdatelistener groupinfoupdatelistener;

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	static TMUIControlPanel tmuiControlPanel = new TMUIControlPanel();
	static TMUITargetStatusPanel tmuiTargetStatusPanel = new TMUITargetStatusPanel();
	DefineJpanel defineJpanel;
	DefineJpanel[] defineJpanels = new DefineJpanel[30];// 自定义的panel，用来分组的
	static Huitupanel huitupanel = new Huitupanel();// 新建地图panel
	JTextArea dataarea = new JTextArea();

	public static JButton fuweibutton = new JButton("复位");
	public static JButton fangdabutton = new JButton("");
	public static JButton suoxiaobutton = new JButton("");
	public static JButton tuozhuaibutton = new JButton("拖拽");
	public static JButton dianjifangdabutton = new JButton("点击放大");
	public static JButton dianjisuoxiaobutton = new JButton("点击缩小");
	public static JButton suofangbutton = new JButton("");

	public static JToolBar jToolBar = new JToolBar("工具");
	public static JPopupMenu popupMenu = new JPopupMenu("菜单");
	public static JMenuItem dongzuomenu = new JMenuItem("动作");
	public static JMenuItem tianjiafenzumenu = new JMenuItem("添加分组");
	public static JMenuItem tianjiadaozumenu = new JMenuItem("添加到组");
	public static JMenuItem tuichufenzumenu = new JMenuItem("退出分组");
	public static JMenuItem quanxuanmenu = new JMenuItem("全选");
	public static JButton chushihuabutton = new JButton("初始化");
	public static JButton qidongbutton = new JButton("启动");
	public static JButton tingzhibutton = new JButton("停止");

	public Mainframe(String title) {
		super(title);
		Mainframe.tuichustate = true;
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
		fangdabutton.setIcon(icon7);
		jToolBar.setBorder(null);
		jToolBar.setOrientation(SwingConstants.VERTICAL);

		jToolBar.add(fangdabutton);
		suoxiaobutton.setIcon(icon8);
		jToolBar.add(suoxiaobutton);
		jToolBar.add(suofangbutton);
		jToolBar.add(tuozhuaibutton);
		jToolBar.add(dianjifangdabutton);
		jToolBar.add(dianjisuoxiaobutton);
		jToolBar.add(fuweibutton);
		suofangbutton.setIcon(icon6);// 全屏

		jToolBar.setFloatable(true);
		panel1.add(jToolBar, BorderLayout.EAST);

		jToolBar.add(chushihuabutton);
		chushihuabutton.addActionListener(new Chushihualistener());
		jToolBar.add(qidongbutton);
		qidongbutton.setEnabled(false);
		qidongbutton.addActionListener(new Qidonglistener());
		jToolBar.add(tingzhibutton);
		tingzhibutton.setEnabled(false);

		tingzhibutton.addActionListener(new Tingzhilistener());
		panel1.add(huitupanel, BorderLayout.CENTER);

		tmuiControlPanel.setPreferredSize(new Dimension(313, 0));
		tmuiTargetStatusPanel.setPreferredSize(new Dimension(0, 100));

		container.add(panel1, BorderLayout.CENTER);
		container.add(tmuiControlPanel, BorderLayout.EAST);
		container.add(tmuiTargetStatusPanel, BorderLayout.SOUTH);

		fuweibutton.addActionListener(new Fuweilistener());
		fangdabutton.addActionListener(new Fangdalistener());
		suoxiaobutton.addActionListener(new Suoxiaolistener());
		tuozhuaibutton.addActionListener(new Tuozhuailistener());
		dianjifangdabutton.addActionListener(new Dianjifangdalistener());
		dianjisuoxiaobutton.addActionListener(new Dianjisuoxiaolistener());
		suofangbutton.addActionListener(new Suofanglistener());
		// chushihuabutton.addActionListener(commandlistenerInterface);
		
		popupMenu.add(quanxuanmenu);
		popupMenu.add(dongzuomenu);
		popupMenu.add(tianjiafenzumenu);
		popupMenu.add(tianjiadaozumenu);
		popupMenu.add(tuichufenzumenu);
		huitupanel.add(popupMenu);

		quanxuanmenu.addActionListener(new Quanxuanlistener());
		dongzuomenu.addActionListener(new Dongzuolistener());
		tianjiafenzumenu.addActionListener(new TianjiafenzuListener());
		tianjiadaozumenu.addActionListener(new Tianjiadaozulistener());
		tuichufenzumenu.addActionListener(new Tuichufenzulistener());

		huitupanel.addMouseListener(new Mymouse());
		huitupanel.addMouseMotionListener(new Mymouse());

		this.setVisible(true);
	}
	public void addUIeventListener(UIEventListener listener){
		uiEventListener=listener;
	}
	public void addGroupinfoUpdateListener(Groupinfoupdatelistener listener){
		groupinfoupdatelistener=listener;
	}
	public void addCommandstateListener(Commandstatelistener listener){
		commandstatelistener=listener;
	}

	private class Fuweilistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setsysstate(tuichustaten);//系统切换为退出状态
			chushihuabutton.setEnabled(true);//初始化按钮可用
			qidongbutton.setEnabled(false);//启动按钮不可用
			tingzhibutton.setEnabled(false);//停止按钮不可用
			//commandlistenerInterface.systemReset();
			commandstatelistener.sysReset();//向通讯模块发送系统复位命令
		}
	}

	private class Chushihualistener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (tuichustate) {
				//commandlistenerInterface.systemInitialize();
				commandstatelistener.sysInitialize();//向通讯模块发送系统初始化命令
				systemdatainit();// 界面单独运行时数据初始化，读取文本中的数据
				huitupanel.displayzhengti(tubiaos, pointnum);//初次化地图并显示，将图标显示出来
				tmuiControlPanel.getPanel_7().removeAll();//移除分组面板的小组界面
				tmuiControlPanel.getPanel_7().repaint();//对分组面板进行重绘
				if (reflesh.isAlive()) {
				} else {
					reflesh.start();
				}//启动一个线程不断更新数据，这是在单独界面模块用来测试用的
				// 获取分组信息，将分组信息作为参数传给通讯模块
				Bazhiz[] bazhizs = new Bazhiz[Guanlifenzu.getZushu()];//创建一个靶子组数组，数组长度为当前靶子组的个数
				for (int i = 0; i < bazhizs.length; i++) {
					bazhizs[i] = Guanlifenzu.getBazhizs()[i + 1];
				}//获取当前的所有靶子组并赋值给上面新创建的靶子组数组
				//commandlistenerInterface.groupinfoUpdate(bazhizs);
				
				tmuiTargetStatusPanel.statusChanged("系统初始化");//更新系统消息
				setsysstate(chushihuastaten);//将系统状态设置为初始化状态
				chushihuabutton.setEnabled(false);//初始化按钮不可用
				tmuiControlPanel.getSavegroupButton().setEnabled(true);//保存分组按钮可用
			} else {
				System.out.println("请先复位");
			}
		}
	}

	private class Qidonglistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (jiuxustate) {//如果是在就绪状态下按启动
				tmuiControlPanel.getTimeoutthread().interrupt();//中断一个线程，这个线程是用来检测系统是否要进入睡眠状态的
				setsysstate(yunxinstaten);//设置系统状态为运行状态
				//commandlistenerInterface.systemStart();
				commandstatelistener.sysStart();//向通讯模块发送系统启动命令
				qidongbutton.setEnabled(false);//启动按钮不可用
				tingzhibutton.setEnabled(true);//停止按钮可用
			} else if (shuimianstate) {//如果是在睡眠状态下按启动按钮
				setsysstate(jiuxustaten);//系统进入就绪状态
				qidongbutton.setText("启动");//将启动按钮的名字设置为启动
				//commandlistenerInterface.systemReady();
				commandstatelistener.sysReady();//向通讯模块发送系统已就绪命令
				tmuiControlPanel.setTimeoutthread(new Timeoutthread());
				tmuiControlPanel.getTimeoutthread().start();//启动一个新的线程来检测系统是否进入睡眠状态
			} else {
				System.out.println("分组还未就绪");
			}
		}
	}

	private class Tingzhilistener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (yunxinstate == true) {//如果是在运行状态下
				setsysstate(jiuxustaten);//系统进入就绪状态
				tmuiControlPanel.setTimeoutthread(new Timeoutthread());
				tmuiControlPanel.getTimeoutthread().start();//开启一个新的线程用来检测系统是否进入睡眠状态
//				commandlistenerInterface.systemStop();
				commandstatelistener.sysStop();//向通讯模块发送停止命令
				qidongbutton.setEnabled(true);//启动按钮不可用
				tingzhibutton.setEnabled(false);//停止按钮不可用
			} else {
				System.out.println("现在不是运行状态");
			}
		}
	}

	/**
	 * @author Administrator 
	 * 全选，即选中所有靶子
	 *
	 */
	public class Quanxuanlistener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			for (int i = 0; i < pointnum; i++) {
				if (tubiaos[i] instanceof Bazhi) {
					if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
						((Bazhi) (tubiaos[i])).setstateXuanzhong();
					} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
						((Bazhi) (tubiaos[i])).setstateDefault();
					}
				}
			}
			huitupanel.displayzhengti(tubiaos, pointnum);
		}
	}

	/**
	 * @author Administrator
	 * 给选中的靶子添加分组
	 *
	 */
	private class TianjiafenzuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (chushihuastate) {//如果是在初始化状态下
				String strbazhi = getXuanzhong();// 获取选中的靶子转化为字符串形式
				String stringbianhao=getXuanzhongbiaohao();
				Tianjiafenzumsgbox tianjiafenzumsgbox = new Tianjiafenzumsgbox(Guanlifenzu.getZushu());// 创建对话框
				tianjiafenzumsgbox.getTextField().setText(stringbianhao);// 设置对话框内容
				tianjiafenzumsgbox.setVisible(true);// 显示对话框
				tianjiafenzumsgbox.getButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tianjiafenzumsgbox.dispose();// 关闭对话框
					}
				});
				//在弹出的对话中点击确定后作以下操作，把选中的靶子天剑到分组
				tianjiafenzumsgbox.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 获取对话框的内容
						String[] mStrings = strbazhi.split(",");
						int j = mStrings.length;
						int[] a = new int[mStrings.length];
						// 将选中的靶子提取出来成为一个参数（靶子组）
						if (!mStrings[0].equals("")) {
							for (int i = 0; i < mStrings.length; i++) {
								a[i] = Integer.parseInt(mStrings[i]);
							}
							Bazhi[] bazhiijk = new Bazhi[j];
							for (int i = 0; i < j; i++) {
								bazhiijk[i] = bazhis[a[i]];
							}
							System.out.println(bazhiijk.length);
							Guanlifenzu.creatfenzu(bazhiijk);// 创建分组
							Guanlifenzu.showpanel();// 显示创建的分组
						} else {
							Bazhi[] bazhikji = new Bazhi[0];
							Guanlifenzu.creatfenzu(bazhikji);
							// Guanlifenzu.creatnewpanel();
							Guanlifenzu.showpanel();
						}
						//Guanlifenzu.groupinfoUpdate();//更新分组信息到通讯模块
						tmuiControlPanel.getSavegroupButton().setEnabled(true);//保存分组按钮可用
						tianjiafenzumsgbox.dispose();
					}
				});

			}
		}
	}

	/**
	 * @author Administrator
	 * 将选中的靶子添加到已有分组
	 *
	 */
	private class Tianjiadaozulistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (chushihuastate) {//如果是在初始化状态下
				String string = getXuanzhong();
				String stringbianhao=getXuanzhongbiaohao();
				Tianjiadaozumsgbox tianjiadaozumsgbox = new Tianjiadaozumsgbox(Guanlifenzu.getZushu(), stringbianhao);
				tianjiadaozumsgbox.setVisible(true);
				//在弹出的对话框中点击确定以后做以下操作，将选中的靶子添加到某一组
				tianjiadaozumsgbox.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] mStrings = string.split(",");
						int j = mStrings.length;
						int[] a = new int[mStrings.length];
						if (!mStrings[0].equals("")) {
							for (int i = 0; i < mStrings.length; i++) {
								a[i] = Integer.parseInt(mStrings[i]);
							}
							Bazhi[] bazhiijk = new Bazhi[j];
							for (int i = 0; i < j; i++) {
								bazhiijk[i] = bazhis[a[i]];
							}
							Guanlifenzu.tianjiaDaozu((int) tianjiadaozumsgbox.getComboBox().getSelectedItem(), bazhiijk);
							Guanlifenzu.showpanel();
						} else {
							Bazhi[] bazhikji = new Bazhi[0];
							Guanlifenzu.tianjiaDaozu((int) tianjiadaozumsgbox.getComboBox().getSelectedItem(), bazhikji);
							Guanlifenzu.showpanel();
						}
						//Guanlifenzu.groupinfoUpdate();//更新分组信息到通讯模块
						tmuiControlPanel.getSavegroupButton().setEnabled(true);//保存分组按钮可用
						tianjiadaozumsgbox.dispose();
					}
				});
				tianjiadaozumsgbox.getBtnNewButton_1().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tianjiadaozumsgbox.dispose();
					}
				});
			}
		}
	}

	/**
	 * @author Administrator
	 * 退出分组
	 *
	 */
	private class Tuichufenzulistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (chushihuastate) {
				String strbazhi = getXuanzhong();
				int[] a = Toolsfunction.str2num(strbazhi);
				Bazhi[] bazhisijk = new Bazhi[a.length];
				for (int i = 0; i < a.length; i++) {
					bazhisijk[i] = bazhis[a[i]];
				}
				Guanlifenzu.tuichuFenzhu(bazhisijk);
				Guanlifenzu.showpanel();
				//Guanlifenzu.groupinfoUpdate();//更新分组信息到通讯模块
				tmuiControlPanel.getSavegroupButton().setEnabled(true);//保存分组按钮可用
			}
		}
	}

	/**
	 * @author Administrator
	 * 放大地图，以屏幕中心为中心放大地图
	 *
	 */
	private class Fangdalistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			for (int i = 0; i < pointnum; i++) {
				tubiaos[i].setPointx(
						(int) (1.1 * (tubiaos[i].getPointx() - huitupanel.getWidth() / 2) + huitupanel.getWidth() / 2));
				tubiaos[i].setPointy(
						(int) (1.1 * (tubiaos[i].getPointy() - huitupanel.getWidth() / 2) + huitupanel.getWidth() / 2));
			}
			huitupanel.displayzhengti(tubiaos, pointnum);
		}
	}

	/**
	 * @author Administrator
	 * 地图缩小
	 *
	 */
	private class Suoxiaolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			for (int i = 0; i < pointnum; i++) {
				tubiaos[i].setPointx(
						(int) ((tubiaos[i].getPointx() - huitupanel.getWidth() / 2) / 1.1 + huitupanel.getWidth() / 2));
				tubiaos[i].setPointy(
						(int) ((tubiaos[i].getPointy() - huitupanel.getWidth() / 2) / 1.1 + huitupanel.getWidth() / 2));
			}
			huitupanel.displayzhengti(tubiaos, pointnum);
		}
	}

	/**
	 * @author Administrator
	 * 地图拖拽
	 *
	 */
	private class Tuozhuailistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			tuozhuai = !tuozhuai;
			dianjifangda = false;
			dianjisuoxiao = false;
			if (tuozhuai) {

				huitupanel.setCursor(cutuozhuai);
				tuozhuaibutton.setBackground(Color.red);
				dianjifangdabutton.setBackground(null);
				dianjisuoxiaobutton.setBackground(null);
			} else {
				huitupanel.setCursor(null);
				tuozhuaibutton.setBackground(null);
			}
			for (int i = 0; i < pointnum; i++) {
				tuozhuaibuffx[i] = tubiaos[i].getPointx();
				tuozhuaibuffy[i] = tubiaos[i].getPointy();
			}
		}
	}

	/**
	 * @author Administrator
	 * 地图点击放大
	 *
	 */
	private class Dianjifangdalistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			dianjifangda = !dianjifangda;
			dianjisuoxiao = false;
			tuozhuai = false;
			if (dianjifangda) {
				huitupanel.setCursor(cufaangda);
				dianjifangdabutton.setBackground(Color.red);
				dianjisuoxiaobutton.setBackground(null);
				tuozhuaibutton.setBackground(null);
			} else {
				huitupanel.setCursor(null);
				dianjifangdabutton.setBackground(null);
			}
		}
	}

	/**
	 * @author Administrator
	 * 地图点击缩小
	 *
	 */
	private class Dianjisuoxiaolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			dianjisuoxiao = !dianjisuoxiao;
			dianjifangda = false;
			tuozhuai = false;
			if (dianjisuoxiao) {
				huitupanel.setCursor(cusuoxiao);
				dianjisuoxiaobutton.setBackground(Color.red);
				dianjifangdabutton.setBackground(null);
				tuozhuaibutton.setBackground(null);
			} else {
				huitupanel.setCursor(null);
				dianjisuoxiaobutton.setBackground(null);
			}
		}
	}

	/**
	 * @author Administrator
	 * 选中的靶子动作
	 *
	 */
	private class Dongzuolistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (yunxinstate) {
				String string = getXuanzhong();// 获取选中的靶子
				//String stringbianhao=getXuanzhongbiaohao();
				int[] a = Toolsfunction.str2num(string);// 将选中的靶子转化为数组形式
				Bazhi[] bazhisijk = new Bazhi[a.length];
				for (int i = 0; i < a.length; i++) {
					bazhisijk[i] = bazhis[a[i]];
					uiEventListener.processStandEvent(TargetsCollection.INSTANCE.get(a[i]));//发送靶子动作命令
				}
				//commandlistenerInterface.bazhisAction(bazhisijk);
			}
		}
	}

	
	/**
	 * @author Administrator
	 * 屏幕的缩放
	 *
	 */
	private class Suofanglistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (zinout == 0) {
				tmuiControlPanel.setVisible(false);
				tmuiTargetStatusPanel.setVisible(false);
				suofangbutton.setIcon(icon5);
				zinout = 1 - zinout;
			} else {
				tmuiControlPanel.setVisible(true);
				tmuiTargetStatusPanel.setVisible(true);
				suofangbutton.setIcon(icon6);
				zinout = 1 - zinout;
			}
		}
	}

	private class Mymouse extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
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
			// TODO 自动生成的方法存根
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
								((Bazhi) (tubiaos[i])).setstateXuanzhong();
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setstateDefault();
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
								((Bazhi) (tubiaos[i])).setstateXuanzhong();
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setstateDefault();
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
								((Bazhi) (tubiaos[i])).setstateXuanzhong();
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setstateDefault();
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
								((Bazhi) (tubiaos[i])).setstateXuanzhong();
							} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 1) {
								((Bazhi) (tubiaos[i])).setstateDefault();
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
						tubiaos[i].setPointx((int) (1.1 * (tubiaos[i].getPointx() - x) + x));
						tubiaos[i].setPointy((int) (1.1 * (tubiaos[i].getPointy() - y) + y));
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
								if (yunxinstate) {
									System.out.println("使摆子成为动作状态");
									((Bazhi) (tubiaos[i])).setSuodingyf(0);
									((Bazhi) (tubiaos[i])).setstateDongzuo();
									//commandlistenerInterface.bazhiAction(((Bazhi) (tubiaos[i])));
									uiEventListener.processStandEvent(TargetsCollection.INSTANCE.get(((Bazhi) (tubiaos[i])).getBianhao()));
								} else if (((Bazhi) (tubiaos[i])).getSuodingyf() == 0
										|| ((Bazhi) (tubiaos[i])).getSuodingyf() == 2) {
									System.out.println("不允许操作");
								} else {
									((Bazhi) (tubiaos[i])).setIsdongzuo(false);
									((Bazhi) (tubiaos[i])).setIsdazhong(false);
									((Bazhi) (tubiaos[i])).setIsweidazhong(false);
								}

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
			// TODO 自动生成的方法存根
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
			// TODO 自动生成的方法存根
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

	/* （非 Javadoc）
	 * when the data update,this function would be called
	 * @see name.lxm.targets.interfaces.WirelessListener#onDataReceived()
	 */
	@Override
	public void onDataReceived() {
		// TODO 自动生成的方法存根
		int a=TargetsCollection.INSTANCE.getCount();
		bazhis=new Bazhi[a];
		for(int i=0;i<a;i++){
			bazhis[i].setBianhao(TargetsCollection.INSTANCE.get(i).getID());//靶子编号
			bazhis[i].setChengji(TargetsCollection.INSTANCE.get(i).getGrade());//靶子成绩
			bazhis[i].setDianliang(TargetsCollection.INSTANCE.get(i).getVoltage());//电量
			bazhis[i].setGroup(TargetsCollection.INSTANCE.get(i).getGroup());//所在组
			bazhis[i].setPointx((int)(TargetsCollection.INSTANCE.get(i).getGps().getLat()));//x坐标
			bazhis[i].setPointy((int)(TargetsCollection.INSTANCE.get(i).getGps().getLon()));//y坐标
			bazhis[i].setPose(TargetsCollection.INSTANCE.get(i).getStatus());//靶子姿势
			bazhis[i].setQiya(TargetsCollection.INSTANCE.get(i).getPresure());//气压
			bazhis[i].setTemprature(TargetsCollection.INSTANCE.get(i).getTemprature());//温度
			bazhis[i].setIsdazhong(TargetsCollection.INSTANCE.get(i).getStatus()>0?true:false);
			bazhis[i].setIsweidazhong(!(TargetsCollection.INSTANCE.get(i).getStatus()>0?true:false));
			bazhis[i].setZuhao(0);
			bazhis[i].setSeqbianhao(i);//靶子序列编号
			bazhis[i].setHit(0);//
			bazhis[i].setLight(0);//灯光
			bazhis[i].setDebug(null);//调试数据
			bazhis[i].setAlert(null);//报警数据
			}
		int b=TraineeEntityCollection.INSTANCE.getSize();
		dabarenyuans=new Dabarenyuan[b];
		for(int i=0;i<b;i++){
			dabarenyuans[i].setBianhao(TraineeEntityCollection.INSTANCE.get(i).getID());
			dabarenyuans[i].setPointx((int)(TraineeEntityCollection.INSTANCE.get(i).getGps().getLat()));
			dabarenyuans[i].setPointy((int)(TraineeEntityCollection.INSTANCE.get(i).getGps().getLon()));
			dabarenyuans[i].setXingming(TraineeEntityCollection.INSTANCE.get(i).getName());
			dabarenyuans[i].setSeqbianhao(i);
			dabarenyuans[i].setDebuge(null);
			dabarenyuans[i].setAlert(null);
		}
		pointnum=a+b;
		tubiaos=new Tubiao[pointnum];
		int k;
		for(k=0;k<a;k++){
			tubiaos[k]=bazhis[k];
		}
		for(int j=0;j<b;j++){
			tubiaos[k+j]=dabarenyuans[j];
		}
	}

	/**
	 * @return 返回选中的靶子的bianhao
	 */
	private String getXuanzhongbiaohao() {
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
	/**
	 * @return 返回选中的靶子的seqbianhao
	 */
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
			str = str + String.valueOf(((Bazhi) (tubiaos[k[i]])).getSeqbianhao()) + ",";
		}
		if (j > 0) {
			strbazhi = str.substring(0, str.length() - 1);
		}
		return strbazhi;
	}

	public static void setsysstate(int a) {
		switch (a) {
		case chushihuastaten: {
			chushihuastate = true;
			jiuxustate = false;
			shuimianstate = false;
			yunxinstate = false;
			tuichustate = false;
		}
			break;
		case jiuxustaten: {
			chushihuastate = false;
			jiuxustate = true;
			shuimianstate = false;
			yunxinstate = false;
			tuichustate = false;
		}
			break;
		case shuimianstaten: {
			chushihuastate = false;
			jiuxustate = false;
			shuimianstate = true;
			yunxinstate = false;
			tuichustate = false;
		}
			break;
		case yunxinstaten: {
			chushihuastate = false;
			jiuxustate = false;
			shuimianstate = false;
			yunxinstate = true;
			tuichustate = false;
		}
			break;
		case tuichustaten: {
			chushihuastate = false;
			jiuxustate = false;
			shuimianstate = false;
			yunxinstate = false;
			tuichustate = true;
		}
			break;
		default:
			break;
		}
	}

	/**
	 * 在只有主界面的情况下单独运行时，用来初始化数据的（从文本文件读取数据）
	 * data initialize
	 */
	public static void systemdatainit() {
		System.out.println("数据初始化");
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
		int j = 0;
		int k = 0;
		for (int i = 0; i < pointnum; i++) {
			data[i][5] = 0;
			data[i][4] = 0;
			data[i][6] = 0;
			data[i][7] = 0;
			data[i][8] = 1;
			if (data[i][2] == 1) {
				bazhis[j]=new Bazhi();
				bazhis[j].setBianhao(data[i][3]);
				bazhis[j].setSeqbianhao(j);
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
				dabarenyuans[k].setSeqbianhao(k);
				dabarenyuans[k].setBianhao(k);
				dabarenyuans[k].setPointx(data[i][0]);
				dabarenyuans[k].setPointy(data[i][1]);
				tubiaos[i] = dabarenyuans[k];
				k++;
			}
		}
		bazhishu = j;
		dabarenyuanshu = k;
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
			for (int i = 0; i < Mainframe.pointnum; i++) {
				if (Mainframe.tubiaos[i] instanceof Bazhi) {
					if (((Bazhi) (Mainframe.tubiaos[i])).getSuodingyf() == 0) {
						if (((Bazhi) (Mainframe.tubiaos[i])).getBianhao() % 2 == 0) {
							((Bazhi) (Mainframe.tubiaos[i])).setIsweidazhong(((random.nextInt(10)) < 5 ? true : false));
							((Bazhi) (Mainframe.tubiaos[i])).setIsdazhong(!((Bazhi) (Mainframe.tubiaos[i])).isIsweidazhong());
							((Bazhi) (Mainframe.tubiaos[i])).setIsxuanzhong(false);
							((Bazhi) (Mainframe.tubiaos[i])).setIsdongzuo(false);
						} else {
							((Bazhi) (Mainframe.tubiaos[i])).setIsweidazhong(!w);
							((Bazhi) (Mainframe.tubiaos[i])).setIsdazhong(!((Bazhi) (Mainframe.tubiaos[i])).isIsweidazhong());
							((Bazhi) (Mainframe.tubiaos[i])).setIsxuanzhong(false);
							((Bazhi) (Mainframe.tubiaos[i])).setIsdongzuo(false);
						}
						((Bazhi) (Mainframe.tubiaos[i])).setSuodingyf(2);
					}
				}
			}
			w = !w;
			huitupanel.displayzhengti(Mainframe.tubiaos, Mainframe.pointnum);
		}
	}
}
