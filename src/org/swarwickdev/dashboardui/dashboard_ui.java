package org.swarwickdev.dashboardui;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class dashboard_ui {

	protected static Shell shell;
	boolean startup = true;
	private static Connection con;
	static Group group;
	Group rbmGroup, ratingGroup, bdbrGroup, taskGroup, statusGroup;
	static final String[] EVENT_NAMES = { "None", "KeyDown", "KeyUp", "MouseDown", "MouseUp", "MouseMove", "MouseEnter",
			"MouseExit", "MouseDoubleClick", "Paint", "Move", "Resize", "Dispose", "Selection", "DefaultSelection",
			"FocusIn", "FocusOut", "Expand", "Collapse", "Iconify", "Deiconify", "Close", "Show", "Hide", "Modify",
			"Verify", "Activate", "Deactivate", "Help", "DragDetect", "Arm", "Traverse", "MouseHover", "HardKeyDown",
			"HardKeyUp", "MenuDetect", "SetData", "MouseWheel", };

	CLabel lblRefreshDat;
	CLabel lblConfigagent;
	CLabel lblTM;
	CLabel lblSID;
	CLabel lblBiCEP;
	CLabel lblBiCEPCnt;
	CLabel lblCEW;
	CLabel lblCEWCnt;
	CLabel lblDUMCnt;
	CLabel lblDUM;
	CLabel lblCDRPP;
	CLabel lblCDRPPCnt;
	CLabel lblSCEP;
	CLabel lblSCEPCnt;
	CLabel lblSCBM;
	CLabel lblSCBMCnt;
	CLabel lblGADate;
	CLabel lblWSMPDate;
	CLabel lblCMSPerf;
	CLabel lblCMSExtr;
	CLabel lblCMSLoad;
	CLabel lblRating;
	CLabel lblRatingCnt;
	CLabel lblRateStart;
	CLabel lblRateEnd;
	CLabel lblRateErrCnt;
	CLabel lblBDBRStatus;
	CLabel lblBDBRCnt;
	CLabel lblBDBRStart;
	CLabel lblBDBREnd;
	CLabel lblBDBRLog;
	CLabel lblBDBRErrCnt;
	CLabel lblBDBRExtrCnt;
	CLabel lblBDBRExtractingCnt;
	CLabel lblBDBRExtractedCnt;
	CLabel lblBDBRPending;
	CLabel lblBDBRExtrProgress;
	CLabel lblBDBRScpCnt;
	CLabel lblBDBRFilesCnt;
	CLabel lblBDBRDataCnt;
	CLabel lblBDBRFSentCnt;
	CLabel lblBDBRDSentCnt;
	CLabel lblBDBRTransferProgress;
	CLabel lblRefreshDatVal;
	CLabel lblConfigagentVal;
	CLabel lblTMVal;
	CLabel lblSIDVal;
	CLabel lblBiCEPVal;
	CLabel lblBiCEPCntVal;
	CLabel lblCEWVal;
	CLabel lblCEWCntVal;
	CLabel lblDUMCntVal;
	CLabel lblDUMVal;
	CLabel lblCDRPPVal;
	CLabel lblCDRPPCntVal;
	CLabel lblSCEPVal;
	CLabel lblSCEPCntVal;
	CLabel lblSCBMVal;
	CLabel lblSCBMCntVal;
	CLabel lblGADateVal;
	CLabel lblWSMPDateVal;
	CLabel lblCMSPerfVal;
	CLabel lblCMSExtrVal;
	CLabel lblCMSLoadVal;
	CLabel lblRatingVal;
	CLabel lblRatingCntVal;
	CLabel lblRateStartVal;
	CLabel lblRateEndVal;
	CLabel lblRateErrCntVal;

	CLabel lblJobWaitingVal;
	CLabel lblJobRatingVal;
	CLabel lblJobRejectedVal;
	CLabel lblJobHoldVal;
	CLabel lblJobDisabledVal;
	CLabel lblJobUnknownVal;
	CLabel lblJobWaitingCntVal;
	CLabel lblJobRatingCntVal;
	CLabel lblJobRejectedCntVal;
	CLabel lblJobHoldCntVal;
	CLabel lblJobDisabledCntVal;
	CLabel lblJobUnknownCntVal;

	CLabel lblBDBRStatusVal;
	CLabel lblBDBRCntVal;
	CLabel lblBDBRStartVal;
	CLabel lblBDBREndVal;
	CLabel lblBDBRLogVal;
	CLabel lblBDBRErrCntVal;
	CLabel lblBDBRExtrCntVal;
	CLabel lblBDBRExtractingCntVal;
	CLabel lblBDBRExtractedCntVal;
	CLabel lblBDBRPendingVal;
	CLabel lblBDBRScpCntVal;
	CLabel lblBDBRFilesCntVal;
	CLabel lblBDBRDataCntVal;
	CLabel lblBDBRFSentCntVal;
	CLabel lblBDBRDSentCntVal;
	CLabel lblStatus;
	static CLabel lblStatusVal;
	private ProgressBar extrProgressBar;
	private ProgressBar sendProgressBar;
	private static Menu menu;
	private static MenuItem mntmOptions;
	private static Menu menu_1;
	private static MenuItem mntmConnect;
	private static MenuItem mntmDisconnect;
	private static MenuItem mntmRefresh;
	private static MenuItem mntmExit;
//	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	static Display display;
	static boolean bConnected = false;
	String sUser;
	String sPasswd;
	String sPort;
	String sSID;
	static RefreshThread thread;
	long lRefreshTime = 20000;
	private List list;
	private Table taskConsole;
	String sLastRunDtm = "";
	String sBlank = "                           ";
	private CLabel lblCewQueueDepth;
	private CLabel lblCewQueueDepthVal;
	private CLabel lblBicepQueueDepth;
	private CLabel lblBicepQueueDepthVal;
	private CLabel lblSCEPQueueDepth;
	private CLabel lblSCEPQueueDepthVal;
	private CLabel lblSCBMQueueDepth;
	private CLabel lblSCBMQueueDepthVal;
	private CLabel lblSCEPErrs;
	private CLabel lblSCEPErrsVal;
	private CLabel lblSCBMErrs;
	private CLabel lblSCBMErrsVal;
	private CLabel lblBicepErrs;
	private CLabel lblBicepErrsVal;
	MenuItem mntmRuntask;
	TableCursor cursor;

	public dashboard_ui(Composite parent) {
		createRBMGroup();
		createRatingGroup();
		createBDBRGroup();
		createTaskGroup();
		createStatusGroup();

		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		mntmOptions = new MenuItem(menu, SWT.CASCADE);
		mntmOptions.setText("Options");

		menu_1 = new Menu(mntmOptions);
		mntmOptions.setMenu(menu_1);

		mntmConnect = new MenuItem(menu_1, SWT.PUSH);
		mntmConnect.addSelectionListener(new dashboard_ui.connectListener());
		mntmConnect.setText("Connect");

		mntmDisconnect = new MenuItem(menu_1, SWT.PUSH);
		mntmDisconnect.addSelectionListener(new dashboard_ui.disconnectListener());
		mntmDisconnect.setText("Disconnect");

		mntmRefresh = new MenuItem(menu_1, SWT.PUSH);
		mntmRefresh.addSelectionListener(new dashboard_ui.refreshListener());
		mntmRefresh.setText("Refresh");

		mntmRuntask = new MenuItem(menu_1, SWT.NONE);
		mntmRuntask.addSelectionListener(new dashboard_ui.runtaskListener());
		mntmRuntask.setText("Run Task");

		mntmExit = new MenuItem(menu_1, SWT.PUSH);
		mntmExit.addSelectionListener(new dashboard_ui.exitListener());
		mntmExit.setText("Exit");

		startup = false;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// display = new Display();
		display = Display.getDefault();
		shell = new Shell(display);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setLayout(new FillLayout());
		group = new Group(shell, SWT.NONE);
		GridLayout gl_group = new GridLayout(3, true);
		gl_group.verticalSpacing = 1;
		gl_group.marginWidth = 1;
		gl_group.marginHeight = 1;
		gl_group.horizontalSpacing = 1;
		group.setLayout(gl_group);

		dashboard_ui instance = new dashboard_ui(shell);
		shell.setText("Billing System Status");
		setShellSize(display, shell);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		if (bConnected == true) {
			try {
				if (thread.isRunning()) {
					thread.terminate();
					thread.interrupt();
					thread.join();
				}
				con.close();
				bConnected = false;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		instance.dispose();
	}

	/**
	 * Sets the size of the shell to it's "packed" size, unless that makes it bigger
	 * than the display, in which case set it to 9/10 of display size.
	 */
	static void setShellSize(Display display, Shell shell) {
		Rectangle bounds = display.getBounds();
		Point size = shell.computeSize(1900, 1000);
		// Point size = shell.computeSize(1600, 800);
		if (size.x > bounds.width)
			size.x = bounds.width;
		if (size.y > bounds.height)
			size.y = bounds.height;
		shell.setSize(size);
	}

	/**
	 * Disposes of all resources associated with a particular instance of the
	 * ControlExample.
	 */
	public void dispose() {
	}

	void createRBMGroup() {
		rbmGroup = new Group(group, SWT.NONE);
		GridLayout gl_rbmGroup = new GridLayout();
		gl_rbmGroup.verticalSpacing = 1;
		gl_rbmGroup.numColumns = 4;
		rbmGroup.setLayout(gl_rbmGroup);
		// gd_rbmGroup.heightHint = 417;
		rbmGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		rbmGroup.setText("RBM");

		lblConfigagent = new CLabel(rbmGroup, SWT.NONE);
		lblConfigagent.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConfigagentVal = new CLabel(rbmGroup, SWT.NONE);
		lblConfigagentVal.setText(sBlank);
		lblTM = new CLabel(rbmGroup, SWT.NONE);
		lblTM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTM.setText("Task Master : ");
		lblTMVal = new CLabel(rbmGroup, SWT.NONE);
		lblTMVal.setText(sBlank);
		lblSID = new CLabel(rbmGroup, SWT.NONE);
		lblSID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSID.setText("Oracle SID : ");
		lblSIDVal = new CLabel(rbmGroup, SWT.NONE);
		lblSIDVal.setText(sBlank);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		lblCEW = new CLabel(rbmGroup, SWT.NONE);
		lblCEW.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCEW.setText("CEW : ");
		lblCEWVal = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEP = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBiCEP.setText("BiCEP : ");
		lblBiCEPVal = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEPVal.setText(sBlank);
		lblCEWCnt = new CLabel(rbmGroup, SWT.NONE);
		lblCEWCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCEWCnt.setText("Process Count : ");
		lblCEWCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEPCnt = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEPCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBiCEPCnt.setText("Process Count : ");
		lblBiCEPCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblBiCEPCntVal.setText(sBlank);
		lblCDRPP = new CLabel(rbmGroup, SWT.NONE);
		lblCDRPP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCDRPP.setText("CDRPP : ");
		lblCDRPPVal = new CLabel(rbmGroup, SWT.NONE);
		lblDUM = new CLabel(rbmGroup, SWT.NONE);
		lblDUM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDUM.setText("DUM : ");
		lblDUMVal = new CLabel(rbmGroup, SWT.NONE);
		lblDUMVal.setText(sBlank);
		lblCDRPPCnt = new CLabel(rbmGroup, SWT.NONE);
		lblCDRPPCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCDRPPCnt.setText("Process Count : ");
		lblCDRPPCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblDUMCnt = new CLabel(rbmGroup, SWT.NONE);
		lblDUMCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDUMCnt.setText("Process Count : ");
		lblDUMCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblDUMCntVal.setText(sBlank);
		lblSCBM = new CLabel(rbmGroup, SWT.NONE);
		lblSCBM.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCBM.setText("SCBM : ");
		lblSCBMVal = new CLabel(rbmGroup, SWT.NONE);
		lblSCEP = new CLabel(rbmGroup, SWT.NONE);
		lblSCEP.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCEP.setText("SCEP : ");
		lblSCEPVal = new CLabel(rbmGroup, SWT.NONE);
		lblSCEPVal.setText(sBlank);
		lblSCBMCnt = new CLabel(rbmGroup, SWT.NONE);
		lblSCBMCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCBMCnt.setText("Process Count : ");
		lblSCBMCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblSCEPCnt = new CLabel(rbmGroup, SWT.NONE);
		lblSCEPCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCEPCnt.setText("Process Count : ");
		lblSCEPCntVal = new CLabel(rbmGroup, SWT.NONE);
		lblSCEPCntVal.setText(sBlank);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		lblGADate = new CLabel(rbmGroup, SWT.NONE);
		lblGADate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGADate.setText("Adapter Tidemark : ");
		lblGADateVal = new CLabel(rbmGroup, SWT.NONE);
		lblGADateVal.setText(sBlank);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		lblWSMPDate = new CLabel(rbmGroup, SWT.NONE);
		lblWSMPDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblWSMPDate.setText("WSMP Tidemark : ");
		lblWSMPDateVal = new CLabel(rbmGroup, SWT.NONE);
		lblWSMPDateVal.setText(sBlank);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		new Label(rbmGroup, SWT.NONE);
		lblCMSPerf = new CLabel(rbmGroup, SWT.NONE);
		lblCMSPerf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCMSPerf.setText("CMS Performer : ");

		lblConfigagent.setText("Config Agent : ");
		lblCEWVal.setText(sBlank);
		lblCEWCntVal.setText(sBlank);
		lblCDRPPVal.setText(sBlank);
		lblCDRPPCntVal.setText(sBlank);
		lblSCBMVal.setText(sBlank);
		lblSCBMCntVal.setText(sBlank);
		lblCMSPerfVal = new CLabel(rbmGroup, SWT.NONE);
		lblCMSPerfVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
		lblCMSPerfVal.setText(sBlank + sBlank);
		lblCMSExtr = new CLabel(rbmGroup, SWT.NONE);
		lblCMSExtr.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCMSExtr.setText("CMS Extractor : ");
		// new Label(rbmGroup, SWT.NONE);
		// new Label(rbmGroup, SWT.NONE);
		lblCMSExtrVal = new CLabel(rbmGroup, SWT.NONE);
		lblCMSExtrVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
		lblCMSExtrVal.setText(sBlank + sBlank);
		lblCMSLoad = new CLabel(rbmGroup, SWT.NONE);
		lblCMSLoad.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCMSLoad.setText("CMS Loader : ");
		// new Label(rbmGroup, SWT.NONE);
		// new Label(rbmGroup, SWT.NONE);
		lblCMSLoadVal = new CLabel(rbmGroup, SWT.NONE);
		lblCMSLoadVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
		lblCMSLoadVal.setText(sBlank + sBlank);

	}

	void createRatingGroup() {
		ratingGroup = new Group(group, SWT.NONE);
		GridLayout gl_ratingGroup = new GridLayout();
		gl_ratingGroup.numColumns = 4;
		gl_ratingGroup.verticalSpacing = 1;
		ratingGroup.setLayout(gl_ratingGroup);
		// gd_ratingGroup.heightHint = 237;
		ratingGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		ratingGroup.setText("Rating");

		lblRating = new CLabel(ratingGroup, SWT.NONE);
		lblRating.setText("Status : ");
		lblRating.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRating.setAlignment(SWT.RIGHT);
		lblRatingVal = new CLabel(ratingGroup, SWT.NONE);
		lblRatingVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));

		lblRatingVal.setText(sBlank);
		lblRatingCnt = new CLabel(ratingGroup, SWT.NONE);
		lblRatingCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRatingCnt.setAlignment(SWT.RIGHT);
		lblRatingCnt.setText("Process Count : ");
		lblRatingCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblRatingCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblRatingCntVal.setText(sBlank);
		lblRateStart = new CLabel(ratingGroup, SWT.NONE);
		lblRateStart.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRateStart.setAlignment(SWT.RIGHT);
		lblRateStart.setText("Start Dtm : ");
		lblRateStartVal = new CLabel(ratingGroup, SWT.NONE);
		lblRateStartVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblRateStartVal.setText(sBlank + sBlank);
		lblRateEnd = new CLabel(ratingGroup, SWT.NONE);
		lblRateEnd.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRateEnd.setAlignment(SWT.RIGHT);
		lblRateEnd.setText("End Dtm : ");
		lblRateEndVal = new CLabel(ratingGroup, SWT.NONE);
		lblRateEndVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblRateEndVal.setText(sBlank + sBlank);
		lblRateErrCnt = new CLabel(ratingGroup, SWT.NONE);
		lblRateErrCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRateErrCnt.setAlignment(SWT.RIGHT);
		lblRateErrCnt.setText("Errors : ");
		lblRateErrCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblRateErrCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblRateErrCntVal.setText(sBlank);

		lblJobWaitingVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobWaitingVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobWaitingVal.setAlignment(SWT.RIGHT);
		lblJobWaitingVal.setText("Jobs Waiting : ");
		lblJobWaitingCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobWaitingCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobWaitingCntVal.setText(sBlank);
		lblJobRatingVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobRatingVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobRatingVal.setAlignment(SWT.RIGHT);
		lblJobRatingVal.setText("Jobs Rating : ");
		lblJobRatingCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobRatingCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobRatingCntVal.setText(sBlank);
		lblJobRejectedVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobRejectedVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobRejectedVal.setAlignment(SWT.RIGHT);
		lblJobRejectedVal.setText("Jobs Rejected : ");
		lblJobRejectedCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobRejectedCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobRejectedCntVal.setText(sBlank);

		lblJobHoldVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobHoldVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobHoldVal.setAlignment(SWT.RIGHT);
		lblJobHoldVal.setText("Jobs On Hold : ");
		lblJobHoldCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobHoldCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobHoldCntVal.setText(sBlank);

		lblJobDisabledVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobDisabledVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobDisabledVal.setAlignment(SWT.RIGHT);
		lblJobDisabledVal.setText("Jobs Disabled : ");
		lblJobDisabledCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobDisabledCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobDisabledCntVal.setText(sBlank);

		lblJobUnknownVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobUnknownVal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJobUnknownVal.setAlignment(SWT.RIGHT);
		lblJobUnknownVal.setText("Jobs Unknown : ");
		lblJobUnknownCntVal = new CLabel(ratingGroup, SWT.NONE);
		lblJobUnknownCntVal.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblJobUnknownCntVal.setText(sBlank);
		new Label(ratingGroup, SWT.NONE);
		new Label(ratingGroup, SWT.NONE);
		new Label(ratingGroup, SWT.NONE);
		new Label(ratingGroup, SWT.NONE);
		lblCewQueueDepth = new CLabel(ratingGroup, SWT.NONE);
		lblCewQueueDepth.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCewQueueDepth.setText("CEW Queue Depth : ");
		// new Label(ratingGroup, SWT.NONE);
		// new Label(ratingGroup, SWT.NONE);
		lblCewQueueDepthVal = new CLabel(ratingGroup, SWT.NONE);
		lblCewQueueDepthVal.setText(sBlank);
		new Label(ratingGroup, SWT.NONE);
		new Label(ratingGroup, SWT.NONE);

		lblBicepQueueDepth = new CLabel(ratingGroup, SWT.NONE);
		lblBicepQueueDepth.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBicepQueueDepth.setText("BiCEP Queue Depth : ");
		lblBicepQueueDepthVal = new CLabel(ratingGroup, SWT.NONE);
		lblBicepQueueDepthVal.setText(sBlank);

		lblBicepErrs = new CLabel(ratingGroup, SWT.NONE);
		lblBicepErrs.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBicepErrs.setText("Errors : ");

		lblBicepErrsVal = new CLabel(ratingGroup, SWT.NONE);
		lblBicepErrsVal.setText(sBlank);

		lblSCEPQueueDepth = new CLabel(ratingGroup, SWT.NONE);
		lblSCEPQueueDepth.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCEPQueueDepth.setText("SCEP Queue Depth : ");
		lblSCEPQueueDepthVal = new CLabel(ratingGroup, SWT.NONE);
		lblSCEPQueueDepthVal.setText(sBlank);

		lblSCEPErrs = new CLabel(ratingGroup, SWT.NONE);
		lblSCEPErrs.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCEPErrs.setText("Errors : ");

		lblSCEPErrsVal = new CLabel(ratingGroup, SWT.NONE);
		lblSCEPErrsVal.setText(sBlank);

		lblSCBMQueueDepth = new CLabel(ratingGroup, SWT.NONE);
		lblSCBMQueueDepth.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCBMQueueDepth.setText("SCBM Queue Depth : ");
		lblSCBMQueueDepthVal = new CLabel(ratingGroup, SWT.NONE);
		lblSCBMQueueDepthVal.setText(sBlank);

		lblSCBMErrs = new CLabel(ratingGroup, SWT.NONE);
		lblSCBMErrs.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSCBMErrs.setText("Errors : ");

		lblSCBMErrsVal = new CLabel(ratingGroup, SWT.NONE);
		lblSCBMErrsVal.setText(sBlank);
		new Label(ratingGroup, SWT.NONE);
		new Label(ratingGroup, SWT.NONE);

	}

	void createBDBRGroup() {
		bdbrGroup = new Group(group, SWT.NONE);
		GridLayout gl_bdbrGroup = new GridLayout();
		gl_bdbrGroup.numColumns = 2;
		gl_bdbrGroup.verticalSpacing = 1;
		bdbrGroup.setLayout(gl_bdbrGroup);
		// gd_bdbrGroup.heightHint = 473;
		bdbrGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		bdbrGroup.setText("BDBR");

		lblBDBRStatus = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRStatus.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRStatus.setAlignment(SWT.RIGHT);
		lblBDBRStatusVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRCnt.setAlignment(SWT.RIGHT);
		lblBDBRCnt.setText("Process Count : ");
		lblBDBRCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRStart = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRStart.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRStart.setAlignment(SWT.RIGHT);
		lblBDBRStartVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBREnd = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBREnd.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBREnd.setAlignment(SWT.RIGHT);
		lblBDBREnd.setText("End Dtm : ");
		lblBDBREndVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRLog = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRLog.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRLog.setAlignment(SWT.RIGHT);
		lblBDBRLogVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRLogVal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblBDBRErrCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRErrCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRErrCnt.setAlignment(SWT.RIGHT);
		lblBDBRErrCnt.setText("Errors : ");
		lblBDBRErrCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtrCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtrCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRExtrCnt.setAlignment(SWT.RIGHT);
		lblBDBRExtrCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtractingCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtractingCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRExtractingCnt.setAlignment(SWT.RIGHT);
		lblBDBRExtractingCnt.setText("Extracting : ");
		lblBDBRExtractingCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtractedCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtractedCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRExtractedCnt.setAlignment(SWT.RIGHT);
		lblBDBRExtractedCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRPending = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRPending.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRPending.setAlignment(SWT.RIGHT);
		lblBDBRPending.setText("Pending : ");
		lblBDBRPendingVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtrProgress = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRExtrProgress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRExtrProgress.setAlignment(SWT.RIGHT);

		extrProgressBar = new ProgressBar(bdbrGroup, SWT.NONE);
		extrProgressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblBDBRScpCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRScpCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRScpCnt.setAlignment(SWT.RIGHT);
		lblBDBRScpCnt.setText("SCP Threads : ");
		lblBDBRScpCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRFilesCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRFilesCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRFilesCnt.setAlignment(SWT.RIGHT);
		lblBDBRFilesCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRDataCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRDataCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRDataCnt.setAlignment(SWT.RIGHT);
		lblBDBRDataCnt.setText("Data to SCP : ");
		lblBDBRDataCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRFSentCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRFSentCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRFSentCnt.setAlignment(SWT.RIGHT);
		lblBDBRFSentCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRDSentCnt = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRDSentCnt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRDSentCnt.setAlignment(SWT.RIGHT);
		lblBDBRDSentCnt.setText("Data Sent : ");
		lblBDBRDSentCntVal = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRTransferProgress = new CLabel(bdbrGroup, SWT.NONE);
		lblBDBRTransferProgress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBDBRTransferProgress.setAlignment(SWT.RIGHT);

		lblBDBRStatus.setText("Status : ");
		lblBDBRStart.setText("Start Dtm : ");
		lblBDBRLog.setText("Log File : ");
		lblBDBRExtrCnt.setText("Extractors : ");
		lblBDBRExtractedCnt.setText("Extracted : ");
		lblBDBRExtrProgress.setText("Progress : ");
		lblBDBRFilesCnt.setText("Files to SCP : ");
		lblBDBRFSentCnt.setText("Files Sent : ");
		lblBDBRTransferProgress.setText("Progress : ");

		sendProgressBar = new ProgressBar(bdbrGroup, SWT.NONE);
		sendProgressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		lblBDBRStatusVal.setText(sBlank);
		lblBDBRCntVal.setText(sBlank);
		lblBDBRStartVal.setText(sBlank + sBlank);
		lblBDBREndVal.setText(sBlank + sBlank);
		lblBDBRLogVal.setText(sBlank + sBlank);
		lblBDBRErrCntVal.setText(sBlank);
		lblBDBRExtrCntVal.setText(sBlank);
		lblBDBRExtractingCntVal.setText(sBlank);
		lblBDBRExtractedCntVal.setText(sBlank);
		lblBDBRPendingVal.setText(sBlank);
		lblBDBRScpCntVal.setText(sBlank);
		lblBDBRFilesCntVal.setText(sBlank);
		lblBDBRDataCntVal.setText(sBlank);
		lblBDBRFSentCntVal.setText(sBlank);
		lblBDBRDSentCntVal.setText(sBlank);
	}

	void createTaskGroup() {
		taskGroup = new Group(group, SWT.NONE);
		GridLayout gl_taskGroup = new GridLayout(1, true);
		gl_taskGroup.marginHeight = 1;
		gl_taskGroup.marginWidth = 1;
		taskGroup.setLayout(gl_taskGroup);
		taskGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		taskGroup.setText("Task Status");

		taskConsole = new Table(taskGroup, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL);
		taskConsole.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		taskConsole.setHeaderVisible(false);
		taskConsole.setLinesVisible(false);

		cursor = new TableCursor(taskConsole, SWT.NONE);

		Menu menu_2 = new Menu(taskConsole);
		taskConsole.setMenu(menu_2);

		// MenuItem mntmCopy = new MenuItem(menu_2, SWT.NONE);
		// mntmCopy.addSelectionListener(new
		// Dashboard_UI.copytaskconsoleListener());
		// mntmCopy.setText("Copy");

		MenuItem mntmClear = new MenuItem(menu_2, SWT.NONE);
		mntmClear.addSelectionListener(new dashboard_ui.cleartaskconsoleListener());
		mntmClear.setText("Clear");
	}

	void createStatusGroup() {
		statusGroup = new Group(group, SWT.NONE);
		statusGroup.setText("Status");
		GridLayout gl_statusGroup = new GridLayout();
		gl_statusGroup.horizontalSpacing = 1;
		gl_statusGroup.marginWidth = 0;
		gl_statusGroup.marginHeight = 0;
		gl_statusGroup.numColumns = 5;
		gl_statusGroup.verticalSpacing = 1;
		statusGroup.setLayout(gl_statusGroup);
		GridData gd_statusGroup = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd_statusGroup.horizontalSpan = 3;
		statusGroup.setLayoutData(gd_statusGroup);

		lblStatus = new CLabel(statusGroup, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStatus.setAlignment(SWT.RIGHT);
		lblStatusVal = new CLabel(statusGroup, SWT.NONE);
		GridData gd_lblStatusVal = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblStatusVal.widthHint = 198;
		lblStatusVal.setLayoutData(gd_lblStatusVal);

		lblStatus.setText("DB Status : ");
		lblStatusVal.setText("Disconnected");
		new Label(statusGroup, SWT.NONE);

		lblRefreshDat = new CLabel(statusGroup, SWT.NONE);
		lblRefreshDat.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRefreshDat.setAlignment(SWT.RIGHT);

		lblRefreshDat.setText("Last Refreshed : ");
		lblRefreshDatVal = new CLabel(statusGroup, SWT.NONE);
		GridData gd_lblRefreshDatVal = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRefreshDatVal.widthHint = 158;
		lblRefreshDatVal.setLayoutData(gd_lblRefreshDatVal);
		java.util.Date date = new java.util.Date();
		String strDateFormat = "dd/MM/yy HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(date);
		lblRefreshDatVal.setText(formattedDate);
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);

	}

	int getProcessCountPL(String sProcessName) {
		int iCount = 0;
		String sSQL;
		Statement st = null;
		ResultSet rs = null;

		sSQL = new String(
				"select count(1) from PROCESSINSTANCELOG where process_def_id in (select process_def_id from processdefinition where image_name = '");
		sSQL += sProcessName;
		sSQL += "') and end_dtm is null";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				iCount = rs.getInt(1);
			}
			st.close();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}

		return iCount;
	}

	int getProcessCountSess(String sProcessName) {
		int iCount = 0;
		String sSQL;
		Statement st = null;
		ResultSet rs = null;

		sSQL = new String("select count(1) from v$session where program like '");
		sSQL += sProcessName;
		sSQL += "%'";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				iCount = rs.getInt(1);
			}
			st.close();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}

		return iCount;
	}

	void refreshData() {
		int iCount = 0;
		String sSQL;
		Statement st = null;
		ResultSet rs = null;

		iCount = getProcessCountSess("DConfigAgent");
		if (iCount == 0) {
			lblConfigagentVal.setText("NOT RUNNING");
			lblConfigagentVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblConfigagentVal.setText("RUNNING");
			lblConfigagentVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}

		iCount = getProcessCountSess("TM");
		if (iCount == 0) {
			lblTMVal.setText("NOT RUNNING");
			lblTMVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblTMVal.setText("RUNNING");
			lblTMVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}

		iCount = getProcessCountPL("CDRPP");
		if (iCount == 0) {
			lblCDRPPVal.setText("NOT RUNNING");
			lblCDRPPVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblCDRPPVal.setText("RUNNING");
			lblCDRPPVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblCDRPPCntVal.setText(Integer.toString(iCount));

		iCount = getProcessCountPL("CEW");
		if (iCount == 0) {
			lblCEWVal.setText("NOT RUNNING");
			lblCEWVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblCEWVal.setText("RUNNING");
			lblCEWVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblCEWCntVal.setText(Integer.toString(iCount));

		iCount = getProcessCountPL("BiCEP");
		if (iCount == 0) {
			lblBiCEPVal.setText("NOT RUNNING");
			lblBiCEPVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblBiCEPVal.setText("RUNNING");
			lblBiCEPVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblBiCEPCntVal.setText(Integer.toString(iCount));

		iCount = getProcessCountPL("DUM");
		if (iCount == 0) {
			lblDUMVal.setText("NOT RUNNING");
			lblDUMVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblDUMVal.setText("RUNNING");
			lblDUMVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblDUMCntVal.setText(Integer.toString(iCount));

		iCount = getProcessCountPL("SCEP");
		if (iCount == 0) {
			lblSCEPVal.setText("NOT RUNNING");
			lblSCEPVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblSCEPVal.setText("RUNNING");
			lblSCEPVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblSCEPCntVal.setText(Integer.toString(iCount));

		iCount = getProcessCountPL("SCBM");
		if (iCount == 0) {
			lblSCBMVal.setText("NOT RUNNING");
			lblSCBMVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblSCBMVal.setText("RUNNING");
			lblSCBMVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}
		lblSCBMCntVal.setText(Integer.toString(iCount));

		try {
			st = con.createStatement();
			sSQL = "SELECT system, to_char(trunc((sysdate-min(TIDEMARK_DTM))*24*60)) from geneva_adapter.O2TIDEMARKBILLING group by SYSTEM";
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				if (rs.getString(1).equals("WSMP")) {
					lblWSMPDateVal.setText(rs.getString(2));
					if (rs.getInt(2) > 15)
						lblWSMPDateVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
					else
						lblWSMPDateVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));

				} else {
					lblGADateVal.setText(rs.getString(2));
					if (rs.getInt(2) > 15)
						lblGADateVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
					else
						lblGADateVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
				}
			}
			st.close();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}

		iCount = getProcessCountPL("o2_treatment_action_performer");
		if (iCount == 0) {
			lblCMSPerfVal.setText("NOT RUNNING");
			lblCMSPerfVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblCMSPerfVal.setText("RUNNING");
			lblCMSPerfVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}

		iCount = getProcessCountPL("o2_action_loader");
		if (iCount == 0) {
			lblCMSLoadVal.setText("NOT RUNNING");
			lblCMSLoadVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblCMSLoadVal.setText("RUNNING");
			lblCMSLoadVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}

		iCount = getProcessCountPL("o2_cms_extractor");
		if (iCount == 0) {
			lblCMSExtrVal.setText("NOT RUNNING");
			lblCMSExtrVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
		} else {
			lblCMSExtrVal.setText("RUNNING");
			lblCMSExtrVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
		}

		iCount = getProcessCountPL("RATE");
		if (iCount == 0) {
			lblRatingVal.setText("NOT RUNNING");

			int iErrors = 0;
			String sStart = "";
			String sEnd = "";
			try {
				st = con.createStatement();
				sSQL = "SELECT nvl(to_char(p.start_dtm,'YYYY-MM-DD HH24:MI:SS'),''), nvl(to_char(p.end_dtm,'YYYY-MM-DD HH24:MI:SS'),''), p.total_errors FROM PROCESSLOG p WHERE p.PROCESS_DEF_ID IN (SELECT PROCESS_DEF_ID FROM PROCESSDEFINITION WHERE IMAGE_NAME = 'RATE') and p.end_dtm is not null order by 1 desc";
				rs = st.executeQuery(sSQL);
				while (rs.next()) {
					sStart = rs.getString(1);
					sEnd = rs.getString(2);
					iErrors = rs.getInt(3);
					break;
				}
				st.close();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage(e3.getMessage());
				messageBox.open();
			}

			lblRateStartVal.setText(sStart);
			lblRateEndVal.setText(sEnd);
			lblRateErrCntVal.setText(Integer.toString(iErrors));

			if (iErrors != 0) {
				lblRatingVal.setForeground(display.getSystemColor(SWT.COLOR_YELLOW));
				lblRateErrCntVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
			} else {
				lblRatingVal.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				lblRateErrCntVal.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			}
		} else {
			lblRatingVal.setText("RUNNING");
			lblRatingVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));

			int iErrors = 0;
			String sStart = "";
			String sEnd = "";
			try {
				st = con.createStatement();
				sSQL = "SELECT to_char(min(p.start_dtm),'YYYY-MM-DD HH24:MI:SS'), sum(p.total_errors) FROM PROCESSLOG p WHERE p.PROCESS_DEF_ID IN (SELECT PROCESS_DEF_ID FROM PROCESSDEFINITION WHERE IMAGE_NAME = 'RATE') and p.end_dtm is null";
				rs = st.executeQuery(sSQL);
				while (rs.next()) {
					sStart = rs.getString(1);
					iErrors = rs.getInt(2);
					break;
				}
				st.close();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage(e3.getMessage());
				messageBox.open();
			}

			lblRateStartVal.setText(sStart);
			lblRateEndVal.setText(sEnd);
			lblRateErrCntVal.setText(Integer.toString(iErrors));

			if (iErrors != 0) {
				lblRatingVal.setForeground(display.getSystemColor(SWT.COLOR_YELLOW));
				lblRateErrCntVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
			} else {
				lblRatingVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
				lblRateErrCntVal.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			}

		}
		lblRatingCntVal.setText(Integer.toString(iCount));

		getJobStats();
		getTMStatus();
		getBDBRStatus();

		java.util.Date date = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		lblRefreshDatVal.setText(formattedDate);
	}

	void getJobStats() {
		Statement st;
		String sSQL = "";
		ResultSet rs;
		try {

			lblJobWaitingCntVal.setText("0");
			lblJobRatingCntVal.setText("0");
			lblJobRejectedCntVal.setText("0");
			lblJobHoldCntVal.setText("0");
			lblJobDisabledCntVal.setText("0");
			lblJobUnknownCntVal.setText("0");

			lblCewQueueDepthVal.setText("0");
			lblBicepQueueDepthVal.setText("0");
			lblSCEPQueueDepthVal.setText("0");
			lblSCBMQueueDepthVal.setText("0");
			lblBicepErrsVal.setText("0");
			lblSCEPErrsVal.setText("0");
			lblSCBMErrsVal.setText("0");

			st = con.createStatement();
			sSQL = "select DECODE(TO_CHAR(j.JOB_STATUS), '2', 'Waiting', '3', 'Rating', '5', 'Rejected', '88', 'On hold', '98', 'Disabled', '99', 'Disabled', 'Unknown'), count(*) from job j, jobhasfile jhf, jobtype jt where j.JOB_TYPE_ID IN (1,23,24,34) and j.JOB_STATUS not in (4,7,8) and jhf.JOB_ID = j.JOB_ID and jt.job_type_id = j.job_type_id group by DECODE(TO_CHAR(j.JOB_STATUS), '2', 'Waiting', '3', 'Rating', '5', 'Rejected', '88', 'On hold', '98', 'Disabled', '99', 'Disabled', 'Unknown')";
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				String sState = rs.getString(1);
				if (sState.equals("Waiting"))
					lblJobWaitingCntVal.setText(rs.getString(2));
				else if (sState.equals("Rating"))
					lblJobRatingCntVal.setText(rs.getString(2));
				else if (sState.equals("Rejected"))
					lblJobRejectedCntVal.setText(rs.getString(2));
				else if (sState.equals("On hold"))
					lblJobHoldCntVal.setText(rs.getString(2));
				else if (sState.equals("Disabled"))
					lblJobDisabledCntVal.setText(rs.getString(2));
				else if (sState.equals("Unknown"))
					lblJobUnknownCntVal.setText(rs.getString(2));

			}
			st.close();

			st = con.createStatement();
			sSQL = "select consumer_name, count(*) from aq$costedeventqueuetail where msg_state = 'READY' group by consumer_name ";
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				String sConsumer = rs.getString(1);
				if (sConsumer.equals("CEW"))
					lblCewQueueDepthVal.setText(rs.getString(2));
				else if (sConsumer.equals("BICEP"))
					lblBicepQueueDepthVal.setText(rs.getString(2));
			}
			st.close();

			st = con.createStatement();
			sSQL = "select 'SCEP', count(*) from INTERFACESC.aq$o2spendcapevent_qtab where msg_state = 'READY' group by 'SCEP' "
					+ "union all "
					+ "select 'SCBM', count(*) from INTERFACESC.aq$o2spendcapbarcheck_qtab where msg_state = 'READY' group by 'SCBM'";
			try {
				rs = st.executeQuery(sSQL);
				while (rs.next()) {
					String sConsumer = rs.getString(1);
					if (sConsumer.equals("SCEP"))
						lblSCEPQueueDepthVal.setText(rs.getString(2));
					else if (sConsumer.equals("SCBM"))
						lblSCBMQueueDepthVal.setText(rs.getString(2));

				}
			} catch (SQLException e3) {
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage(e3.getMessage());
				messageBox.open();
			}
			st.close();

			st = con.createStatement();
			sSQL = "select 'BICEP', count(*) from INTERFACECDRPP.BICEP_ERROR_TAB group by 'BICEP' ";
			rs = st.executeQuery(sSQL);
			while (rs.next()) {
				String sConsumer = rs.getString(1);
				if (sConsumer.equals("BICEP"))
					lblBicepErrsVal.setText(rs.getString(2));
			}
			st.close();

			try {
				st = con.createStatement();
				sSQL = "select 'SCEP', count(*) from INTERFACESC.SCEP_ERROR_TAB group by 'SCEP' " + "union all "
						+ "select 'SCBM', count(*) from INTERFACESC.SCBM_ERROR_TAB group by 'SCBM'";
				rs = st.executeQuery(sSQL);
				while (rs.next()) {
					String sConsumer = rs.getString(1);
					if (sConsumer.equals("SCEP"))
						lblSCEPErrsVal.setText(rs.getString(2));
					else if (sConsumer.equals("SCBM"))
						lblSCBMErrsVal.setText(rs.getString(2));

				}

			} catch (SQLException e3) {
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage(e3.getMessage());
				messageBox.open();
			}
			st.close();

			if (lblBicepErrsVal.getText().equals("0")) {
				lblBicepErrsVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
			} else {
				lblBicepErrsVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
			}
			if (lblSCBMErrsVal.getText().equals("0")) {
				lblSCBMErrsVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
			} else {
				lblSCBMErrsVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
			}
			if (lblSCEPErrsVal.getText().equals("0")) {
				lblSCEPErrsVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
			} else {
				lblSCEPErrsVal.setForeground(display.getSystemColor(SWT.COLOR_RED));
			}

			st.close();
		} catch (SQLException e3) {
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}
	}

	void getTMStatus() {
		PreparedStatement st;
		String sSQL = "";
		ResultSet rs;
		try {
			// taskConsole.removeAll();
			if (sLastRunDtm.equals("")) {
				java.util.Date date = new java.util.Date();
				date = new java.util.Date(date.getTime() + ((1 * (3600 * 1000) * -1)));
				DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
				sLastRunDtm = dateFormat.format(date);
			}

			sSQL = "select to_char(TSTMP,'HH24:mi:ss YYYY-MM-DD') AS LOG_TSTMP, DESCRIPTION, to_char(TSTMP,'DDMMYYHH24MISS') from ( "
					+ "SELECT pl.end_dtm AS TSTMP, CASE WHEN pl.total_errors > 0 THEN  ' PP-FAILURE: ' WHEN pl.processes_failed > 0 THEN ' PP-FAILURE: ' ELSE '      PP-OK: ' END || pp.process_plan_name || ' (of (' || pt.task_name || ') completed. (' || pl.processes_failed || ' failed processes / ' || pl.total_errors || ' errors)' AS DESCRIPTION "
					+ "FROM       processlog pl, processplan pp, task pt, tasklog pr "
					+ "WHERE      (((pl.processes_failed + pl.total_errors > 0) AND pl.end_dtm > to_date(?,'DDMMYYHH24MISS')) "
					+ "OR       (      (pl.processes_failed + pl.total_errors >= 0) "
					+ "AND pl.end_dtm > to_date(?,'DDMMYYHH24MISS'))) "
					+ "AND     pp.process_def_id = pl.process_def_id "
					+ "AND     pp.start_dat = (SELECT max(pp3.start_dat) FROM " + "processplan pp3 "
					+ "WHERE   pp3.plan_number = pl.plan_number " + "AND     pp3.process_def_id = pl.process_def_id) "
					+ "AND     pp.plan_number = pl.plan_number " + "AND     pr.task_id = pt.task_id "
					+ "AND     pl.task_instance_id = pr.task_instance_id " + "union all "
					+ "SELECT tl.end_dtm AS TSTMP, CASE WHEN tl.total_errors > 0 THEN  ' TA-FAILURE: ' ELSE '      TA-OK: ' END || ta.task_name || ' completed. (' || tl.total_errors || ' errors)'AS DESCRIPTION "
					+ "FROM task ta, " + "tasklog tl " + "WHERE   tl.task_id = ta.task_id "
					+ "AND     (((tl.end_dtm > to_date(?,'DDMMYYHH24MISS')) AND tl.total_errors > 0) "
					+ "OR      ((tl.end_dtm > to_date(?,'DDMMYYHH24MISS')) AND tl.total_errors >= 0))) "
					+ "ORDER BY TSTMP asc";

			st = con.prepareStatement(sSQL);
			st.setString(1, sLastRunDtm);
			st.setString(2, sLastRunDtm);
			st.setString(3, sLastRunDtm);
			st.setString(4, sLastRunDtm);
			rs = st.executeQuery();
			int iCount = 0;
			while (rs.next()) {
				TableItem item = new TableItem(taskConsole, SWT.NONE, 0);
				item.setText(rs.getString(1) + rs.getString(2));
				if (rs.getString(2).contains("FAILURE")) {
					item.setForeground(display.getSystemColor(SWT.COLOR_RED));
				} else {
					item.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				}

				sLastRunDtm = rs.getString(3);

				iCount++;
			}

			st.close();
			int iRows = taskConsole.getItemCount();
			if (iRows > 1000) {
				for (int iIndex = (iRows - 1); iIndex > 1000; iIndex--) {
					taskConsole.remove(iIndex);
				}
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}
	}

	void getBDBRStatus() {
		PreparedStatement st;
		String sSQL = "";
		ResultSet rs;
		int iCount = 0;
		String sLogFile = "";
		int iRunId = 0;

		try {

			/*sSQL = "select string_value || '/' from gparams where name = 'SYSlogFileRootDir'";
			st = con.prepareStatement(sSQL);
			rs = st.executeQuery();
			while (rs.next()) {
				sLogFile = rs.getString(1);
			}
			st.close();
*/
			sSQL = "select process_instance_id || '.LOG' as logname, to_char(start_dtm,'YYYY-MM-DD HH24:MI:SS'), to_char(end_dtm,'YYYY-MM-DD HH24:MI:SS') from processinstancelog where process_def_id in (select process_def_id from processdefinition where image_name = 'bdbr.pl') order by start_dtm desc";
			st = con.prepareStatement(sSQL);
			rs = st.executeQuery();
			while (rs.next()) {
				lblBDBRLogVal.setText(sLogFile + rs.getString(1));
				lblBDBRStartVal.setText(rs.getString(2));
				lblBDBREndVal.setText(rs.getString(3));
				break;
			}
			st.close();

			if (!lblBDBREndVal.getText().equals("")) {
				lblBDBRStatusVal.setText("NOT RUNNING");
				lblBDBRStatusVal.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				lblBDBRCntVal.setText("0");
			} else {
				lblBDBRStatusVal.setText("RUNNING");
				lblBDBRStatusVal.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
				lblBDBRCntVal.setText("1");
			}

			sSQL = "SELECT last_number - 1  FROM user_sequences WHERE sequence_name = 'BDBRRUNIDSEQ'";
			st = con.prepareStatement(sSQL);
			rs = st.executeQuery();
			while (rs.next()) {
				iRunId = rs.getInt(1);
			}
			st.close();

			lblBDBRErrCntVal.setText("0");
			lblBDBRExtrCntVal.setText("0");
			lblBDBRScpCntVal.setText("0");
			lblBDBRPendingVal.setText("0");
			lblBDBRExtractingCntVal.setText("0");
			lblBDBRExtractedCntVal.setText("0");
			lblBDBRFilesCntVal.setText("0");
			lblBDBRDataCntVal.setText("0 GB");
			lblBDBRFSentCntVal.setText("0");
			lblBDBRDSentCntVal.setText("0 GB");

			sSQL = "select status, count(*) from BDBR_EXTRACT_SET where run_id = ? group by status";
			st = con.prepareStatement(sSQL);
			st.setInt(1, iRunId);
			rs = st.executeQuery();
			int iTotal = 0;
			int iExtracted = 0;
			while (rs.next()) {
				int iStatus = rs.getInt(1);
				iTotal += rs.getInt(2);
				if (iStatus == 1)
					lblBDBRPendingVal.setText(rs.getString(2));
				else if (iStatus == 2) {
					lblBDBRExtractingCntVal.setText(rs.getString(2));
					lblBDBRExtrCntVal.setText(rs.getString(2));
				} else if (iStatus == 3) {
					lblBDBRExtractedCntVal.setText(rs.getString(2));
					iExtracted = rs.getInt(2);
				}
			}
			st.close();

			sSQL = "select status, count(*), ROUND(sum(filesize)/1024/1024/1024,2) from BDBR_TRANSFER_SET_FILES where TRANSFER_SET_ID IN (SELECT TRANSFER_SET_ID FROM BDBR_TRANSFER_SET WHERE run_id = ?) group by status";
			st = con.prepareStatement(sSQL);
			st.setInt(1, iRunId);
			rs = st.executeQuery();
			int iTotalFiles = 0;
			int iSent = 0;
			while (rs.next()) {
				int iStatus = rs.getInt(1);
				iTotalFiles += rs.getInt(2);
				if (iStatus == 1) {
					lblBDBRFilesCntVal.setText(rs.getString(2));
					lblBDBRDataCntVal.setText(rs.getString(3) + "GB");
				} else if (iStatus == 2)
					lblBDBRScpCntVal.setText(rs.getString(2));
				else if (iStatus == 3) {
					lblBDBRFSentCntVal.setText(rs.getString(2));
					lblBDBRDSentCntVal.setText(rs.getString(3) + "GB");
					iSent = rs.getInt(2);
				}
			}
			st.close();

			if (iTotal != iExtracted)
				extrProgressBar.setForeground(display.getSystemColor(SWT.COLOR_RED));
			else
				extrProgressBar.setForeground(display.getSystemColor(SWT.COLOR_GREEN));

			if (iTotalFiles != iSent)
				sendProgressBar.setForeground(display.getSystemColor(SWT.COLOR_RED));
			else
				sendProgressBar.setForeground(display.getSystemColor(SWT.COLOR_GREEN));

			extrProgressBar.setMaximum(iTotal);
			extrProgressBar.setSelection(iExtracted);
			sendProgressBar.setMaximum(iTotalFiles);
			sendProgressBar.setSelection(iSent);

		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
			messageBox.setMessage(e3.getMessage());
			messageBox.open();
		}
	}

	class connectListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			DBConnInput dbInput = new DBConnInput(shell);
			dbInput.create();
			dbInput.open();

			if (bConnected == false) {
				try {
					String connStr = new String("jdbc:oracle:thin:@//localhost:");
					connStr += dbInput.getPort();
					connStr += "/";
					connStr += dbInput.getSID();
					con = DriverManager.getConnection(connStr, dbInput.getDBUser(), dbInput.getDBPasswd());
					lblStatusVal.setText("Connected");
					lblSIDVal.setText(dbInput.getSID());
					bConnected = true;
					lRefreshTime = dbInput.getPause();

					backGroundRefresh();

				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
					messageBox.setMessage(e.getMessage());
					messageBox.open();
				}

			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			DBConnInput dbInput = new DBConnInput(shell);
			dbInput.create();
			dbInput.open();

			if (bConnected == false) {
				try {
					String connStr = new String("jdbc:oracle:thin:@//localhost:");
					connStr += dbInput.getPort();
					connStr += "/";
					connStr += dbInput.getSID();
					con = DriverManager.getConnection(connStr, dbInput.getDBUser(), dbInput.getDBPasswd());
					lblStatusVal.setText("Connected");
					lblSIDVal.setText(dbInput.getSID());
					bConnected = true;
					lRefreshTime = dbInput.getPause();

					backGroundRefresh();

				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
					messageBox.setMessage(e.getMessage());
					messageBox.open();
				}

			}
		}
	}

	class disconnectListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			if (bConnected == true) {
				try {
					if (thread.isRunning()) {
						thread.terminate();
						thread.interrupt();
						thread.join();
					}
					con.close();
					bConnected = false;
					lblStatusVal.setText("Disconnected");
				} catch (SQLException e) {
					lblStatusVal.setText(e.getMessage());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		public void widgetDefaultSelected(SelectionEvent event) {
			if (bConnected == true) {
				try {
					if (thread.isRunning()) {
						thread.terminate();
						thread.interrupt();
						thread.join();
					}
					con.close();
					bConnected = false;
					lblStatusVal.setText("Disconnected");
				} catch (SQLException e) {
					lblStatusVal.setText(e.getMessage());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class cleartaskconsoleListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			taskConsole.removeAll();

		}

		public void widgetDefaultSelected(SelectionEvent event) {
			taskConsole.removeAll();
		}
	}

	class copytaskconsoleListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {

		}

		public void widgetDefaultSelected(SelectionEvent event) {

		}
	}

	class runtaskListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			if (bConnected == true) {
				RunTask rTask = new RunTask(shell, SWT.ERROR);
				//rTask.create();
				rTask.open();
			} else {
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage("Not connected to the database");
				messageBox.open();
			}

		}

		public void widgetDefaultSelected(SelectionEvent event) {

		}
	}

	class refreshListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			refreshData();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			refreshData();
		}
	}

	class exitListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			shell.dispose();
			// display.dispose();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			shell.dispose();
			// display.dispose();
		}
	}

	class DBConnInput extends TitleAreaDialog {

		private Text tDBUser;
		private Text tDBPasswd;
		private Text tSID;
		private Text tPort;
		private Text tPause;

		private String sDBUser;
		private String sDBPasswd;
		private String sSID;
		private String sPort;
		private long lPause;

		public DBConnInput(Shell parentShell) {
			super(parentShell);
		}

		public void create() {
			super.create();
			setTitle("Database Login Details");
		}

		protected Control createDialogArea(Composite parent) {
			Composite area = (Composite) super.createDialogArea(parent);
			Composite container = new Composite(area, SWT.NONE);
			container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			GridLayout layout = new GridLayout(2, false);
			container.setLayout(layout);

			createsDBUser(container);
			createsDBPasswd(container);
			createsSID(container);
			createsPort(container);
			createsPause(container);
			tDBUser.setText("geneva_admin");
			tDBPasswd.setText("");
			tSID.setText("GENPRO");
			tPort.setText("23400");
			tPause.setText("20");
			return area;
		}

		private void createsDBUser(Composite container) {
			Label lbtsDBUser = new Label(container, SWT.NONE);
			lbtsDBUser.setText("DB User Name");

			GridData datasDBUser = new GridData();
			datasDBUser.grabExcessHorizontalSpace = true;
			datasDBUser.horizontalAlignment = GridData.FILL;

			tDBUser = new Text(container, SWT.BORDER);
			tDBUser.setLayoutData(datasDBUser);
		}

		private void createsDBPasswd(Composite container) {
			Label lbtsDBPasswd = new Label(container, SWT.NONE);
			lbtsDBPasswd.setText("DB Password");

			GridData datasDBPasswd = new GridData();
			datasDBPasswd.grabExcessHorizontalSpace = true;
			datasDBPasswd.horizontalAlignment = GridData.FILL;
			tDBPasswd = new Text(container, SWT.PASSWORD | SWT.BORDER);
			tDBPasswd.setLayoutData(datasDBPasswd);
		}

		private void createsSID(Composite container) {
			Label lbtsSID = new Label(container, SWT.NONE);
			lbtsSID.setText("SID");

			GridData datasSID = new GridData();
			datasSID.grabExcessHorizontalSpace = true;
			datasSID.horizontalAlignment = GridData.FILL;
			tSID = new Text(container, SWT.BORDER);
			tSID.setLayoutData(datasSID);
		}

		private void createsPort(Composite container) {
			Label lbtsPort = new Label(container, SWT.NONE);
			lbtsPort.setText("Port");

			GridData datasPort = new GridData();
			datasPort.grabExcessHorizontalSpace = true;
			datasPort.horizontalAlignment = GridData.FILL;
			tPort = new Text(container, SWT.BORDER);
			tPort.setLayoutData(datasPort);
		}

		private void createsPause(Composite container) {
			Label lbtsPause = new Label(container, SWT.NONE);
			lbtsPause.setText("Refresh Interval");

			GridData datasPause = new GridData();
			datasPause.grabExcessHorizontalSpace = true;
			datasPause.horizontalAlignment = GridData.FILL;
			tPause = new Text(container, SWT.BORDER);
			tPause.setLayoutData(datasPause);
		}

		protected boolean isResizable() {
			return true;
		}

		// save content of the Text fields because they get disposed
		// as soon as the Dialog closes
		private void saveInput() {
			sDBUser = tDBUser.getText();
			sDBPasswd = tDBPasswd.getText();
			sSID = tSID.getText();
			sPort = tPort.getText();
			lPause = Long.parseLong(tPause.getText()) * 1000;

		}

		protected void okPressed() {
			saveInput();
			super.okPressed();
		}

		public String getDBUser() {
			return sDBUser;
		}

		public String getDBPasswd() {
			return sDBPasswd;
		}

		public String getSID() {
			return sSID;
		}

		public String getPort() {
			return sPort;
		}

		public long getPause() {
			return lPause;
		}
	}

	class RefreshThread extends Thread {
		private volatile boolean running = true;

		public void terminate() {
			running = false;
		}

		public boolean isRunning() {
			return running;
		}

		public void run() {
			while (running) {
				display.asyncExec(new Runnable() {
					public void run() {
						refreshData();
					}
				});
				try {
					Thread.sleep(lRefreshTime);
				} catch (InterruptedException e) {
					running = false;
				}
			}
		}
	}

	void backGroundRefresh() {
		thread = new RefreshThread();
		/** start the working thread */
		thread.start();
	}
	
	class RunTask extends Dialog {

		protected Object result;
		protected Shell shell;

		String outString = "";
		/**
		 * Create the dialog.
		 * @param parent
		 * @param style
		 */
		public RunTask(Shell parent, int style) {
			super(parent, style);
			setText("Run Task");
		}

		/**
		 * Open the dialog.
		 * @return the result
		 */
		public Object open() {
			createContents();
			shell.open();
			shell.layout();
			Display display = getParent().getDisplay();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			
			return result;
		}

		/**
		 * Create contents of the dialog.
		 */
		private void createContents() {
			shell = new Shell(getParent(), getStyle());
			shell.setSize(450, 300);
			shell.setText(getText());
			
			
			List taskList = new List(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE | SWT.V_SCROLL);
			taskList.setBounds(10, 10, 430, 224);
			
			taskList.addSelectionListener(new SelectionListener() {
			      public void widgetSelected(SelectionEvent event) {
			        String[] selectedItems = taskList.getSelection();
			        for (int loopIndex = 0; loopIndex < selectedItems.length; loopIndex++)
			          outString += selectedItems[loopIndex] + " ";		        
			      }

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			Button btnRunTask = new Button(shell, SWT.NONE);
			btnRunTask.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TitleAreaDialog d = new TitleAreaDialog(shell);
					d.setTitle("Confirm");
					d.setMessage("Do you want to run the following task immediately ? \n\n" + outString);
					d.open();
					if ( d.getReturnCode() == SWT.OK)
						insertTaskRequest(outString);
					
				}
			});
			btnRunTask.setBounds(256, 240, 94, 28);
			btnRunTask.setText("Run Task");
			
			Button btnCancel = new Button(shell, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			btnCancel.setBounds(346, 240, 94, 28);
			btnCancel.setText("Cancel");
			
			try {
				Statement st = con.createStatement();
				String sSQL = "select TASK_NAME, TASK_ID FROM TASK ORDER BY TASK_NAME ASC";
				ResultSet rs = st.executeQuery(sSQL);
				while (rs.next()) {
					taskList.add(rs.getString(1) + " (" + rs.getString(2) + ")");
				}
				st.close();

			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ERROR);
				messageBox.setMessage(e.getMessage());
				messageBox.open();
			}
		}
		
		void insertTaskRequest(String sTaskName) {
			/*  Need to determine if the task required parameters and if so get the mask and give a prompt to enter them 
			 * 
			 * 
			 * GNVTASK.CREATETASKREQUEST1NC(
			    TASKID => TASKID,
			    RUNAFTERDTM => RUNAFTERDTM,
			    TASKPARAMETERS => TASKPARAMETERS,
			    TASKREQUESTID => TASKREQUESTID)*/

		}
	}

}
