package zkl.dietitian.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;



import zkl.dietitian.BaseTest;
import zkl.dietitian.dao.daoInterface.CommonDao;
import zkl.dietitian.entity.datastore.Nutrition;
import zkl.dietitian.utils.excle.ExcelImprotUtil;


public class ImportExcleTest extends BaseTest{
	@Resource(name="nutritionDao")
	private CommonDao<Nutrition, String> nutritionDao;
	
	
	@Test
	public void excelImportTest(){
		ExcelImprotUtil<Nutrition> exceltool = new ExcelImprotUtil<Nutrition>("zkl.dietitian.entity.datastore.Nutrition");
		InputStream in = null;
		try {
			in = new FileInputStream(new File("C:/Users/zkl/Desktop/123/2010.xls"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String[] colNames={"name","region","edible_part",
							"energy","moisture","protein","fat",
							"df","carbohydrate","vax","vb1",
							"vb2","vb3","ve","na",
							"ca","fe","category","vc",
							"types","cholesterol"};
		String pattern = null;
		List<Nutrition> beans = null;
		try {
			beans = exceltool.excute(in, colNames, pattern,1);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < beans.size(); i++) {
			nutritionDao.create(beans.get(i));
			System.out.println(beans.get(i).getName());
		}
	}
	
	
}
