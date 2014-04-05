package zkl.dietitian.utils.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class SexDecractor implements DisplaytagColumnDecorator {
	@Override
	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		if(columnValue == null ||columnValue == ""){
			return "";
		}else{
			if(columnValue.equals("1")){
				return "男";
			}else {
				return "女";
			}
		}
	}
}
