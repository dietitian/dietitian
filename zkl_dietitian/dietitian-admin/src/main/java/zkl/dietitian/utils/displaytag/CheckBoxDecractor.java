package zkl.dietitian.utils.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class CheckBoxDecractor implements DisplaytagColumnDecorator{


	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		return "<input type=\"checkbox\" name=\"nid\" value=\""+columnValue+"\"/>";
	}

}
