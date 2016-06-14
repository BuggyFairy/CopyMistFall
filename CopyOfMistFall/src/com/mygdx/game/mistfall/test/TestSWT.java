package com.mygdx.game.mistfall.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemyOperation;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyType;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.cards.HC_Action;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.enums.HeroIdentifyEnum;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.GamePhaseType;
import com.mygdx.game.mistfall.model.enums.Keyword;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class TestSWT {

	// {{ Attributes

	private GameController gc;
	
	protected Shell shell;
	private TabFolder tabFolder;
	
		// {{ Game Overview
		private TabItem tbtmGameOverview;
		private Composite gameComposite;
		private SashForm gameSashForm;
		private Group gameGroupMain;
		private StyledText gameDispGamePhase;
		private Button gameBttnNextPhase;
		private Combo gameComboChosePhase;
		private Button gameBttnGoToPhase;
		private StyledText gameDispActiveHero;
		private Button gameBttnNextHero;
		private Button gameBttnGoToHero;
		private Combo gameComboChoseHero;
		// }}
	
		// {{ Hero Chart
		private TabItem tbtmHeroChart;
		private Composite heroComposit;
		private SashForm heroSashForm;
		private Group heroGroupPic;
		private Group heroGroupMain;
		private Label heroLblHeroPic;
		private Button heroBttnSwitchHeroLeft;
		private StyledText heroDispHeroName;
		private Button heroBttnSwitchHeroRight;
		private StyledText heroDispFocus;
		private StyledText heroDispRest;
		private StyledText heroDispResolve;
		private StyledText heroDispGearProficiencies;
		
		private int posHeroCharterHero;
		
		// }}
	
		// {{ Hero Card
		private TabItem tbtmHeroCards;
		private Composite hcComposit;
		private SashForm hcSashForm;
		private Group hcGroupPic;
		private Group hcGroupMain;
		private StyledText hcDispHeroName;
		private StyledText hcDispHeroArea;
		private StyledText hcDispHeroCardName;
		private StyledText hcDispCardAreaRestriction;
		private StyledText hcDispCardType;
		private StyledText hcDispCardResolve;
		private StyledText hcDispCardKeywords;
		private Label hcLblHeroPic;
		private StyledText hcDispActions;
		private Button hcBttnDrawCard;
		private Combo hcComboChooseAction;
		private Button hcBttnPlayAction;
		
		
		private Map<HeroIdentifyEnum,Image> imagesHeroes;
		
		private int posHcHero;
		private HC_Area HcArea;
		private int posHcCard;
	
		// }}
	
		// {{ Enemies
		private Group enemyGroupMain;
		
		private Label enemyDispArea;
		private Label enemyDispEnemyName;
		private Label enemyDispResolveValue;
		private Label enemyDispPhysResValue;
		private Label enemyDispMagResValue;
		private Label enemyDispAttack;
		private Label enemyDispAttackValue;
		private Label enemyDispKeywords;
		private StyledText enemyDispHeroName;
		private StyledText enemyDispAbilities;
		private StyledText enemyDispModifications;
		private StyledText enemyDispVulnerabilities;
		private Label enemyLabelPic;
		private Combo enemyComboSelectGreen;
		private Combo enemyComboSelectBlue;
		private Combo enemyComboSelectRed;
		private Button enemyBttnMoveEnemy;
		private Combo enemyComboSelectArea;
		private Button enemyBttnEnrage;
		private Button enemyBttnApplyWound;
		private StyledText enemyDispEnrage;
		private StyledText enemyDispLifeValue;
		
		private Map<EnemyType,Image> imagesEnemy;
		
		private int enemyPos;
		private int enemyAreaPos;
		private Label label_5;
		private Button hcBttnDiscardCard;
		
		// }}

	// }}
	
	// {{ Launch and Open
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestSWT window = new TestSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		startParameterInitialization();
		
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		
		setFirstOpen();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	//}}
	
	// {{ Create Contents
	protected void createContents() {
		
		// {{ Game Overview
		
			// {{ Widgets
			shell = new Shell();
			shell.setSize(1000, 600);
			shell.setText("SWT Application");
			shell.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			tabFolder = new TabFolder(shell, SWT.NONE);
			
			tbtmGameOverview = new TabItem(tabFolder, SWT.NONE);
			tbtmGameOverview.setText("Game Overview");
			
			gameComposite = new Composite(tabFolder, SWT.NONE);
			tbtmGameOverview.setControl(gameComposite);
			gameComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			gameSashForm = new SashForm(gameComposite, SWT.NONE);
			
			gameGroupMain = new Group(gameSashForm, SWT.NONE);
			gameGroupMain.setLayout(new GridLayout(6, false));
			new Label(gameGroupMain, SWT.NONE);
			
			Label label_6 = new Label(gameGroupMain, SWT.NONE);
			GridData gd_label_6 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
			gd_label_6.widthHint = 80;
			gd_label_6.heightHint = 28;
			label_6.setLayoutData(gd_label_6);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			
			gameDispGamePhase = new StyledText(gameGroupMain, SWT.READ_ONLY | SWT.WRAP);
			gameDispGamePhase.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 13, SWT.NORMAL));
			gameDispGamePhase.setText("Game Phase: Hero Phase");
			GridData gd_gameDispGamePhase = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
			gd_gameDispGamePhase.widthHint = 304;
			gd_gameDispGamePhase.heightHint = 36;
			gameDispGamePhase.setLayoutData(gd_gameDispGamePhase);
			new Label(gameGroupMain, SWT.NONE);
			
			gameBttnNextPhase = new Button(gameGroupMain, SWT.NONE);
			gameBttnNextPhase.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			
			GridData gd_gameBttnNextPhase = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			gd_gameBttnNextPhase.widthHint = 136;
			gameBttnNextPhase.setLayoutData(gd_gameBttnNextPhase);
			gameBttnNextPhase.setText("Next Phase");
			
			
			gameComboChosePhase = new Combo(gameGroupMain, SWT.READ_ONLY);
			gameComboChosePhase.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			GridData gd_gameComboChosePhase = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			gd_gameComboChosePhase.widthHint = 212;
			gameComboChosePhase.setLayoutData(gd_gameComboChosePhase);
			
			gameBttnGoToPhase = new Button(gameGroupMain, SWT.NONE);
			gameBttnGoToPhase.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			
			GridData gd_gameBttnGoToPhase = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			gd_gameBttnGoToPhase.widthHint = 138;
			gameBttnGoToPhase.setLayoutData(gd_gameBttnGoToPhase);
			gameBttnGoToPhase.setText("Go to Phase");
			
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			
			label_5 = new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			
			gameDispActiveHero = new StyledText(gameGroupMain, SWT.READ_ONLY | SWT.WRAP);
			gameDispActiveHero.setText("Active Hero: Crow the Seeker");
			gameDispActiveHero.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 13, SWT.NORMAL));
			GridData gd_gameDispActiveHero = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
			gd_gameDispActiveHero.heightHint = 41;
			gameDispActiveHero.setLayoutData(gd_gameDispActiveHero);
			new Label(gameGroupMain, SWT.NONE);
			new Label(gameGroupMain, SWT.NONE);
			
			gameBttnNextHero = new Button(gameGroupMain, SWT.NONE);
			gameBttnNextHero.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			
			gameBttnNextHero.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			gameBttnNextHero.setText("Next Hero");
			
			
			gameComboChoseHero = new Combo(gameGroupMain, SWT.READ_ONLY);
			gameComboChoseHero.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			gameComboChoseHero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
			
			gameBttnGoToHero = new Button(gameGroupMain, SWT.NONE);
			gameBttnGoToHero.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			
			gameBttnGoToHero.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			gameBttnGoToHero.setText("Go to Hero");
			new Label(gameGroupMain, SWT.NONE);
			
			gameSashForm.setWeights(new int[] {1});
			// }}
		
			// {{ Buttons
			gameBttnNextPhase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					gc.getGamePhase().goToNext(gc);
					updateGameOverview();
				}
			});
			gameBttnGoToPhase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					switch (gameComboChosePhase.getSelectionIndex()){
						case 0:
						gc.getGamePhase().setType(gc, GamePhaseType.REINFORCEMENT_PHASE);
						break;
						case 1:
							gc.getGamePhase().setType(gc, GamePhaseType.TRAVEL_PHASE);
						break;
						case 2:
							gc.getGamePhase().setType(gc, GamePhaseType.PURSUIT_PHASE);
						break;
						case 3:
							gc.getGamePhase().setType(gc, GamePhaseType.HERO_PHASE);
						break;
						case 4:
							gc.getGamePhase().setType(gc, GamePhaseType.DEFENCE_PHASE);
						break;
						case 5:
							gc.getGamePhase().setType(gc, GamePhaseType.ENCOUNTER_PHASE);
						break;
						case 6:
							gc.getGamePhase().setType(gc, GamePhaseType.TIME_PHASE);
						break;
						default:
							
					}
					
					updateGameOverview();
				}
			});
			gameBttnNextHero.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					if (gc.getActiveHero()<0){
						gc.setActiveHero(0);
					}
					else if (gc.getActiveHero()<gc.getHeroes().size()-1){
						gc.setActiveHero(gc.getActiveHero()+1);
					}
					else{
						gc.setActiveHero(0);
					}
					updateGameOverview();
				}
			});
			gameBttnGoToHero.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					gc.setActiveHero(gameComboChoseHero.getSelectionIndex());
					updateGameOverview();
				}
			});
			// }}
		
		// }}
		
		// {{ Hero Charter
		
			// {{ Widges
			
			
			tbtmHeroChart = new TabItem(tabFolder, SWT.NONE);
			tbtmHeroChart.setText("Hero Chart");
			
			heroComposit = new Composite(tabFolder, SWT.NONE);
			tbtmHeroChart.setControl(heroComposit);
			heroComposit.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			heroSashForm = new SashForm(heroComposit, SWT.NONE);
			
			heroGroupPic = new Group(heroSashForm, SWT.NONE);
			heroGroupPic.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			heroLblHeroPic = new Label(heroGroupPic, SWT.NONE);
			
			heroGroupMain = new Group(heroSashForm, SWT.NONE);
			heroGroupMain.setLayout(new GridLayout(5, false));
			new Label(heroGroupMain, SWT.NONE);
			
			Label label_1 = new Label(heroGroupMain, SWT.NONE);
			new Label(heroGroupMain, SWT.NONE);
			new Label(heroGroupMain, SWT.NONE);
			new Label(heroGroupMain, SWT.NONE);
			
			heroBttnSwitchHeroLeft = new Button(heroGroupMain, SWT.NONE);
			
			heroBttnSwitchHeroLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			heroBttnSwitchHeroLeft.setText("<");
			
			heroDispHeroName = new StyledText(heroGroupMain, SWT.READ_ONLY | SWT.WRAP);
			heroDispHeroName.setFont(SWTResourceManager.getFont("Calibri", 20, SWT.BOLD));
			heroDispHeroName.setText("Crow the Seeker");
			heroDispHeroName.setAlignment(SWT.CENTER);
			GridData gd_heroDispHeroName = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
			gd_heroDispHeroName.heightHint = 57;
			gd_heroDispHeroName.widthHint = 290;
			heroDispHeroName.setLayoutData(gd_heroDispHeroName);
			
			heroBttnSwitchHeroRight = new Button(heroGroupMain, SWT.NONE);
			
			heroBttnSwitchHeroRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			heroBttnSwitchHeroRight.setText(">");
			new Label(heroGroupMain, SWT.NONE);
			
			Label label_2 = new Label(heroGroupMain, SWT.NONE);
			label_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
			new Label(heroGroupMain, SWT.NONE);
			new Label(heroGroupMain, SWT.NONE);
			
			heroDispFocus = new StyledText(heroGroupMain, SWT.READ_ONLY | SWT.WRAP);
			heroDispFocus.setAlignment(SWT.RIGHT);
			heroDispFocus.setText("Focus: 3     ");
			heroDispFocus.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			GridData gd_heroDispFocus = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
			gd_heroDispFocus.heightHint = 36;
			gd_heroDispFocus.widthHint = 32;
			heroDispFocus.setLayoutData(gd_heroDispFocus);
			
			heroDispRest = new StyledText(heroGroupMain, SWT.READ_ONLY | SWT.WRAP);
			heroDispRest.setAlignment(SWT.CENTER);
			heroDispRest.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			heroDispRest.setText("Restoration: +1");
			GridData gd_heroDispRest = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
			gd_heroDispRest.widthHint = 23;
			heroDispRest.setLayoutData(gd_heroDispRest);
			
			heroDispResolve = new StyledText(heroGroupMain, SWT.READ_ONLY | SWT.WRAP);
			heroDispResolve.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			heroDispResolve.setText("Resolve Pool: 2");
			GridData gd_heroDispResolve = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
			gd_heroDispResolve.widthHint = 24;
			heroDispResolve.setLayoutData(gd_heroDispResolve);
			new Label(heroGroupMain, SWT.NONE);
			new Label(heroGroupMain, SWT.NONE);
			
			heroDispGearProficiencies = new StyledText(heroGroupMain, SWT.READ_ONLY | SWT.WRAP);
			heroDispGearProficiencies.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			heroDispGearProficiencies.setText("Gear Proficiencies:\r\nBOW.");
			heroDispGearProficiencies.setAlignment(SWT.CENTER);
			GridData gd_heroDispGearProficiencies = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
			gd_heroDispGearProficiencies.heightHint = 43;
			heroDispGearProficiencies.setLayoutData(gd_heroDispGearProficiencies);
			new Label(heroGroupMain, SWT.NONE);
			heroSashForm.setWeights(new int[] {1, 2});
			// }}
		
			// {{ Buttons
			
			heroBttnSwitchHeroLeft.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					if (posHeroCharterHero>0){
						posHeroCharterHero--;
					}
					else{
						posHeroCharterHero=gc.getHeroes().size()-1;
					}
					updateDisplayHeroCharter();
				}
			});
			
			heroBttnSwitchHeroRight.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					if (posHeroCharterHero<gc.getHeroes().size()-1){
						posHeroCharterHero++;
					}
					else{
						posHeroCharterHero=0;
					}
					updateDisplayHeroCharter();
				}
			});
			
			// }}
		
		// }}
		
		// {{ Hero Cards
		
			// {{ Widgets
			
			
			tbtmHeroCards = new TabItem(tabFolder, SWT.NONE);
			tbtmHeroCards.setText("Hero Cards");
			
			hcComposit = new Composite(tabFolder, SWT.NONE);
			tbtmHeroCards.setControl(hcComposit);
			hcComposit.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			hcSashForm = new SashForm(hcComposit, SWT.NONE);
			
			hcGroupPic = new Group(hcSashForm, SWT.NONE);
			hcGroupPic.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			hcLblHeroPic = new Label(hcGroupPic, SWT.NONE);
			
			hcGroupMain = new Group(hcSashForm, SWT.NONE);
			hcGroupMain.setLayout(new GridLayout(5, false));
			
			Button hcBttnSwitchHeroLeft = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchHeroLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
			hcBttnSwitchHeroLeft.setText("<");
			Label label_3 = new Label(hcGroupMain, SWT.NONE);
			GridData gd_label_3 = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
			gd_label_3.heightHint = 11;
			label_3.setLayoutData(gd_label_3);
			
			Button hcBttnSwitchHeroRight = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchHeroRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
			hcBttnSwitchHeroRight.setText(">");
			
			hcDispHeroName = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispHeroName.setFont(SWTResourceManager.getFont("Calibri", 20, SWT.BOLD));
			hcDispHeroName.setText("Crow the Seeker");
			hcDispHeroName.setAlignment(SWT.CENTER);
			GridData gd_hcDispHeroName = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
			gd_hcDispHeroName.heightHint = 46;
			hcDispHeroName.setLayoutData(gd_hcDispHeroName);
			
			Button hcBttnSwitchHeroAreaLeft = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchHeroAreaLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnSwitchHeroAreaLeft.setText("<");
			
			hcDispHeroArea = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispHeroArea.setFont(SWTResourceManager.getFont("Calibri", 14, SWT.BOLD));
			hcDispHeroArea.setAlignment(SWT.CENTER);
			hcDispHeroArea.setText("Hero Area Cards (1/2)");
			GridData gd_hcDispHeroArea = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
			gd_hcDispHeroArea.heightHint = 43;
			hcDispHeroArea.setLayoutData(gd_hcDispHeroArea);
			
			Button hcBttnSwitchHeroAreaRight = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchHeroAreaRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnSwitchHeroAreaRight.setText(">");
			
			Button hcBttnSwitchCardLeft = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchCardLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnSwitchCardLeft.setText("<");
			
			hcDispHeroCardName = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispHeroCardName.setText("Dagger");
			hcDispHeroCardName.setFont(SWTResourceManager.getFont("Calibri", 14, SWT.BOLD));
			hcDispHeroCardName.setAlignment(SWT.CENTER);
			GridData gd_hcDispHeroCardName = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
			gd_hcDispHeroCardName.heightHint = 48;
			hcDispHeroCardName.setLayoutData(gd_hcDispHeroCardName);
			
			Button hcBttnSwitchCardRight = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnSwitchCardRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnSwitchCardRight.setText(">");
			new Label(hcGroupMain, SWT.NONE);
			
			Label lblNewLabel = new Label(hcGroupMain, SWT.NONE);
			GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
			gd_lblNewLabel.heightHint = 13;
			lblNewLabel.setLayoutData(gd_lblNewLabel);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			hcDispCardAreaRestriction = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispCardAreaRestriction.setText("Area Restriction: Hand\r\n");
			hcDispCardAreaRestriction.setAlignment(SWT.CENTER);
			hcDispCardAreaRestriction.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			GridData gd_hcDispCardAreaRestriction = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_hcDispCardAreaRestriction.heightHint = 35;
			gd_hcDispCardAreaRestriction.widthHint = 189;
			hcDispCardAreaRestriction.setLayoutData(gd_hcDispCardAreaRestriction);
			
			hcDispCardType = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispCardType.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			hcDispCardType.setText("Basic Gear");
			hcDispCardType.setAlignment(SWT.CENTER);
			GridData gd_hcDispCardType = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_hcDispCardType.widthHint = 194;
			hcDispCardType.setLayoutData(gd_hcDispCardType);
			
			hcDispCardResolve = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispCardResolve.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.NORMAL));
			hcDispCardResolve.setText("Resolve: -");
			hcDispCardResolve.setAlignment(SWT.CENTER);
			hcDispCardResolve.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			hcDispCardKeywords = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispCardKeywords.setText("KEYWORD");
			hcDispCardKeywords.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.ITALIC));
			hcDispCardKeywords.setAlignment(SWT.CENTER);
			GridData gd_hcDispCardKeywords = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
			gd_hcDispCardKeywords.heightHint = 38;
			hcDispCardKeywords.setLayoutData(gd_hcDispCardKeywords);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			hcDispActions = new StyledText(hcGroupMain, SWT.READ_ONLY | SWT.WRAP);
			hcDispActions.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			hcDispActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			hcBttnDrawCard = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnDrawCard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnDrawCard.setText("Draw Card");
			
			hcBttnDiscardCard = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnDiscardCard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnDiscardCard.setText("Discard Card");
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			hcComboChooseAction = new Combo(hcGroupMain, SWT.READ_ONLY);
			hcComboChooseAction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			
			hcBttnPlayAction = new Button(hcGroupMain, SWT.NONE);
			
			hcBttnPlayAction.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			hcBttnPlayAction.setText("Play Action");
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			
			Label label_4 = new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			new Label(hcGroupMain, SWT.NONE);
			hcSashForm.setWeights(new int[] {1, 2});
			// }}
		
			// {{ Buttons
			
				// {{ Switch Hero
			
				hcBttnSwitchHeroRight.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						
						if (posHcHero<gc.getHeroes().size()-1){
							posHcHero++;
						}
						else{
							posHcHero=0;
						}
						if(gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).isEmpty()==true){
							posHcCard=-1;
						}
						else{
							posHcCard=0;
						}
						
						updateDisplayHeroCard();
					}
				});
		
				hcBttnSwitchHeroLeft.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						
						if (posHcHero>0){
							posHcHero--;
						}
						else{
							posHcHero=gc.getHeroes().size()-1;
						}
						
						if(gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).isEmpty()==true){
							posHcCard=-1;
						}
						else{
							posHcCard=0;
						}
						
						updateDisplayHeroCard();
					}
				});
			
			
				// }}
				
				// {{ Switch Hero Area
				hcBttnSwitchHeroAreaLeft.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						switch (HcArea){
							case HERO_AREA:
								HcArea=HC_Area.ADVANCED_FEAT;
							break;
							case HAND:
								HcArea=HC_Area.HERO_AREA;
							break;
							case DISCARD:
								HcArea=HC_Area.HAND;
							break;
							case BURIAL:
								HcArea=HC_Area.DISCARD;
							break;
							case ADVANCED_FEAT:
								HcArea=HC_Area.BURIAL;
							break;
							default:
						}
						if(gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).isEmpty()==true){
							posHcCard=-1;
						}
						else{
							posHcCard=0;
						}
						updateDisplayHeroCard();
					}
				});
				
				hcBttnSwitchHeroAreaRight.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						switch (HcArea){
						case HERO_AREA:
							HcArea=HC_Area.HAND;
						break;
						case HAND:
							HcArea=HC_Area.DISCARD;
						break;
						case DISCARD:
							HcArea=HC_Area.BURIAL;
						break;
						case BURIAL:
							HcArea=HC_Area.ADVANCED_FEAT;
						break;
						case ADVANCED_FEAT:
							HcArea=HC_Area.HERO_AREA;
						break;
						default:
					}
					if(gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).isEmpty()==true){
						posHcCard=-1;
					}
					else{
						posHcCard=0;
					}
						updateDisplayHeroCard();
					}
				});
				// }}

				// {{ Switch Card
				hcBttnSwitchCardLeft.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						
						if (posHcCard<0){
							return;
						}
						if (posHcCard>0){
							posHcCard--;
						}
						else{
							posHcCard=gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).size()-1;
						}
						
						updateDisplayHeroCard();
					}
				});
				
				hcBttnSwitchCardRight.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (posHcCard<0){
							return;
						}
						
						if (posHcCard<gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).size()-1){
							posHcCard++;
						}
						else{
							posHcCard=0;
						}
						
						updateDisplayHeroCard();
					}
				});
				// }}
				
				// {{ Move Card
				
				hcBttnDrawCard.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (gc.getHeroes().get(posHcHero).getDeck().isEmpty()==false){
							gc.getHeroCardController().drawCard(gc, gc.getHeroes().get(posHcHero));
							HcArea=HC_Area.HAND;
							posHcCard=gc.getHeroes().get(posHcHero).getHand().size()-1;
							updateDisplayHeroCard();
						}
					}
				});
				
				hcBttnDiscardCard.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (HcArea==HC_Area.DISCARD || HcArea==HC_Area.BURIAL || HcArea==HC_Area.ADVANCED_FEAT){
							return;
						}
						gc.getHeroCardController().discardCard(gc, gc.getHeroes().get(posHcHero), gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).get(posHcCard), HcArea);
						updateDisplayHeroCard();
						
					}
				});
				
				// }}
				
				// {{ Play Action
				hcBttnPlayAction.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						gc.getHeroCardController().sourceActionUpdatePlayability(gc);
						HeroCard card= gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).get(posHcCard);
						
						
						int errorCode = card.getActions().get(hcComboChooseAction.getSelectionIndex()).getErrorCode();
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION |SWT.OK);
						if (errorCode!=0){
						    messageBox.setMessage(gc.getHeroCardController().getErrorCodeString().get(errorCode));
							messageBox.open();
						}
						if (errorCode==0){
							gc.getHeroCardController().sourceActionChosen(gc, gc.getHeroes().get(posHcHero), card, card.getActions().get(hcComboChooseAction.getSelectionIndex()),HcArea);
						}
						updateDisplayHeroCard();
					}
				});
				// }}
				
			// }}
		
		// }}
		
		// {{ Enemies
		
			// {{ Widges
			TabItem tbtmEnemies = new TabItem(tabFolder, SWT.NONE);
			tbtmEnemies.setText("Enemies");
			
			Composite enemyComposit = new Composite(tabFolder, SWT.NONE);
			tbtmEnemies.setControl(enemyComposit);
			enemyComposit.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			SashForm enemySashForm = new SashForm(enemyComposit, SWT.NONE);
			
			Group enemyGroupPic = new Group(enemySashForm, SWT.NONE);
			enemyGroupPic.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			enemyLabelPic = new Label(enemyGroupPic, SWT.NONE);
			enemyLabelPic.setText("Pic");
			
			enemyGroupMain = new Group(enemySashForm, SWT.NONE);
			enemyGroupMain.setLayout(new GridLayout(8, false));
			
			Button enemyBttnSwitchAreaLeft = new Button(enemyGroupMain, SWT.NONE);
			enemyBttnSwitchAreaLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
			enemyBttnSwitchAreaLeft.setText("<");
			
			enemyDispArea = new Label(enemyGroupMain, SWT.NONE);
			enemyDispArea.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
			enemyDispArea.setAlignment(SWT.CENTER);
			GridData gd_enemyDispArea = new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1);
			gd_enemyDispArea.heightHint = 34;
			enemyDispArea.setLayoutData(gd_enemyDispArea);
			enemyDispArea.setText("Quest Area Enemies (1/1)");
			
			Button enemyBttnSwitchAreaRight = new Button(enemyGroupMain, SWT.NONE);
			enemyBttnSwitchAreaRight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
			enemyBttnSwitchAreaRight.setText(">");
			
			enemyDispHeroName = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispHeroName.setAlignment(SWT.CENTER);
			enemyDispHeroName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
			enemyDispHeroName.setText("Crow the Seeker");
			GridData gd_enemyDispHeroName = new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1);
			gd_enemyDispHeroName.heightHint = 35;
			enemyDispHeroName.setLayoutData(gd_enemyDispHeroName);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispEnemyName = new Label(enemyGroupMain, SWT.NONE);
			enemyDispEnemyName.setAlignment(SWT.CENTER);
			enemyDispEnemyName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
			GridData gd_enemyDispEnemyName = new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1);
			gd_enemyDispEnemyName.heightHint = 39;
			enemyDispEnemyName.setLayoutData(gd_enemyDispEnemyName);
			enemyDispEnemyName.setText("Blackwood Magehunter");
			new Label(enemyGroupMain, SWT.NONE);
			
			Button enemyBttnSwitchEnemyLeft = new Button(enemyGroupMain, SWT.NONE);
			
			enemyBttnSwitchEnemyLeft.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3));
			enemyBttnSwitchEnemyLeft.setText("<");
			
			enemyDispAttack = new Label(enemyGroupMain, SWT.NONE);
			enemyDispAttack.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			enemyDispAttack.setAlignment(SWT.RIGHT);
			GridData gd_enemyDispAttack = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyDispAttack.widthHint = 156;
			enemyDispAttack.setLayoutData(gd_enemyDispAttack);
			enemyDispAttack.setText("Physical Attack: ");
			
			enemyDispAttackValue = new Label(enemyGroupMain, SWT.NONE);
			enemyDispAttackValue.setAlignment(SWT.CENTER);
			enemyDispAttackValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
			GridData gd_enemyDispAttackValue = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_enemyDispAttackValue.widthHint = 51;
			enemyDispAttackValue.setLayoutData(gd_enemyDispAttackValue);
			enemyDispAttackValue.setText("2");
			

			
			Label enemyDispResolve = new Label(enemyGroupMain, SWT.RIGHT);
			GridData gd_enemyDispResolve = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
			gd_enemyDispResolve.widthHint = 188;
			enemyDispResolve.setLayoutData(gd_enemyDispResolve);
			enemyDispResolve.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			enemyDispResolve.setText("Resolve: ");
			
			enemyDispResolveValue = new Label(enemyGroupMain, SWT.CENTER);
			enemyDispResolveValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
			GridData gd_enemyDispResolveValue = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_enemyDispResolveValue.widthHint = 114;
			enemyDispResolveValue.setLayoutData(gd_enemyDispResolveValue);
			enemyDispResolveValue.setText("1");
			
			Button enemyBttnSwitchEnemyRight = new Button(enemyGroupMain, SWT.NONE);
			
			GridData gd_enemyBttnSwitchEnemyRight = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
			gd_enemyBttnSwitchEnemyRight.widthHint = 23;
			enemyBttnSwitchEnemyRight.setLayoutData(gd_enemyBttnSwitchEnemyRight);
			enemyBttnSwitchEnemyRight.setText(">");
			
			Label enemyDispPhysRes = new Label(enemyGroupMain, SWT.NONE);
			enemyDispPhysRes.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			enemyDispPhysRes.setAlignment(SWT.RIGHT);
			GridData gd_enemyDispPhysRes = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyDispPhysRes.widthHint = 128;
			enemyDispPhysRes.setLayoutData(gd_enemyDispPhysRes);
			enemyDispPhysRes.setText("Physical Resistance: ");
			
			enemyDispPhysResValue = new Label(enemyGroupMain, SWT.NONE);
			GridData gd_enemyDispPhysResValue = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_enemyDispPhysResValue.widthHint = 53;
			enemyDispPhysResValue.setLayoutData(gd_enemyDispPhysResValue);
			enemyDispPhysResValue.setAlignment(SWT.CENTER);
			enemyDispPhysResValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
			enemyDispPhysResValue.setText("2");
			
			Label enemyDispMagRes = new Label(enemyGroupMain, SWT.NONE);
			enemyDispMagRes.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			enemyDispMagRes.setAlignment(SWT.RIGHT);
			GridData gd_enemyDispMagRes = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyDispMagRes.widthHint = 178;
			enemyDispMagRes.setLayoutData(gd_enemyDispMagRes);
			enemyDispMagRes.setText("Magical Resistance: ");
			
			enemyDispMagResValue = new Label(enemyGroupMain, SWT.CENTER);
			enemyDispMagResValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
			GridData gd_enemyDispMagResValue = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_enemyDispMagResValue.widthHint = 67;
			enemyDispMagResValue.setLayoutData(gd_enemyDispMagResValue);
			enemyDispMagResValue.setText("3");
			
			Label enemyDispLife = new Label(enemyGroupMain, SWT.RIGHT);
			enemyDispLife.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			GridData gd_enemyDispLife = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyDispLife.heightHint = 31;
			gd_enemyDispLife.widthHint = 73;
			enemyDispLife.setLayoutData(gd_enemyDispLife);
			enemyDispLife.setText("Life: ");
			
			enemyDispLifeValue = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispLifeValue.setText("2 / 2");
			enemyDispLifeValue.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
			enemyDispLifeValue.setAlignment(SWT.CENTER);
			enemyDispLifeValue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispKeywords = new Label(enemyGroupMain, SWT.CENTER);
			enemyDispKeywords.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.ITALIC));
			GridData gd_enemyDispKeywords = new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1);
			gd_enemyDispKeywords.heightHint = 23;
			enemyDispKeywords.setLayoutData(gd_enemyDispKeywords);
			enemyDispKeywords.setText("Brigand. Hunter. Piercing. Ranged.");
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispVulnerabilities = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispVulnerabilities.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			enemyDispVulnerabilities.setAlignment(SWT.CENTER);
			enemyDispVulnerabilities.setText("Vulnerabilities");
			GridData gd_enemyDispVulnerabilities = new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1);
			gd_enemyDispVulnerabilities.heightHint = 26;
			enemyDispVulnerabilities.setLayoutData(gd_enemyDispVulnerabilities);
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispEnrage = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispEnrage.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			enemyDispEnrage.setAlignment(SWT.CENTER);
			enemyDispEnrage.setText("Enrage");
			GridData gd_enemyDispEnrage = new GridData(SWT.FILL, SWT.FILL, false, false, 6, 1);
			gd_enemyDispEnrage.heightHint = 28;
			enemyDispEnrage.setLayoutData(gd_enemyDispEnrage);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispAbilities = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispAbilities.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			GridData gd_enemyDispAbilities = new GridData(SWT.FILL, SWT.FILL, false, true, 5, 2);
			gd_enemyDispAbilities.widthHint = 405;
			gd_enemyDispAbilities.heightHint = 89;
			enemyDispAbilities.setLayoutData(gd_enemyDispAbilities);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyDispModifications = new StyledText(enemyGroupMain, SWT.READ_ONLY | SWT.WRAP);
			enemyDispModifications.setFont(SWTResourceManager.getFont("Calibri-Mistfall", 11, SWT.NORMAL));
			GridData gd_enemyDispModifications = new GridData(SWT.FILL, SWT.FILL, false, true, 2, 2);
			gd_enemyDispModifications.heightHint = 88;
			gd_enemyDispModifications.widthHint = 131;
			enemyDispModifications.setLayoutData(gd_enemyDispModifications);
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyComboSelectGreen = new Combo(enemyGroupMain, SWT.READ_ONLY);
			GridData gd_enemyComboSelectGreen = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
			gd_enemyComboSelectGreen.widthHint = 184;
			enemyComboSelectGreen.setLayoutData(gd_enemyComboSelectGreen);
			
			Button enemyBttnSpwnGreen = new Button(enemyGroupMain, SWT.NONE);
			
			enemyBttnSpwnGreen.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
			GridData gd_enemyBttnSpwnGreen = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			gd_enemyBttnSpwnGreen.widthHint = 152;
			enemyBttnSpwnGreen.setLayoutData(gd_enemyBttnSpwnGreen);
			enemyBttnSpwnGreen.setText("Spawn Green");
			
			Button enemyBttnDiscardEnemy = new Button(enemyGroupMain, SWT.NONE);
			GridData gd_enemyBttnDiscardEnemy = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyBttnDiscardEnemy.widthHint = 201;
			enemyBttnDiscardEnemy.setLayoutData(gd_enemyBttnDiscardEnemy);
			enemyBttnDiscardEnemy.setText("Discard Enemy");
			
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyComboSelectBlue = new Combo(enemyGroupMain, SWT.READ_ONLY);
			GridData gd_enemyComboSelectBlue = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
			gd_enemyComboSelectBlue.widthHint = 177;
			enemyComboSelectBlue.setLayoutData(gd_enemyComboSelectBlue);
			
			Button enemyBttnSpwnBlue = new Button(enemyGroupMain, SWT.NONE);
			enemyBttnSpwnBlue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			enemyBttnSpwnBlue.setText("Spawn Blue");
			
			Button enemyBttnDisperse = new Button(enemyGroupMain, SWT.NONE);
			GridData gd_enemyBttnDisperse = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
			gd_enemyBttnDisperse.widthHint = 107;
			enemyBttnDisperse.setLayoutData(gd_enemyBttnDisperse);
			enemyBttnDisperse.setText("Disperse Enemies");
			
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyComboSelectRed = new Combo(enemyGroupMain, SWT.READ_ONLY);
			GridData gd_enemyComboSelectRed = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
			gd_enemyComboSelectRed.widthHint = 177;
			enemyComboSelectRed.setLayoutData(gd_enemyComboSelectRed);
			
			Button enemyBttnSpwnRed = new Button(enemyGroupMain, SWT.NONE);
			
			enemyBttnSpwnRed.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			enemyBttnSpwnRed.setText("Spawn Red");
			
			enemyBttnEnrage = new Button(enemyGroupMain, SWT.NONE);
			enemyBttnEnrage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
			enemyBttnEnrage.setText("Enrage Enemy");
			new Label(enemyGroupMain, SWT.NONE);
			new Label(enemyGroupMain, SWT.NONE);
			
			enemyComboSelectArea = new Combo(enemyGroupMain, SWT.READ_ONLY);
			GridData gd_enemyComboSelectArea = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
			gd_enemyComboSelectArea.widthHint = 170;
			enemyComboSelectArea.setLayoutData(gd_enemyComboSelectArea);
			enemyComboSelectArea.select(0);
			
			enemyBttnMoveEnemy = new Button(enemyGroupMain, SWT.NONE);
			
			enemyBttnMoveEnemy.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
			enemyBttnMoveEnemy.setText("Move Enemy");
			
			enemyBttnApplyWound = new Button(enemyGroupMain, SWT.NONE);
			enemyBttnApplyWound.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
			enemyBttnApplyWound.setText("Apply Wound");
			new Label(enemyGroupMain, SWT.NONE);
			enemySashForm.setWeights(new int[] {1, 2});
			// }} 
		
			// {{ Buttons

				// {{ Switch Enemy Area
				
				// Left
				enemyBttnSwitchAreaLeft.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos>0){
							enemyAreaPos=enemyAreaPos-1;
						}
						else{
							enemyAreaPos=gc.getHeroes().size();
						}
						enemyPos=0;
						updateDisplayEnemies();
					}
				});
				
				// Right
				enemyBttnSwitchAreaRight.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos<gc.getHeroes().size()){
							enemyAreaPos=enemyAreaPos+1;	
							
						}
						else{
							enemyAreaPos=0;	
						}
						enemyPos=0;
						updateDisplayEnemies();
					}
				});
			
				// }}
	
				// {{ Switch Enemies
				
				enemyBttnSwitchEnemyLeft.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						// Quest Area
						if (enemyAreaPos==gc.getHeroes().size()){
							if (gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
								if (enemyPos==0){
									enemyPos=gc.getQuestArea().getQuestAreaEnemies().size()-1;
								}
								else{
									enemyPos=enemyPos-1;
								}
							}
						}
						// Hero Area
						else{
							if (gc.getHeroes().get(enemyAreaPos).getEnemies().isEmpty()==false){
								if (enemyPos==0){
									enemyPos=gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1;
								}
								else{
									enemyPos=enemyPos-1;
								}
							}
						}
						updateDisplayEnemies();
					}
				});
				
				enemyBttnSwitchEnemyRight.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						// Quest Area
						if (enemyAreaPos==gc.getHeroes().size()){
							if (gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
								if (enemyPos==gc.getQuestArea().getQuestAreaEnemies().size()-1){
									enemyPos=0;
								}
								else{
									enemyPos=enemyPos+1;
								}
							}
						}
						// Hero Area
						else{
							if (gc.getHeroes().get(enemyAreaPos).getEnemies().isEmpty()==false){
								if (enemyPos==gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1){
									enemyPos=0;
								}
								else{
									enemyPos=enemyPos+1;
								}
							}
						}
						updateDisplayEnemies();
					}
				});
				
				// }}
				
				// {{ Spawn Enemies
				
				enemyBttnSpwnGreen.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						switch (enemyComboSelectGreen.getSelectionIndex()){
							case 1:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.DIRE_WOLF));
							break;
							case 2:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.FELLSTALKER));
							break;
							case 3:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.GHOREN_BLOOD_TRACKER));
							break;
							case 4:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.GHOREN_RAGECALLER));
							break;
							case 5:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.GHOREN_SLINGER));
							break;
							case 6:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.GHOREN_SMALLHORN));
							break;
							case 7:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.GHOREN_WARRIOR));
							break;
							case 8:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.ICE_REAVER));
							break;
							case 9:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.TRACKER_HOUND));
							break;
							case 10:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.WILD_ICEHOUND));
							break;
							case 11:
								gc.getGameSetupController().getGreenEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.WILDLANDS_SHAMAN));
							break;
							default:
						}
						if (enemyAreaPos==gc.getHeroes().size()){
							gc.getEnemyController().drawEnemy(gc, EnemyArea.QUEST, null, EnemySuit.GREEN, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getQuestArea().getQuestAreaEnemies().size()-1;
						}
						else{
							gc.getEnemyController().drawEnemy(gc, EnemyArea.HERO, gc.getHeroes().get(enemyAreaPos), EnemySuit.GREEN, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1;
						}
						updateDisplayEnemies();
					}
				});
				
				enemyBttnSpwnBlue.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						
						switch (enemyComboSelectBlue.getSelectionIndex()){
							case 1:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLOODSCORNE_VAMPIRE));
							break;
							case 2:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BONESORROW_MAGUS));
							break;
							case 3:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BONESORROW_SHOOTER));
							break;
							case 4:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BONESORROW_WARRIOR));
							break;
							case 5:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.CURSED_WALKER));
							break;
							case 6:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.RAVENOUS_DRAUGR));
							break;
							case 7:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.UNDEAD_LOREMASTER));
							break;
							case 8:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.VAMPIRE_BAT_SWARM));
							break;
							case 9:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.VAMPIRE_HOUND));
							break;
							case 10:
								gc.getGameSetupController().getBlueEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.VENGEFUL_BANSHEE));
							break;
							default:
						}
						
						if (enemyAreaPos==gc.getHeroes().size()){
							gc.getEnemyController().drawEnemy(gc, EnemyArea.QUEST, null, EnemySuit.BLUE, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getQuestArea().getQuestAreaEnemies().size()-1;
						}
						else{
							gc.getEnemyController().drawEnemy(gc, EnemyArea.HERO, gc.getHeroes().get(enemyAreaPos), EnemySuit.BLUE, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1;
						}
						updateDisplayEnemies();
					}
				});
						
				enemyBttnSpwnRed.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						
						switch (enemyComboSelectRed.getSelectionIndex()){
							case 1:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACK_COVEN_CASTER));
							break;
							case 2:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_AMBUSHER));
							break;
							case 3:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_ASSASSIN));
							break;
							case 4:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_CHANGELING));
							break;
							case 5:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_CUTTPURSE));
							break;
							case 6:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_FIGHTER));
							break;
							case 7:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_HARASSER));
							break;
							case 8:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.BLACKWOOD_MAGEHUNTER));
							break;
							case 9:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.RENEGADE_FLAMECASTER));
							break;
							case 10:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.TWISTED_CURSEBEARER));
							break;
							case 11:
								gc.getGameSetupController().getRedEnemies().add(0, gc.getGameSetupController().createSpecificEnemy(gc, EnemyType.TWISTED_LASHER));
							break;
							default:
						}
						
						if (enemyAreaPos==gc.getHeroes().size()){
							gc.getEnemyController().drawEnemy(gc, EnemyArea.QUEST, null, EnemySuit.RED, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getQuestArea().getQuestAreaEnemies().size()-1;
						}
						else{
							gc.getEnemyController().drawEnemy(gc, EnemyArea.HERO, gc.getHeroes().get(enemyAreaPos), EnemySuit.RED, EnemyKeyword.ANY, 1, false);
							enemyPos=gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1;
						}
						updateDisplayEnemies();
					}
				});
				
				
				// }}
				
				// {{ Move Enemies
	
				enemyBttnDiscardEnemy.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos==gc.getHeroes().size()){
							if (gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
								// Discard Enemy
								gc.getEnemyController().discardEnemy(gc, gc.getQuestArea().getQuestAreaEnemies().get(enemyPos), EnemyArea.QUEST, null);
							}
						}
						else{
							Hero hero=gc.getHeroes().get(enemyAreaPos);
							if (hero.getEnemies().isEmpty()==false){
								// Discard Enemy
								gc.getEnemyController().discardEnemy(gc, hero.getEnemies().get(enemyPos), EnemyArea.HERO, hero);
							}
						}
						// Update Enemy Display
						updateDisplayEnemies();
					}
				});
	
				enemyBttnDisperse.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						gc.getEnemyController().disperseEnemies(gc);
						// Update Enemy Display
						updateDisplayEnemies();
					}
				});
				
				enemyBttnMoveEnemy.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos!=enemyComboSelectArea.getSelectionIndex()){
							// Move from Quest Area to Hero Area
							if (enemyAreaPos==gc.getHeroes().size() && gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
								gc.getEnemyController().moveEnemy(gc, gc.getQuestArea().getQuestAreaEnemies().get(enemyPos), EnemyArea.QUEST, EnemyArea.HERO, null, null, gc.getHeroes().get(enemyComboSelectArea.getSelectionIndex()), EnemyOperation.MOVE);
							}
							if (enemyAreaPos!=gc.getHeroes().size() && gc.getHeroes().get(enemyAreaPos).getEnemies().isEmpty()==false){
								// Move from Hero Area to Hero Area
								if (enemyComboSelectArea.getSelectionIndex()!=gc.getHeroes().size()){
									gc.getEnemyController().moveEnemy(gc, gc.getHeroes().get(enemyAreaPos).getEnemies().get(enemyPos), EnemyArea.HERO, EnemyArea.HERO, null, gc.getHeroes().get(enemyAreaPos), gc.getHeroes().get(enemyComboSelectArea.getSelectionIndex()), EnemyOperation.MOVE);
								}
								// Move from Hero Area to Quest Area
								else{
									gc.getEnemyController().moveEnemy(gc, gc.getHeroes().get(enemyAreaPos).getEnemies().get(enemyPos), EnemyArea.HERO, EnemyArea.QUEST, null, gc.getHeroes().get(enemyAreaPos), null, EnemyOperation.MOVE);
								}
							}
						}
						// Update Enemy Display
						updateDisplayEnemies();
					}
				});
				
				// }}
			
				// {{ Wounds & Enrage
				
				enemyBttnApplyWound.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos==gc.getHeroes().size() && gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
							gc.getEnemyController().enemyApplyWounds(gc, gc.getQuestArea().getQuestAreaEnemies().get(enemyPos), EnemyArea.QUEST, null, 1);
						}
						if (enemyAreaPos!=gc.getHeroes().size() && gc.getHeroes().get(enemyAreaPos).getEnemies().isEmpty()==false){
							gc.getEnemyController().enemyApplyWounds(gc, gc.getHeroes().get(enemyAreaPos).getEnemies().get(enemyPos), EnemyArea.HERO, gc.getHeroes().get(enemyAreaPos), 1);
						}
						updateDisplayEnemies();
					}
				});
				
				enemyBttnEnrage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						if (enemyAreaPos==gc.getHeroes().size() && gc.getQuestArea().getQuestAreaEnemies().isEmpty()==false){
							gc.getEnemyController().enrageEnemy(gc, EnemyArea.QUEST,gc.getQuestArea().getQuestAreaEnemies().get(enemyPos), null);
						}
						if (enemyAreaPos!=gc.getHeroes().size() && gc.getHeroes().get(enemyAreaPos).getEnemies().isEmpty()==false){
							gc.getEnemyController().enrageEnemy(gc, EnemyArea.HERO,gc.getHeroes().get(enemyAreaPos).getEnemies().get(enemyPos), gc.getHeroes().get(enemyAreaPos));
						}
						updateDisplayEnemies();
					}
				});
				
				// }}
				
			// }}
		
		// }}
	
	}
	// }}
	
	private void startParameterInitialization(){
		// Game Controller
		gc=new GameController();
		// Heroes
		gc.getGameSetupController().createNewHero(gc, HeroIdentifyEnum.CROW);
		gc.getGameSetupController().createNewHero(gc, HeroIdentifyEnum.HAREAG);
		gc.getGameSetupController().createNewHero(gc, HeroIdentifyEnum.ARDENAI);
		gc.getGameSetupController().createNewHero(gc, HeroIdentifyEnum.ARANI);
		// Initialize Stuff that needs to be
		
		gc.getHeroCardController().initialize(gc);
		
		//
		enemyPos=0;
		enemyAreaPos=gc.getHeroes().size();
		
		posHcHero=0;
		HcArea=HC_Area.HERO_AREA;
		posHcCard=0;
		
		posHeroCharterHero=0;
		
	}
	
	private void setFirstOpen(){
			
		// {{ Fill Combo
			enemyComboSelectGreen.add("Random Green Enemy");
			enemyComboSelectGreen.add("Dire Wolf");
			enemyComboSelectGreen.add("Fellstalker");
			enemyComboSelectGreen.add("Ghoren Blood Tracker");
			enemyComboSelectGreen.add("Ghoren Ragecaller");
			enemyComboSelectGreen.add("Ghoren Slinger");
			enemyComboSelectGreen.add("Ghoren Smallhorn");
			enemyComboSelectGreen.add("Ghoren Warrior");
			enemyComboSelectGreen.add("Ice Reaver");
			enemyComboSelectGreen.add("Tracker Hound");
			enemyComboSelectGreen.add("Wild Icehound");
			enemyComboSelectGreen.add("Wildlands Shaman");			
			enemyComboSelectGreen.select(0);
			
			enemyComboSelectBlue.add("Random Blue Enemy");
			enemyComboSelectBlue.add("Bloodscorne Vampire");
			enemyComboSelectBlue.add("Bonesorrow Magus");
			enemyComboSelectBlue.add("Bonesorrow Shooter");
			enemyComboSelectBlue.add("Bonesorrow Warrior");
			enemyComboSelectBlue.add("Cursed Walker");
			enemyComboSelectBlue.add("Ravenous Draugr");
			enemyComboSelectBlue.add("Undead Loremaster");
			enemyComboSelectBlue.add("Vampire Bat Swarm");
			enemyComboSelectBlue.add("Vampire Hound");
			enemyComboSelectBlue.add("Vengeful Banshee");
			enemyComboSelectBlue.select(0);
			
			enemyComboSelectRed.add("Random Red Enemy");
			enemyComboSelectRed.add("Black Coven Caster");
			enemyComboSelectRed.add("Blackwood Ambusher");
			enemyComboSelectRed.add("Blackwood Assassin");
			enemyComboSelectRed.add("Blackwood Changeling");
			enemyComboSelectRed.add("Blackwood Cuttpurse");
			enemyComboSelectRed.add("Blackwood Fighter");
			enemyComboSelectRed.add("Blackwood Harasser");
			enemyComboSelectRed.add("Blackwood Magehunter");
			enemyComboSelectRed.add("Renegade Flamecaster");
			enemyComboSelectRed.add("Twisted Cursebearer");
			enemyComboSelectRed.add("Twisted Lasher");
			enemyComboSelectRed.select(0);
		// }}
	
		// {{ Load Pictures

			// {{ Enemies
			tabFolder.setSelection(3);
			imagesEnemy=new HashMap<EnemyType,Image>();
			imagesEnemy.put(EnemyType.BLOODSCORNE_VAMPIRE, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BloodscorneVampire.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BONESORROW_MAGUS, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BonesorrowMagus.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BONESORROW_SHOOTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BonesorrowShooter.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BONESORROW_WARRIOR, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BonesorrowWarrior.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.CURSED_WALKER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/CursedWalker.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.RAVENOUS_DRAUGR, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/RavenousDraugr.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.UNDEAD_LOREMASTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/UndeadLoremaster.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.VAMPIRE_BAT_SWARM, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/VampireBatSwarm.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.VAMPIRE_HOUND, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/VampireHound.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.VENGEFUL_BANSHEE, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/VengefulBanshee.jpg"),enemyLabelPic));
			
			imagesEnemy.put(EnemyType.BLACK_COVEN_CASTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackCovenCaster.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_AMBUSHER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodAmbusher.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_ASSASSIN, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodAssassin.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_CHANGELING, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodChangeling.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_CUTTPURSE, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodCuttpurse.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_FIGHTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodFighter.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_HARASSER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodHarasser.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.BLACKWOOD_MAGEHUNTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/BlackwoodMagehunter.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.RENEGADE_FLAMECASTER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/RenegadeFlamecaster.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.TWISTED_CURSEBEARER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/TwistedCursebearer.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.TWISTED_LASHER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/TwistedLasher.jpg"),enemyLabelPic));
			
			imagesEnemy.put(EnemyType.DIRE_WOLF, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/DireWolf.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.FELLSTALKER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/Fellstalker.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.GHOREN_BLOOD_TRACKER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/GhorenBloodTracker.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.GHOREN_RAGECALLER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/GhorenRagecaller.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.GHOREN_SLINGER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/GhorenSlinger.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.GHOREN_SMALLHORN, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/GhorenSmallhorn.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.GHOREN_WARRIOR, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/GhorenWarrior.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.ICE_REAVER, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/IceReaver.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.TRACKER_HOUND, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/TrackerHound.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.WILD_ICEHOUND, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/WildIcehound.jpg"),enemyLabelPic));
			imagesEnemy.put(EnemyType.WILDLANDS_SHAMAN, imageStretch(new Image(Display.getCurrent(),"./Pics/Enemies/WildlandsShaman.jpg"),enemyLabelPic));
			// }}
			
			// {{ Heroes
			tabFolder.setSelection(2);
			imagesHeroes = new HashMap<HeroIdentifyEnum,Image>();
			imagesHeroes.put(HeroIdentifyEnum.ARANI, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Arani.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.ARDENAI, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Ardenai.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.CELENTHIA, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Celenthia.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.CROW, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Crow.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.FENGRAY, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Fengray.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.HAREAG, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Hareag.jpg"),hcLblHeroPic));
			imagesHeroes.put(HeroIdentifyEnum.VENDA, imageStretch(new Image(Display.getCurrent(),"./Pics/Heroes/Venda.jpg"),hcLblHeroPic));
			// }}
			
		// }}
	
		// {{ Fill Combo 2 
		
		
		for (Hero hero : gc.getHeroes()){
			enemyComboSelectArea.add("Hero Area: "+hero.getName());
			gameComboChoseHero.add(hero.getName());
		}
		enemyComboSelectArea.add("Quest Area");
		enemyComboSelectArea.select(0);
		gameComboChoseHero.select(0);
			
		gameComboChosePhase.add("Reinforcement Phase");
		gameComboChosePhase.add("Travel Phase");
		gameComboChosePhase.add("Pursuit Phase");
		gameComboChosePhase.add("Hero Phase");
		gameComboChosePhase.add("Defence Phase");
		gameComboChosePhase.add("Encounter Phase");
		gameComboChosePhase.add("Time Phase");
		gameComboChosePhase.select(0);
		
		// }}
	
		// Update Enemy Display
		updateDisplayEnemies();
		updateDisplayHeroCard();
		updateDisplayHeroCharter();
		updateGameOverview();
	}
	
	private Image imageStretch(Image image, Label label){
		ImageData data = image.getImageData();		
		data = data.scaledTo(label.getSize().x, label.getSize().y);
		return new Image(Display.getCurrent(),data);
	}
	
	private void updateGameOverview(){
		
		StyleRange range;
		
		gameDispGamePhase.setText("Game Phase: "+gc.getGamePhase().getString());
		range = new StyleRange();
		range.start=12;
		range.length=gameDispGamePhase.getText().length()-12;
		range.fontStyle=SWT.BOLD;
		gameDispGamePhase.setStyleRange(range);
		
		if (gc.getActiveHero()>-1){
			gameDispActiveHero.setText("Active Hero: "+gc.getHeroes().get(gc.getActiveHero()).getName());
		}
		else{
			gameDispActiveHero.setText("Active Hero: No Hero Active.");
		}
		
		range = new StyleRange();
		range.start=13;
		range.length=gameDispActiveHero.getText().length()-13;
		range.fontStyle=SWT.BOLD;
		gameDispActiveHero.setStyleRange(range);
	}
	
	private void updateDisplayEnemies(){
		if (enemyAreaPos==gc.getHeroes().size()){
			if (enemyPos>=gc.getQuestArea().getQuestAreaEnemies().size()){
				enemyPos=gc.getQuestArea().getQuestAreaEnemies().size()-1;
			}
		}
		else{
			if (enemyPos>=gc.getHeroes().get(enemyAreaPos).getEnemies().size()){
				enemyPos=gc.getHeroes().get(enemyAreaPos).getEnemies().size()-1;
			}
		}
		
		// {{ Title
		if (enemyAreaPos==gc.getHeroes().size()){
			enemyDispArea.setText("Quest Area Enemies ("+Integer.toString(enemyPos+1)+"/"+Integer.toString(gc.getQuestArea().getQuestAreaEnemies().size()) +")");
			enemyDispHeroName.setText("");
		}
		else{
			enemyDispArea.setText("Hero Area Enemies ("+Integer.toString(enemyPos+1)+"/"+Integer.toString(gc.getHeroes().get(enemyAreaPos).getEnemies().size()) +")");
			enemyDispHeroName.setText(gc.getHeroes().get(enemyAreaPos).getName());
		}
		// }}
		
		if (enemyPos>=0){
			Enemy enemy;
			if (enemyAreaPos==gc.getHeroes().size()){
				enemy = gc.getQuestArea().getQuestAreaEnemies().get(enemyPos);
			}
			else{
				enemy = gc.getHeroes().get(enemyAreaPos).getEnemies().get(enemyPos);
			}
			
			//Image + Name
			if (enemyLabelPic.getImage()!=imagesEnemy.get(enemy.getEnemyType())){
				enemyLabelPic.setImage(imagesEnemy.get(enemy.getEnemyType()));	
				enemyDispEnemyName.setText(enemy.getName());
				switch (enemy.getEnemySuit()){
					case GREEN:
						enemyDispEnemyName.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
					break;
					case BLUE:
						enemyDispEnemyName.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE));
					break;
					case RED:
						enemyDispEnemyName.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED));
					break;
					default:
					break;
				}
			}
			// }}
			
			// {{ Stats
			enemyDispLifeValue.setText(Integer.toString(enemy.getLife().getValueCurrent())+" / "+Integer.toString(enemy.getLife().getValueMod()));
			if (enemy.getLife().getValueCurrent()<enemy.getLife().getValueMod()){
				StyleRange range = new StyleRange();
				range.start=0;
				range.length=1;
				range.foreground= Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
				enemyDispLifeValue.setStyleRange(range);
			}
			enemyDispPhysResValue.setText(Integer.toString(enemy.getResistances().getPhysicalResMod())+" ("+Integer.toString(enemy.getResistances().getPhysicalResBase())+")");
			enemyDispMagResValue.setText(Integer.toString(enemy.getResistances().getMagicalResMod())+" ("+Integer.toString(enemy.getResistances().getMagicalResBase())+")");
			enemyDispResolveValue.setText(Integer.toString(enemy.getResolve()));
			if (enemy.getAttack().getType()==AttackType.PHYSICAL){
				enemyDispAttack.setText("Physical Attack: ");
			}
			else{
				enemyDispAttack.setText("Magical Attack: ");
			}
			
			enemyDispAttackValue.setText(Integer.toString(enemy.getAttack().getValueMod())+" ("+Integer.toString(enemy.getAttack().getValueBase())+")");
			// }}
			
			// {{ Keywords
			String temp="";
			for (EnemyKeyword keyword : enemy.getEnemyKeyword()){
				temp = temp +keyword.toString()+". ";
			}
			enemyDispKeywords.setText(temp);
			// }}
			
			// {{ Vulnerabilities
			if (enemy.getVulnerability().isEmpty()){
				enemyDispVulnerabilities.setText("Vulnerabilities: None");
			}
			else{
				enemyDispVulnerabilities.setText("Vulnerabilities:");
				for (Keyword vulnerability : enemy.getVulnerability()){
					enemyDispVulnerabilities.setText(enemyDispVulnerabilities.getText()+" "+vulnerability.toString()+".");
				}
				StyleRange italic = new StyleRange();
				italic.start=enemyDispVulnerabilities.getText().indexOf(":")+1;
				italic.length=enemyDispVulnerabilities.getText().length()-italic.start;
				italic.fontStyle=SWT.ITALIC;
				enemyDispVulnerabilities.setStyleRange(italic);
			}
			//}}
			
			// {{ Abilities
			
			enemyDispAbilities.setText("");

			for (EnemyAbility enemyAbility : enemy.getAbilities()){

				enemyDispAbilities.setText(enemyDispAbilities.getText()+enemyAbility.getString()+"\r\n\n");

			}
			enemyDispAbilities.setText(replaceSymbols(enemyDispAbilities.getText()));
			setBold(enemyDispAbilities);
			
			// }}
	
			// {{ Modifications
			enemyDispModifications.setText("");
			for (Modification mod : enemy.getModifications()){
				enemyDispModifications.setText(enemyDispModifications.getText()+mod.getString(gc)+"\r\n\n");
			}
			enemyDispModifications.setText(replaceSymbols(enemyDispModifications.getText()));
			setBold(enemyDispModifications);
			
			// }}
		
			// {{ Enrage
			
			enemyDispEnrage.setText(enemy.getEnrageString());

			enemyDispEnrage.setText(replaceSymbols(enemyDispEnrage.getText()));
			// }}
		}
		
		// {{ If there are no Enemies
			else {
				enemyDispEnemyName.setText("No Enemy");
				enemyDispLifeValue.setText("-/-");
				enemyDispPhysResValue.setText("-");
				enemyDispMagResValue.setText("-");
				enemyDispResolveValue.setText("-");
				enemyDispAttack.setText("Attack: ");		
				enemyDispAttackValue.setText("-");
				enemyDispKeywords.setText("-");
				enemyDispAbilities.setText("");
				enemyDispModifications.setText("");
				enemyDispVulnerabilities.setText("Vulnerabilities: -");
				enemyDispEnrage.setText("");
				enemyLabelPic.setImage(null);
			}
			// }}

	}

	private void updateDisplayHeroCard(){
		if (gc.getHeroes().isEmpty()){
			return;
		}
		if (hcLblHeroPic.getImage()!=imagesHeroes.get(gc.getHeroes().get(posHcHero).getIdentifyEnum())){
			hcDispHeroName.setText(gc.getHeroes().get(posHcHero).getName());
			hcLblHeroPic.setImage(imagesHeroes.get(gc.getHeroes().get(posHcHero).getIdentifyEnum()));
		}
		HeroCard card=null;
		
		if (gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).isEmpty()==false){
			if (posHcCard>=gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).size()){
				posHcCard=gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).size()-1;
			}
			card=gc.getHeroes().get(posHcHero).getSpecificArea(HcArea).get(posHcCard);
		}
		
		switch (HcArea){
		
			// Hero Area
			case HERO_AREA:			
				hcDispHeroArea.setText("Hero Area ("+Integer.toString(posHcCard+1)+"/"+Integer.toString(gc.getHeroes().get(posHcHero).getHeroArea().size())+")");
			break;
			
			// Hand
			case HAND:		
				hcDispHeroArea.setText("Hand ("+Integer.toString(posHcCard+1)+"/"+Integer.toString(gc.getHeroes().get(posHcHero).getHand().size())+")");
			break;
			
			// Discard
			case DISCARD:
				hcDispHeroArea.setText("Discard Pile ("+Integer.toString(posHcCard+1)+"/"+Integer.toString(gc.getHeroes().get(posHcHero).getDiscard().size())+")");
			break;
			
			// Burial
			case BURIAL:
				hcDispHeroArea.setText("Burial Pile ("+Integer.toString(posHcCard+1)+"/"+Integer.toString(gc.getHeroes().get(posHcHero).getBurial().size())+")");
			break;
			
			// Advanced Feat
			case ADVANCED_FEAT:
				hcDispHeroArea.setText("Advanced Feats ("+Integer.toString(posHcCard+1)+"/"+Integer.toString(gc.getHeroes().get(posHcHero).getAdvancedFeats().size())+")");
			break;
			
			default:
		}
		
		if (card==null){
			hcDispHeroCardName.setText("No Card");
			hcDispCardAreaRestriction.setText("");
			hcDispCardType.setText("");
			hcDispCardResolve.setText("");
			hcDispCardKeywords.setText("");
			hcDispActions.setText("");
		}
		else{
			StyleRange range;
			
			hcDispHeroCardName.setText(card.getName());
			
			hcDispCardAreaRestriction.setText("Area Restriction: "+card.getAreaRestriction().toString());
			if (gc.getHeroes().get(posHcHero).getHeroAreaRestriction().get(card.getAreaRestriction())!=null){
				hcDispCardAreaRestriction.setText(hcDispCardAreaRestriction.getText()+Integer.toString(gc.getHeroes().get(posHcHero).getHeroAreaRestriction().get(card.getAreaRestriction())));
			}
			range = new StyleRange();
			range.start=18;
			range.length=hcDispCardAreaRestriction.getText().length()-18;
			range.fontStyle=SWT.BOLD;
			hcDispCardAreaRestriction.setStyleRange(range);
			
			hcDispCardType.setText(card.getHeroCardType().toString());
			hcDispCardResolve.setText("Resolve: "+card.getResolveCost());
			range = new StyleRange();
			range.start=9;
			range.length=1;
			range.fontStyle=SWT.BOLD;
			hcDispCardResolve.setStyleRange(range);
			
			hcDispCardKeywords.setText("");
			for (Keyword keyword : card.getKeywords()){
				hcDispCardKeywords.setText(hcDispCardKeywords.getText()+keyword.toString()+". ");
			}
			
			hcDispActions.setText("");
			
			List<StyleRange> styleRange = new LinkedList<StyleRange>();
			
			int counter = 1;
			hcComboChooseAction.removeAll();
			
			for (HC_Action action : card.getActions()){
				
				// Fill Combo
				hcComboChooseAction.add("Action "+Integer.toString(counter));
				counter++;
				
				switch (action.getCardArea()){
					case HAND:
						hcDispActions.setText(hcDispActions.getText()+"*");
					break;
					case HERO_AREA:
						hcDispActions.setText(hcDispActions.getText()+"#");
					break;
					default:
				}
				switch (action.getRange()){
					case 1:
						hcDispActions.setText(hcDispActions.getText()+"{");
					break;
					case 2:
						hcDispActions.setText(hcDispActions.getText()+"|");
					break;
					case 3:
						hcDispActions.setText(hcDispActions.getText()+"}");
					break;
					case 4:
						hcDispActions.setText(hcDispActions.getText()+"~");
					break;
					default:
				}
				
				int start=hcDispActions.getText().length();
				
				switch (action.getType()){
					case FAST:
						hcDispActions.setText(hcDispActions.getText()+"Fast Action: ");
					break;
					case REGULAR:
						hcDispActions.setText(hcDispActions.getText()+"Regular Action: ");
					break;
					case REFLEX:
						hcDispActions.setText(hcDispActions.getText()+"Reflex: ");
					break;
					case FREE:
						hcDispActions.setText(hcDispActions.getText()+"Free Action: ");
					break;
					default:
				}
				
//				range = new StyleRange();
//				range.start=start;
//				range.length=hcDispActions.getText().length()-start;
//				range.fontStyle = SWT.BOLD;
//				styleRange.add(range);
				
				hcDispActions.setText(hcDispActions.getText()+action.getText()+"\r\n\n");
			}
			hcComboChooseAction.select(0);

			hcDispActions.setText(replaceSymbols(hcDispActions.getText()));
			setBold(hcDispActions);
			
		}
		
		

		
	}
	
	private void updateDisplayHeroCharter(){
		
		if (gc.getHeroes().isEmpty()){
			return;
		}
		
		StyleRange range;
		
//		boolean isFontLoaded = shell.getDisplay().loadFont("Calibri_new.ttf");
//
//		if(isFontLoaded)
//		{
//		    Font font = new Font(shell.getDisplay(), "Calibri_new.ttf", 12, SWT.NORMAL);
//		    heroDispHeroName.setText(gc.getHeroes().get(posHeroCharterHero).getName()+"#");
//		    heroDispHeroName.setFont(font);
//		    
//		}

		heroDispHeroName.setText(gc.getHeroes().get(posHeroCharterHero).getName());
		heroLblHeroPic.setImage(imagesHeroes.get(gc.getHeroes().get(posHeroCharterHero).getIdentifyEnum()));
	
		heroDispFocus.setText("Focus: "+gc.getHeroes().get(posHeroCharterHero).getFocus()+"            ");
		range = new StyleRange();
		range.start=7;
		range.length=1;
		range.fontStyle=SWT.BOLD;
		heroDispFocus.setStyleRange(range);
		
		heroDispRest.setText("Restoration: +"+gc.getHeroes().get(posHeroCharterHero).getRestoration());
		range = new StyleRange();
		range.start=13;
		range.length=2;
		range.fontStyle=SWT.BOLD;
		heroDispRest.setStyleRange(range);
		
		heroDispResolve.setText("     Resolve Pool: "+gc.getGameSetupController().getResolvePool());
		range = new StyleRange();
		range.start=19;
		range.length=1;
		range.fontStyle=SWT.BOLD;
		heroDispResolve.setStyleRange(range);
		
		heroDispGearProficiencies.setText("Gear Proficiencies:\r\n");
		for (Keyword gearKeyword : gc.getHeroes().get(posHeroCharterHero).getGearProficiencies()){
			heroDispGearProficiencies.setText(heroDispGearProficiencies.getText()+gearKeyword.toString()+". ");
		}
		range = new StyleRange();
		range.start=19;
		range.length=heroDispGearProficiencies.getText().length()-19;
		range.fontStyle=SWT.ITALIC;
		heroDispGearProficiencies.setStyleRange(range);
	}
	
	private String replaceSymbols(String text){
		int start = 0;
		String symbol;
		for (int i = 0; i < text.length(); i++){
			if (text.charAt(i)=='<'){
				start = i;
			}
			else if (text.charAt(i)=='>'){
				symbol = "0";
				switch (text.substring(start, i+1)){
						case "<Quest Area>":
							symbol="!";
						break;
						case "<Hero Area>":
							symbol="#";
						break;
						case "<Hand>":
							symbol="*";
						break;
						case "<Focus>":
							symbol=" $";
						break;
						case "<+>":
							symbol="> ";
						break;
						case "<->":
							symbol="<";
						break;
						case "<Physical Damage>":
							symbol="%";
						break;
						case "<Magical Damage>":
							symbol="&";
						break;
						case "<Physical Resistance>":
							symbol="?";
						break;
						case "<Magical Resistance>":
							symbol="@";
						break;
						case "<1 Range>":
							symbol="{";
						break;
						case "<2 Range>":
							symbol="|";
						break;
						case "<3 Range>":
							symbol="}";
						break;
						case "<4 Range>":
							symbol="~";
						break;
						case "<Reinforcements>":
							symbol=";";
						break;
						case "<Objectiv Token>":
							symbol="[";
						break;
						case "<Wound Token>":
							symbol="µ";
						break;
						case "<Life>":
							symbol="´";
						break;
						case "<Calm>":
							symbol="²";
						break;
						case "<Enrage>":
							symbol="³";
						break;
						default:
				}
				
				if (symbol!="0"){
					if (start>1){
						text = text.substring(0, start)+symbol+text.substring(i+1, text.length());
					}
					else{
						text = symbol+text.substring(i+1, text.length());
					}
					i=start;
				}
			}
		}
		
		return text;
	}

	private void setBold(StyledText text){
		String[] lines = text.getText().split("\r\n\n");
		
		StyleRange range;
		int start = 0;
		int start1 = 0;
		boolean detected;
		
		for (String line : lines){
			detected=false;
			for (int i = 0; i<line.length();i++){
				switch (line.charAt(i)){
					case '!':
					case '§':
					case '$':
					case '%':
					case '&':
					case '{':
					case '|':
					case '}':
					case '~':
					case '#':
					case '*':
						start1=i+1;
					break;
					case ':':
						range = new StyleRange();
						range.start=start+start1;
						range.length=i-start1;
						range.fontStyle = SWT.BOLD;
						text.setStyleRange(range);
						detected=true;
					break;
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						range = new StyleRange();
						range.start=start+i;
						range.length=1;
						range.fontStyle = SWT.BOLD;
						text.setStyleRange(range);
					break;
					default:
				}
				
				if (i==line.length()-1 && detected==false){
					range = new StyleRange();
					range.start=start;
					range.length=line.length();
					range.fontStyle = SWT.BOLD;
					text.setStyleRange(range);
				}
			}
			start = start + line.length()+3;
		}
	}
}
