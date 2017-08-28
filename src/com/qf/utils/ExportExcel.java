package com.qf.utils;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcel {
	private static SimpleDateFormat SDF = null;

	/**
	 * 
	 * 方法描述:生成调用的方法
	 * 
	 * @param strMeaning
	 *            表头的数组
	 * @param strName
	 *            表字段数组
	 * @param collection
	 *            表数据集合
	 * @param os
	 *            输出流
	 * @throws Exception
	 *             void
	 * @author Andy 2014-4-29 下午05:40:57
	 */
	public static void exportExcel(String[] strMeaning, String[] strName,
			Collection<?> collection, OutputStream os) throws Exception {
		HSSFWorkbook wb = ExportExcel.generateExcelforObject(strMeaning,
				strName, "", collection);
		wb.write(os);
	}

	private static HSSFWorkbook generateExcelforObject(String[] strMeaning,
			String[] strName, String str, Collection<?> collection)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		HSSFWorkbook wb = new HSSFWorkbook();
		if (collection == null || collection.size() < 1)
			return wb; // 无数据侧返回
		if (strMeaning == null || strMeaning.length < 1)
			return wb; // 表头为空侧返回
		if (strName == null || strName.length < 1)
			return wb; // 字段侧返回
		if (strMeaning.length != strName.length)
			return wb; // 两个数组长度不同侧返回
		HSSFRow row = null;
		short rowNum = 0;
		// 设置工作簿的名称
		String sheetTitle = StringUtils.isEmpty(str) ? "Sheet1" : str;
		HSSFSheet sheet = wb.createSheet();
		wb.setSheetName(0, sheetTitle);
		// 设置标题
		row = sheet.createRow(rowNum);
		setTitle(row, strMeaning, wb);
		for (Iterator<?> iter = collection.iterator(); iter.hasNext();) {
			Object exportEle = iter.next();
			// 行对象
			row = sheet.createRow(++rowNum);
			// 设置对应值
			setRow(row, strName, exportEle);
		}
		return wb;
	}

	/**
	 * 方法描述: 为Excel页中的每个横行设置标题
	 * 
	 * @param row
	 * @param strMeaning
	 * @param wb
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *             void
	 * @author Andy 2014-4-29 下午05:45:45
	 */
	@SuppressWarnings("deprecation")
	private static void setTitle(HSSFRow row, String[] strMeaning,
			HSSFWorkbook wb) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 设置单元格北京颜色
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //设置单元格下部线加粗
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN); //设置单元格左部线加粗
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN); //设置单元格右部线加粗
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN); //设置单元格上部线加粗
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格字符居中

		// 生成一个字体
		HSSFFont font = wb.createFont();
		// font.setColor(HSSFColor.WHITE.index); //设置字体颜色
		font.setFontHeightInPoints((short) 10);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //设置字体加粗
		// 把字体应用到当前的样式
		style.setFont(font);
		for (int k = 0; k < strMeaning.length; k++) {
			HSSFCell cell = row.createCell((short) k);
			cell.setCellStyle(style);
			cell.setCellValue(strMeaning[k]);
		}
	}

	/**
	 * 
	 * 方法描述: 为Excel页中的每个横行设置值
	 * 
	 * @param row
	 * @param strName
	 * @param exportModel
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *             void
	 * @author Andy 2014-4-29 下午05:44:02
	 */
	@SuppressWarnings("deprecation")
	private static void setRow(HSSFRow row, String[] strName, Object exportModel)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object temp = null;
		for (int k = 0; k < strName.length; k++) {
			// Cell对象
			HSSFCell cell = row.createCell((short) k);
			// 设置对应值
			try {
				// 检查该实体是否有这个属性
				temp = PropertyUtils.getProperty(exportModel, strName[k]);
			} catch (Exception e) {
				e.getStackTrace();
				continue;
			}
			if (temp == null) {
				cell.setCellValue(StringUtils.EMPTY);
			} else {
				if (temp instanceof Date) {
					cell.setCellValue(getDateTimeFormat().format(
							Date.class.cast(temp)));
				} else if (NumberUtils.isNumber(temp.toString())) {
					cell.setCellValue(Double.parseDouble(temp.toString()));
				} else {
					cell.setCellValue(temp.toString());
				}
			}
		}
	}

	/**
	 * 方法描述: 获取系统时间格式
	 * 
	 * @return SimpleDateFormat
	 * @author Andy 2014-4-29 下午05:44:39
	 */
	public static SimpleDateFormat getDateFormat() {
		if (SDF == null) {
			SDF = new SimpleDateFormat("yyyy-MM-dd");
		}
		return SDF;
	}

	/**
	 * 方法描述: 获取系统精确时间格式
	 * 
	 * @return SimpleDateFormat
	 * @author Andy 2014-4-29 下午05:44:27
	 */
	public static SimpleDateFormat getDateTimeFormat() {
		if (SDF == null) {
			SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}
		return SDF;
	}
}
