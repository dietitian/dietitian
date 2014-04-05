package zkl.dietitian.utils.fileUpload;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("fileValidator")
public class FileValidator implements Validator
{
	@Override
	public void validate(Object uploadedFile, Errors errors)
	{
		UploadedFile file = (UploadedFile) uploadedFile;

		if (file.getFile() == null || file.getFile().getSize() == 0)
		{
			errors.rejectValue("file", "uploadForm.salectFile", "Please select a file!");
		}
	}

	@Override
	public boolean supports(Class<?> arg0)
	{
		return false;
	}
}
