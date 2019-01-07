package baseKit;

import java.awt.Dimension;

import javafx.event.Event;
import javafx.event.EventType;

public class customEvent extends Event{

	public static final EventType<customEvent> SAVE_PUSHED = new EventType<>(Event.ANY,"SAVE_PUSHED");
	public static final EventType<customEvent> RESIZE_EVENT = new EventType<>(Event.ANY,"RESIZE_EVENT");
	public static final EventType<customEvent> MOUSE_CLICKED = new EventType<>(Event.ANY,"MOUSE_CLICLED");

	public customEvent(EventType<customEvent> type)  {
		super(type);
	}



	private String htmlText;
	private Dimension dimObject;
	
	public void setDimension(Dimension dim)
	{
		dimObject = dim;
	}
	
	public Dimension getDimension()
	{
		return dimObject;
	}
	
	public void setHtmlText(String txt)
	{
		htmlText = txt;
	}

	public String HtmlText()
	{
		return htmlText;
	}

	private static final long serialVersionUID = -6419044262306349152L;
}
