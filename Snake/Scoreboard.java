package Snake;



import baseKit.MWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Scoreboard extends MWidget{
	public Scoreboard(MainWindow parent) {
		super(parent);
		borderColor = Color.BROWN;
		fillColor = Color.GRAY;
		fillTextColor = Color.BLACK;
		
		setWidth(50);
		setHeight(100);
		setX(0);
		setY(0);
		
		Snake = null;
	}
	
	public void setBorderWidth(double w)
	{
		borderWidth = w;
	}
	
	public void setRoundedCorners(double val)
	{
		borderRadius = val;
	}
	
	private double ContentWidth()
	{
		return Width() - 2*borderWidth;
	}
	
	private double ContentHeight()
	{
		return Height() - 2*borderWidth;
	}
	
	private double ContentX()
	{
		return X() + borderWidth;
	}
	
	private double ContentY()
	{
		return Y() + borderWidth;
	}
	
	/*
	 * Draw section
	 */
	@Override
	public void draw() {
		paint();
	}
	
	private void paint()
	{
		GraphicsContext painter = P.getPainter();
		painter.setFill(borderColor);
		painter.fillRoundRect(X(), Y(), Width(), Height(), borderRadius, borderRadius);
		painter.setFill(fillColor);
		painter.fillRect(ContentX(), ContentY(), ContentWidth(), ContentHeight());
		
		painter.setFill(Color.BLACK);
		Font txtHeading = new Font(48);
		painter.setFont(txtHeading);
		painter.fillText("Scoreboard", ContentX() + 7.5, ContentY() + 48);
		
		painter.setLineWidth(5);
		
		painter.strokeLine(ContentX() + 2.5, ContentY() + 64, ContentX() + ContentWidth() - 2.5, ContentY() + 64);
		painter.setLineWidth(1);
		
		Font txtNormal = new Font(24);
		painter.setFont(txtNormal);
		
		Snake = (SnakeObject) P.Child("Snake");
		String txt = String.format("Player scorer: %d ", Snake.Lenght());
		
		painter.fillText(txt, ContentX() + 7.5, ContentY() + 128);
		
	}
	private double borderWidth = 1, borderRadius = 0;
	private Color borderColor, fillColor, fillTextColor;
	private SnakeObject Snake;

}
