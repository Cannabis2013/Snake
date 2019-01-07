package baseKit;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class MHWidget extends MHObject{

	public MHWidget() {
		super();
		drawBoard = new Canvas();
		mainScene = new Scene(new Pane());
		painter = drawBoard.getGraphicsContext2D();
		backgroundImage = null;
		
		paintUpdate();
		setupInputEventHandlers();
		setupResizeEvents();
		defaultLocation();
	}
	public MHWidget(Pane layout)
	{
		super();
		drawBoard = new Canvas();
		mainScene = new Scene(new Pane());
		painter = drawBoard.getGraphicsContext2D();
		backgroundImage = null;
		
		setLayout(layout);
		setupResizeEvents();
		defaultLocation();
	}
	
	public GraphicsContext getPainter()
	{
		return drawBoard.getGraphicsContext2D();
	}
	
	// Get dimension and position
	
	public Dimension Size()
	{
		return mainStage.size();
	}
	
	public int Width()
	{
		return mainStage.width();
	}
	
	public int Height()
	{
		return mainStage.height() - 39;
	}
	
	public void setSizeHint(sizeHint hint)
	{
		if(hint == sizeHint.fixedSize)
			setFixedSize(Width(), Height());
	}
	
	public int X()
	{
		return mainStage.X();
	}
	
	public int Y()
	{
		return mainStage.Y();
	}
	
	public Point position()
	{
		return mainStage.position();
	}
	
	// Set methods
	
	public void setMinimumSize(int minWidth, int minHeight)
	{
		mainStage.setMaxHeight(minWidth);
		mainStage.setMaxWidth(minHeight);
	}
	
	public void setMaximumSize(int maxWidth, int maxHeight)
	{
		mainStage.setMaxHeight(maxWidth);
		mainStage.setMaxWidth(maxHeight);
	}
	public void setFixedSize(double fixedWidth, double fixedHeight)
	{
		mainStage.setMaxHeight(fixedHeight);
		mainStage.setMinHeight(fixedHeight);
		mainStage.setMaxWidth(fixedWidth);
		mainStage.setMinWidth(fixedWidth);
	}
	
	public void setBackground(Image img)
	{
		backgroundImage = img;
		if(backgroundImage != null)
		{
			painter.drawImage(backgroundImage, 0, 0, Width(), Height());
		}
	}
	
	protected void paintClear()
	{
		painter.clearRect(0, 0, Width(), Height());
		if(backgroundImage != null)
			painter.drawImage(backgroundImage, 0, 0, Width(), Height());
	}

	protected void paintUpdate()
	{
		Pane grid = new Pane(drawBoard);
		setLayout(grid);
	}
	private void defaultLocation()
	{
		mainStage.setSize(400, 400);
		Rectangle2D cRect = new Rectangle2D(mainStage.getX(), mainStage.getY(), mainStage.getWidth(), mainStage.getHeight());

		ObservableList<Screen> screens = Screen.getScreensForRectangle(cRect);

		int halfWidth = mainStage.width()/2;
		int halfHeight = mainStage.height() /2;

		if(!screens.isEmpty())
		{
			Screen currentScreen= screens.get(0);
			Rectangle2D rect = currentScreen.getBounds();
			int h = (int) rect.getHeight(),
					w = (int) rect.getWidth();
			mainStage.setPosition(w / 2 - halfWidth, h /2 - halfHeight);
		}

	}
	
	public void show() {
		mainStage.show();
	}
	public void hide() {
		mainStage.hide();
	}

	public void Close()
	{
		mainStage.close();
	}
	
	/*
	 * Scenebuilder related.
	 * Enables the user to get elements by their id in the same manner known from Javascript.
	 */
	
	protected Node getElementById(String id)
	{
		return mainScene.lookup(id);
	}
	
	/*
	 * Setup layouts.
	 * This section contains two methods in which the second overloads the first.
	 * The first takes a Pane object and builds the widget around it.
	 * The second takes a String that holds a path to a fxml file. This is used when designing widgets with the scenebuilder extension.
	 */
	
	protected void setLayout(Pane root)
	{
		mainScene.setRoot(root);
		mainStage.setScene(mainScene);
	}

	protected void setLayout(String path)
	{
		try {
			Parent ui = FXMLLoader.load(MHWidget.class.getResource(path));
			
			mainScene.setRoot(ui);
			mainStage.setScene(mainScene);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Draw related section.
	 */
	
	public void drawEvent()
	{}
	
	/*
	 * Setup event methods.
	 * Adds the necessary handlers and listeners used in typical window managers.
	 */
	
	private void setupResizeEvents()
	{
		ChangeListener<Number> stageListener = (observable,oldValue,newValue) -> 
		{
			int w = (int) mainStage.getWidth(), h = (int) mainStage.getHeight();
			Dimension dim = new Dimension();
			dim.setSize(w, h);
			
			customEvent cEvent = new customEvent(customEvent.RESIZE_EVENT);
			cEvent.setDimension(dim);
			
			fireEvent(cEvent);
			
			drawBoard = new Canvas(w, h);
			painter = drawBoard.getGraphicsContext2D();
		};
		
		mainStage.widthProperty().addListener(stageListener);
		mainStage.heightProperty().addListener(stageListener);	
	}
	
	private void setupInputEventHandlers()
	{
		mainScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				keyPressEvent(event);
			}
		});
		mainScene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseClickedEvent(event);
			}
		});
		mainScene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseMoveEvent(event);
			}
		});
		
		mainScene.addEventHandler(MouseEvent.MOUSE_DRAGGED,new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseDraggedEvent(event);
			}
		});
		
		mainScene.addEventHandler(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseReleaseEvent(event);
			}
		});
		
		
		mainScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				keyReleasedEvent(event);
			}
		});
	}
	
	/*
	 * Event section.
	 * The following methods is called when an event occur.
	 */
	
	protected void keyPressEvent(KeyEvent event)
	{}
	
	protected void keyReleasedEvent(KeyEvent event)
	{}
	
	protected void mouseClickedEvent(MouseEvent event)
	{}
	
	protected void mouseMoveEvent(MouseEvent event)
	{}
	
	protected void mouseDraggedEvent(MouseEvent event)
	{}
	protected void mouseReleaseEvent(MouseEvent event) 
	{}
	
	
	/*
	 * Member variable/property section.
	 */
	
	public enum sizeHint{maxSize,fixedSize,minimumSize};
	
	protected Canvas drawBoard;
	private GraphicsContext painter;
	protected Pane ui;
	private myStage mainStage = new myStage();
	private Scene mainScene;
	protected Image backgroundImage;
}
