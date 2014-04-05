package zkl.dietitian.utils.excle;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 * 
 * @author zkl
 *
 * @param <T> javaBean全类名
 */
public class ExcelImprotUtil<T> {
	
	private static final int CELL_TYPT_data = 0;
	private static final int CELL_TYPT_string = 1;
	private static final int CELL_TYPT_double = 2;
	private static final int CELL_TYPT_blank = 3;
	private static final int CELL_TYPT_boolean = 4;
	private static final int CELL_TYPT_error = 5;
	private static final int CELL_TYPT_Fdouble = 6;
	
	private Class<T> domainClass;

	@SuppressWarnings("unchecked")
	public ExcelImprotUtil(String domainClassName) {
		try {
			this.domainClass = (Class<T>) Class.forName(domainClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * @param in
	 * @param colNames 按execl表头顺序填入相应表中字段名称
	 * @param pattern 日期格式默认“yyyy-MM-dd”
	 * @param skip 忽略行数
	 * @return List<T>
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<T> excute(InputStream in,String[] colNames,String pattern,int skip) throws IOException,RuntimeException{
		ArrayList<T> beans = new ArrayList<T>();
		
	
		//获得文档对象
		POIFSFileSystem fileSystem = new POIFSFileSystem(in);
		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		HSSFSheet sheet = workbook.getSheetAt(1);
		Iterator<Row> rowItertor = sheet.rowIterator();
		for (int i = 0; i < skip; i++) {
			rowItertor.next();
		}
		while(rowItertor.hasNext()){
			Row row = rowItertor.next();
			//获得bean的实例
			T bean = null;
			try {
				bean =domainClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("不能获得该类实例",e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("不能获得该类实例",e);
			}
			Object[] params = new Object[colNames.length];
			int[] cellTypes = new int[colNames.length];
			
				for (int i = 0; i < colNames.length; i++) {
					Cell cell = row.getCell(i);
					if(row.getCell(i)!=null){
					 //判断cell类型
					 switch (cell.getCellType()) {  
		                case HSSFCell.CELL_TYPE_NUMERIC: // cell为数字  实际分为日期与数字
		               	 //日期格式
//			               	if (HSSFDateUtil.isCellDateFormatted(cell)) {
//			               		 Date date = cell.getDateCellValue();// 如果是Date类型则，取得该Cell的Date值
//			               		 cellTypes[i] = CELL_TYPT_data;
//			               		 params[i] = date;
//			               	}else {// 如果是纯数字
			               			Double doubleValue =  cell.getNumericCellValue();// 取得当前Cell的数值
			               			 cellTypes[i] = CELL_TYPT_double;
			               			 params[i] = doubleValue;
//			               	}
		               	 break;
						 case HSSFCell.CELL_TYPE_STRING: // 字符串  
		               	 String stringValue = cell.getStringCellValue();
		               	 cellTypes[i] = CELL_TYPT_string;
		               	 params[i] = stringValue;
		                    break;  
						 case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
		               	 boolean booleanValue =  cell.getBooleanCellValue();
		               	 cellTypes[i] = CELL_TYPT_boolean;
		               	 params[i] = booleanValue;
		                    break;  
						 case HSSFCell.CELL_TYPE_FORMULA: // 公式  
		               	 cell.setCellType(Cell.CELL_TYPE_NUMERIC); //设置其单元格类型为数字  
		               	 Double doubleValue2 = cell.getNumericCellValue(); //获取数字值
		               	 cellTypes[i] = CELL_TYPT_Fdouble;
		               	 params[i] = doubleValue2;
		                    break;  
						 case HSSFCell.CELL_TYPE_BLANK: // 空值  
		               	 cellTypes[i] = CELL_TYPT_blank;
		               	 params[i]="";
		                    break;  
						 case HSSFCell.CELL_TYPE_ERROR: // 故障  
		               	 cellTypes[i] = CELL_TYPT_error;
		               	 params[i]="";
		                    break;  
						 default:  
		                    break;  
		               } //switch判断结束
					}//判断cell是否为空
				}
			
			//封装数据进javabean
			 T beanValue = null;
			try {
				beanValue = insertValue(bean,cellTypes,params,colNames,pattern,row);
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			 beans.add(beanValue);
		}
		//System.out.println(subareas.get(0).getRegion().getProvince());
		return beans;
	}
	
	
	
	private  T insertValue(T bean,int[] cellTypes,Object[] params,String[] colNames,String pattern,Row row) throws InstantiationException{
        if (pattern == null||pattern.equals("")) {    
       	 pattern="yyyy-MM-dd";    
           }	
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
		Class<? extends Object> clazz = bean.getClass();
		for (int i = 0; i < params.length; i++) {
			String methodName = "set" +colNames[i].substring(0, 1).toUpperCase()+colNames[i].substring(1, colNames[i].length());
			Field f = null;
			Method setmethod = null;
			try {
				f = clazz.getDeclaredField(colNames[i]);
				 setmethod =  clazz.getDeclaredMethod(methodName, new Class[]{  
						f.getType()
				});
				
			} catch (NoSuchMethodException e) {
				throw new RuntimeException("没有"+methodName+"这个方法");
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				throw new RuntimeException("没有"+colNames[i]+"这个字段");			
			}
			String type1 = f.getType().toString();
			String type = type1.substring(type1.lastIndexOf(".")+1, type1.length());
			if(type.equalsIgnoreCase("string")){
				try {
					if(cellTypes[i]==CELL_TYPT_string){
						setmethod.invoke(bean, (String)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_blank){
						setmethod.invoke(bean, (String)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_boolean){
						setmethod.invoke(bean,String.valueOf(params[i]));
					}else if(cellTypes[i]==CELL_TYPT_double){
						setmethod.invoke(bean, String.valueOf((Double)params[i]));
					}else if(cellTypes[i]==CELL_TYPT_data){
						setmethod.invoke(bean, String.valueOf(sf.format((Date)params[i])));
					}else if(cellTypes[i]==CELL_TYPT_Fdouble){
						setmethod.invoke(bean, String.valueOf((Double)params[i]));
					}else if(cellTypes[i]==CELL_TYPT_error){
						setmethod.invoke(bean, params[i]);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else if (type.equalsIgnoreCase("integer")){
				try {
					if(cellTypes[i]==CELL_TYPT_string){
						setmethod.invoke(bean, Integer.getInteger((String) params[i]));
					}else if(cellTypes[i]==CELL_TYPT_blank){
						setmethod.invoke(bean, (Integer)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_boolean){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_double){
						Integer integerValue = null;
						try {
							 integerValue = Integer.valueOf((String) (params[i]));
						} catch (Exception e) {
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, integerValue);
					}else if(cellTypes[i]==CELL_TYPT_data){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_Fdouble){
						Integer integerValue = null;
						try {
							 integerValue = Integer.valueOf((String) params[i]);
						} catch (Exception e) {
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, integerValue);
					}else if(cellTypes[i]==CELL_TYPT_error){
						setmethod.invoke(bean, (Integer)params[i]);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else if (type.equalsIgnoreCase("long")){
				try {
					if(cellTypes[i]==CELL_TYPT_string){
						Long longValue = null;
						try {
							longValue = Long.getLong((String)params[i]);
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, longValue);
					}else if(cellTypes[i]==CELL_TYPT_blank){
						setmethod.invoke(bean, (Long)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_boolean){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_double){
						Long longValue = null;
						try {
							longValue = Long.valueOf((Long)params[i]);
						} catch (Exception e) {
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, longValue);
					}else if(cellTypes[i]==CELL_TYPT_data){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_Fdouble){
						Long longValue = null;
						try {
							longValue = Long.valueOf((Long)params[i]);
						} catch (Exception e) {
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, longValue);
					}else if(cellTypes[i]==CELL_TYPT_error){
						setmethod.invoke(bean, (Long)params[i]);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else if (type.equalsIgnoreCase("double")){
				try {
					if(cellTypes[i]==CELL_TYPT_string){
						Double doubleValue = null;
						try {
							doubleValue = Double.valueOf((String) params[i]);
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, doubleValue);
					}else if(cellTypes[i]==CELL_TYPT_blank){
						if(params[i].equals("")||params[i] == null){
							params[i] =(Object)0.0;
						}
						setmethod.invoke(bean, (Double)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_boolean){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_double){
						setmethod.invoke(bean, (Double)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_data){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_Fdouble){
						setmethod.invoke(bean, (Double)params[i]);
					}else if(cellTypes[i]==CELL_TYPT_error){
						setmethod.invoke(bean, (Double)params[i]);
					}
				}  catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else if (type.equalsIgnoreCase("float")){
				try {
					if(cellTypes[i]==CELL_TYPT_string){
						Float floatValue = null;
						try {
							floatValue = (Float)params[i];
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, floatValue);
					}else if(cellTypes[i]==CELL_TYPT_blank){
						Float floatValue = null;
						try {
							floatValue = (Float)params[i];
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, floatValue);
					}else if(cellTypes[i]==CELL_TYPT_boolean){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_double){
						Float floatValue = null;
						try {
							floatValue = (Float)params[i];
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, floatValue);
					}else if(cellTypes[i]==CELL_TYPT_data){
						throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
					}else if(cellTypes[i]==CELL_TYPT_Fdouble){
						Float floatValue = null;
						try {
							floatValue = (Float)params[i];
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, floatValue);
					}else if(cellTypes[i]==CELL_TYPT_error){
						Float floatValue = null;
						try {
							floatValue = (Float)params[i];
						} catch (Exception e) {
							throw new RuntimeException("第"+row.getRowNum()+"行，第"+i+"列，数据格式不正确！");
						}
						setmethod.invoke(bean, floatValue);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}	
		}
		return bean;
	}
	
	
}
