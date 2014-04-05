package zkl.dietitian.utils.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class IsOrNotDecrator implements DisplaytagColumnDecorator{

	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		
		if(columnValue.equals("0")){
			return "否";
		}else{
			return "是";
		}
	}

}
