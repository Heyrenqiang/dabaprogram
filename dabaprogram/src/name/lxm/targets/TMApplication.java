/*
 * May be I should put something here in the future.
 */
package name.lxm.targets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import name.lxm.targets.exception.SetNodeIDException;
import name.lxm.targets.exception.WrongFrameFormatException;
import name.lxm.targets.http.HttpServer;
import name.lxm.targets.model.ActionCommand;
import name.lxm.targets.model.ActionQueue;
import name.lxm.targets.model.GroupCollection;
import name.lxm.targets.model.GroupEntity;
import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.model.TargetsCollection;
import name.lxm.targets.model.TraineeEntity;
import name.lxm.targets.model.TraineeEntityCollection;
import name.lxm.targets.ui.UIEventListener;
import name.lxm.targets.ui.simulation.MainWin;
import name.lxm.targets.wireless.TargetsManager;

/**
 * 
 * @author Xiaoming Li Mar 17, 2017
 *
 */
public class TMApplication implements UIEventListener {
	
	/**
	 * iMode is used to identify the current state of the system. 
	 * It can be one of the values:
	 * 0: uninitialized;
	 * 1: initializing;
	 * 2: initialized;
	 * 3: ready;
	 * 4: acting;
	 * 
	 */
	private int iMode = 0; //默认一运行就进行入未初始化状态。
	private boolean bRun = false;
	private int runMode = 0;
	
	private static final int RUNMODE_ONLINE = 0;
	private static final int RUNMODE_OFFLINE = 1;
	private static final int RUNMODE_ONLINE_DEBUG = 2;
	private static final int RUNMODE_SIMULATE = 3;
	
	public static final int MODE_UNINIT = 0;
	public static final int MODE_INITIALIZING = 1;
	public static final int MODE_INITIALIZED = 2;
	public static final int MODE_READY = 3;
	public static final int MODE_ACTING = 4;
	
	public static final  TMApplication INSTANCE = new TMApplication();
	private Logger log = Logger.getLogger(this.getClass().getName());
	TargetsManager tm = null;
	private boolean bWirelessInit;
	
	
	private TMApplication(){
		log.setLevel(Level.ALL);
		tm = new TargetsManager();
	}
	
	public int getMode()
	{
		return iMode;
	}
	
	
	/**
	 * initialization of the Application. It should be called before anything else.
	 * and be sure to call it only once. 
	 */
	public void init()
	{
		//initialize the UI, show the UI.
		if(runMode == RUNMODE_ONLINE_DEBUG) initDebugUI();
		if(runMode == RUNMODE_ONLINE || runMode == RUNMODE_SIMULATE) initUI();
		//done!
		iMode = MODE_INITIALIZED;
	}
	
	private void initUI() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method will generate some data which can be used to
	 * simulate the process of control.
	 */
	private void simulate()
	{
		//1: nothing to do.

		//2: fake the targets data		
		log.info("Entering the simulation mode.");
		for(int i = 0; i<10; i++){
			TargetEntity te = new TargetEntity(0, i, new byte[]{1,2,3,4,5,6,7,8}, 0, System.currentTimeMillis());
			te.setGps(Utility.fakeGPS());
			te.setGrade(new byte[]{0,0});
			te.setPresure(0.9);
			te.setVoltage(3.5);
			te.setTimestamp(new byte[]{0,0,0,0,0,0});
			TargetsCollection.INSTANCE.put(te);
		}
		//3: fake the group data
		GroupEntity ge = new GroupEntity(1, 1, "only1");
		for(int i=0; i<10; i++) ge.addTargets(i);
		GroupCollection.INSTANCE.put(ge);
		//4: set the trainee data
		TraineeEntity trainee = new TraineeEntity();
		trainee.setBattery(1.2);
		trainee.setGps(Utility.fakeGPS());
		trainee.setID(1);
		trainee.setLastUpdate(System.currentTimeMillis());
		trainee.setName("Jackson");
		trainee.setSN(new byte[]{1,2,3,4,5,6,7,8});
		trainee.setTimestamp(new byte[]{0,0,0,0,0,0});
		TraineeEntityCollection.INSTANCE.put(trainee);
		
		//5: make the trainee move!
		while(true){
			trainee.setGps(Utility.fakeGPS());
			trainee.setLastUpdate(System.currentTimeMillis());
			try {
				Thread.sleep(1000*30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Prepare the application to make it ready for training.</br>
	 * <ol> 
	 * <li>Initialize the targets network</li>
	 * <li>run the server module</li>  
	 * <li> Set the UI mode to let the user configure the application</li>
	 * </ol>
	 */
	public void prepare()
	{
		log.info("Enter preparing state ... ");
		//setup the targets network
		bWirelessInit = false;
		
		if(runMode == RUNMODE_ONLINE || runMode == RUNMODE_ONLINE_DEBUG){
			try {
				tm.connect2Router();
				tm.initResetNetwork();
			} catch (IOException | WrongFrameFormatException | SetNodeIDException e) {
				e.printStackTrace();
				log.severe("Cannot communicate with the router. Error message: " + e.getMessage());
				bWirelessInit = true;
			}
		}
		
		//start the HTTP server
		log.info("Starting the Http Server ... ");
		HttpServer http = new HttpServer(ConfDoc.INSTANCE.getServerPort());
		http.start();
		log.info("Server has been started.");
		//set the UI mode
		if(bWirelessInit) iMode = MODE_READY;
	}

	private void initDebugUI() {
		MainWin win = new MainWin("Debugging Window");
		win.pack();
		win.setVisible(true);
		win.setUIEventListener(this);
	}
	
	/**
	 * <p>Make the whole system in Running mode. In this mode the UI
	 * may send command to targets and the targets should constantly
	 * update their status. </p>
	 * 
	 * <p>This method will runs forever unless someone called the 
	 * <code>stopActing()</code> method form another thread.</p>
	 * 
	 * @see stopActing
	 */
	private void acting()
	{
		//Should check if the mode is MODE_READY
		//This method is only allowed to be executed in MODE_READY.
		if(iMode != MODE_READY) return;
		log.info("Entering real system running mode! ");
		//Entering running state
		iMode = MODE_ACTING;
		//start monitoring the command queue
		ActionQueueChecker checker = new ActionQueueChecker();
		checker.start();
		//Ask the targets-networking in action
		bRun = true;
		while(bRun){
			//update targets status
			tm.updateData();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		iMode = MODE_READY;
	}
	
	public void stopActing()
	{
		bRun = false;
	}

	public static void main(String[] args) {
		INSTANCE.setRunMode(TMApplication.RUNMODE_SIMULATE);
		
		INSTANCE.init();
		INSTANCE.prepare();

		INSTANCE.loop();
	}

	/**
	 * Let the application entering the endlessly loop operation.
	 * 
	 */
	public void loop() {
		if(runMode == RUNMODE_SIMULATE){
			simulate();
		}
		if(runMode == RUNMODE_ONLINE || runMode == RUNMODE_ONLINE_DEBUG){
			acting();
		}
	}

	public void setRunMode(int runmode) {
		runMode = runmode;
	}

	@Override
	public void processStandEvent(TargetEntity te) {
		if(te != null) tm.standTarget(te);
		else{
			for(TargetEntity t : TargetsCollection.INSTANCE.getTargetsCollection()){
				tm.standTarget(t);
			}
		}
	}

	@Override
	public void processSitEvent(TargetEntity te) {
		if(te != null) tm.sitTarget(te);
		else{
			for(TargetEntity t : TargetsCollection.INSTANCE.getTargetsCollection()){
				tm.sitTarget(t);
			}
		}
	}

	@Override
	public void processLightEvent(TargetEntity te) {
		if(te != null) tm.lightOnOff(te);
		else{
			for(TargetEntity t : TargetsCollection.INSTANCE.getTargetsCollection()){
				tm.lightOnOff(t);
			}
		}
	}

	/**
	 * Class used to check the action queue periodically.
	 * @author lxm Mar 24, 2017
	 *
	 */
	private class ActionQueueChecker extends Thread{

		@Override
		public synchronized void start() {
			while(true){
				ActionCommand ac = ActionQueue.INSTANCE.pop();
				if(ac != null){
					int[] ids = ac.getArrayOfTargets();
					for(int id : ids){
						TargetEntity te = TargetsCollection.INSTANCE.get(id);
						if(te != null){
							tm.standTarget(te);
						}
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
	}


	@Override
	public void processInitEvent(TargetEntity te) {
		try {
			tm.initResetNetwork();
		} catch (WrongFrameFormatException | SetNodeIDException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void processIDEvent(TargetEntity te) {
		if(te != null) tm.lightOnOff(te);
		else{
			for(TargetEntity t : TargetsCollection.INSTANCE.getTargetsCollection()){
				tm.setNodeID(t);
			}
		}
	}

	@Override
	public void processStatusEvent(TargetEntity te) {
		if(te != null) tm.lightOnOff(te);
		else{
			for(TargetEntity t : TargetsCollection.INSTANCE.getTargetsCollection()){
				tm.getNodeStatus(t);
			}
		}
	}
}
