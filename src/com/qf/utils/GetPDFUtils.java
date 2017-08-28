package com.qf.utils;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.qf.entity.Employee;

public class GetPDFUtils {
	// 表头
	public static final String[] tableHeader = { "姓名", "性别", "手机号码", "邮箱",
			"职位", "学历", "身份证号码", "部门", "联系地址", "建档日期" };

	// 数据表字段数
	private static final int colNumber = 10;

	// 表格的设置
	private static final int spacing = 2;

	// 表格的设置
	private static final int padding = 2;

	// 导出Pdf文挡
	public static void exportPdfDocument(List<List<String>> ls) {
		// 创建文Pdf文挡50, 50, 50,50左右上下距离
		Document document = new Document(new Rectangle(1500, 2000), 50, 50, 50,
				50);
		try {
			// 使用PDFWriter进行写文件操作
			PdfWriter.getInstance(document, new FileOutputStream(
					"d:\\HRSources.pdf"));
			document.open();
			// 中文字体
			BaseFont bfChinese = BaseFont.createFont(
					"C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// 创建有colNumber(6)列的表格
			PdfPTable datatable = new PdfPTable(colNumber);
			// 定义表格的宽度
			int[] cellsWidth = { 8, 6, 16, 16, 12, 10, 18, 10, 20, 10 };
			datatable.setWidths(cellsWidth);
			// 表格的宽度百分比
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(padding);
			datatable.getDefaultCell().setBorderWidth(spacing);
			// 设置表格的底色
			datatable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
			datatable.getDefaultCell().setHorizontalAlignment(
					Element.ALIGN_CENTER);
			// 添加表头元素
			for (int i = 0; i < colNumber; i++) {
				datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
			}
			// 添加子元素
			List<String> list = null;
			for (int i = 0; i < ls.size();) {

				list = ls.get(i);
				for (int j = 0; j < list.size();) {
					String pa = list.get(j);
					System.out.println(pa + "=" + i + "=" + j);
					datatable.addCell(new Paragraph(pa, fontChinese));
					++j;
				}
				System.out.println("=====" + list + "=====");
				++i;
			}
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

}
