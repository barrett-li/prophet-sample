/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.util.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * excel转换器。
 * 
 * @author 伍军军
 */
public class ExcelConvertor {

	private static final int MAX_ROW_COUNT = 65535;

	private final HSSFWorkbook workbook;

	/**
	 * excel转换器构造方法。
	 * 
	 * @param xlsFile 要转换的Excel文件
	 * 
	 */
	public ExcelConvertor(String xlsFile) throws IOException {
		super();
		InputStream xlsInStream = null;
		try {
			xlsInStream = new FileInputStream(xlsFile);
			this.workbook = new HSSFWorkbook(new POIFSFileSystem(xlsInStream));
		} finally {
			if (xlsInStream != null) {
				xlsInStream.close();
			}
		}
	}

	/**
	 * excel转换器构造方法。
	 * 
	 * @param xlsInStream 要转换的Excel文件输入流
	 * 
	 * @throws IOException
	 */
	public ExcelConvertor(InputStream xlsInStream) throws IOException {
		super();
		try {
			this.workbook = new HSSFWorkbook(new POIFSFileSystem(xlsInStream));
		} finally {
			if (xlsInStream != null) {
				xlsInStream.close();
			}
		}
	}

	/**
	 * 根据Excel模板, 将指定的sheetDataList转换成Excel文件
	 * 
	 * @param templateFile Excel模板文件
	 * @param outFile 输出文件
	 * @param sheetDataList ExcelDataSheet的列表
	 * 
	 * @throws IOException
	 * @throws ExcelConvertorException
	 */
	public static void convertIntoExcelByTemplate(String templateFile, String outFile, List sheetDataList) throws IOException, ExcelConvertorException {
		HSSFSheet sheetTemplt, sheet;
		HSSFRow row, firstRow;
		HSSFCell cell, firstCell;
		HSSFWorkbook bookTemplate;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		ExcelDataSheet sheetData;
		Object data;
		int startRowIndex; // 指向每个模板sheet中的行的索引
		int lastColNum; // 每个模板sheet的最后一个有效列的列号
		int dataSheetCount; // 数据所需sheet的数量
		int rowDataIndex; // sheetDataList中的ExcelDataSheet对象里的放数据的rows的索引
		int type, dataRowCountOfCurSheet;
		String[] templtSheetNames;
		HSSFCellStyle style;

		if (templateFile == null || "".equals(templateFile)) {
			throw new ExcelConvertorException("模板文件为null或空");
		}
		if (outFile == null || "".equals(outFile)) {
			throw new ExcelConvertorException("输出文件为null或空");
		}
		if (sheetDataList == null || sheetDataList.size() == 0) {
			throw new ExcelConvertorException("传入的List为null");
		}

		try {
			// 读取模板book
			inStream = new FileInputStream(templateFile);
			bookTemplate = new HSSFWorkbook(new POIFSFileSystem(inStream));

			// 保存模板sheetName, 用于删除
			templtSheetNames = new String[bookTemplate.getNumberOfSheets()];
			for (int i = 0; i < templtSheetNames.length; i++) {
				templtSheetNames[i] = bookTemplate.getSheetName(i);
			}

			// 检查数据的sheet数量是否符合模板
			if (sheetDataList.size() < bookTemplate.getNumberOfSheets()) {
				throw new ExcelConvertorException("传入的List缺sheet: 现有个数" + sheetDataList.size() + ", 应该有个数" + bookTemplate.getNumberOfSheets());
			}

			for (int sheetDataIndex = 0; sheetDataIndex < sheetDataList.size(); sheetDataIndex++) {
				sheetData = (ExcelDataSheet) sheetDataList.get(sheetDataIndex);
				// 得模板sheet
				sheetTemplt = bookTemplate.getSheetAt(sheetDataIndex);
				// 得到数据填充起始行
				startRowIndex = sheetTemplt.getLastRowNum();
				// 得到列数
				lastColNum = sheetTemplt.getRow(sheetTemplt.getTopRow()).getLastCellNum();
				rowDataIndex = 0;

				dataSheetCount = getSheetCountOfSheetData(sheetData);

				for (int sheetIndex = 1; sheetIndex <= dataSheetCount; sheetIndex++) {
					// 从模板中复制sheet
					sheet = bookTemplate.cloneSheet(sheetDataIndex);
					// 得到当前sheet应填数据的行数
					dataRowCountOfCurSheet = getDataRowCountOfCurSheet(sheetIndex, sheetData);

					// 设置报表标题
					if (sheetData.getTitle() != null) {
						firstRow = sheet.getRow(0);
						firstCell = firstRow.getCell((short) 0);
						firstCell.setCellValue(new HSSFRichTextString(sheetData.getTitle()));
					}

					for (int rowIndex = startRowIndex; rowDataIndex < dataRowCountOfCurSheet; rowDataIndex++, rowIndex++) {
						if (rowIndex == startRowIndex) {
							row = sheet.getRow(rowIndex);
						} else {
							row = sheet.createRow(rowIndex);
						}

						if (sheetData.getRowSizeAt(rowDataIndex) < lastColNum) {
							throw new ExcelConvertorException("sheet[" + sheetDataIndex + "], row[" + rowIndex + "]缺cell数据: 现有个数" + sheetDataList.size() + ", 应该有个数"
									+ lastColNum + "如果单元格没有数据，请设数据为null");
						}

						for (short cellIndex = 0; cellIndex < lastColNum; cellIndex++) {
							data = sheetData.getCellValueAt(rowDataIndex, cellIndex);

							// 保存单元格格式和样式
							if (sheetIndex == 1 && rowIndex == startRowIndex) {
								cell = row.getCell(cellIndex);
								sheetData.addStyle(cell.getCellStyle());
								sheetData.addType(cell.getCellType());
							}

							cell = row.createCell(cellIndex);
							style = sheetData.getStyle(cellIndex);
							type = sheetData.getType(cellIndex);
							cell.setCellStyle(style);
							cell.setCellType(type);

							setCellValue(cell, data);

						}
					}
				}
			}

			// 删除模板sheet
			for (int i = 0; i < templtSheetNames.length; i++) {
				bookTemplate.removeSheetAt(bookTemplate.getSheetIndex(templtSheetNames[i]));
			}

			createParentDir(outFile);

			outStream = new FileOutputStream(outFile);
			bookTemplate.write(outStream);
		} catch (IOException e) {
			throw e;
		} finally {
			if (outStream != null) {
				outStream.close();
			}
			if (inStream != null) {
				inStream.close();
			}
		}
	}

	/**
	 * 将ExcelDataSheet的列表里的数据转成Excel文件
	 * 
	 * @param outFile 输出文件
	 * @param sheetDataList ExcelDataSheet的列表
	 * 
	 * @throws IOException
	 */
	public static void convertIntoExcel(String outFile, List sheetDataList) throws IOException {
		HSSFSheet sheet;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook workbook;
		FileOutputStream outputStream = null;

		ExcelDataSheet sheetData;
		Region merRegion;
		String sheetName;
		Object cellData;
		int rowDataIndex, dataRowCountOfCurSheet, sheetCount;
		workbook = new HSSFWorkbook();
		for (int sheetDataIndex = 0; sheetDataIndex < sheetDataList.size(); sheetDataIndex++) {
			rowDataIndex = 0;
			sheetData = (ExcelDataSheet) sheetDataList.get(sheetDataIndex);
			sheetCount = getSheetCountOfSheetData(sheetData);
			for (int sheetIndex = 1; sheetIndex <= sheetCount; sheetIndex++) {
				dataRowCountOfCurSheet = getDataRowCountOfCurSheet(sheetIndex, sheetData);
				// 创建sheet
				if (sheetData.getSheetName() == null) {
					sheet = workbook.createSheet();
				} else {
					if (sheetCount == 1) {
						sheetName = sheetData.getSheetName();
					} else {
						sheetName = sheetData.getSheetName() + sheetIndex;
					}
					sheet = workbook.createSheet(sheetName);
				}

				// 创建行
				for (int rowIndex = 0; rowDataIndex < dataRowCountOfCurSheet; rowDataIndex++, rowIndex++) {
					row = sheet.createRow(rowIndex);

					// 创建列
					for (short cellIndex = 0; cellIndex < sheetData.getColCount(); cellIndex++) {
						cell = row.createCell(cellIndex);
						cellData = sheetData.getCellValueAt(rowIndex, cellIndex);

						setCellValue(cell, cellData);
					}
				}

				// 合并单元格
				for (int regionIndex = 0; regionIndex < sheetData.getMergedRegionCount(); regionIndex++) {
					merRegion = sheetData.getMergedRegion(regionIndex);
					sheet.addMergedRegion(merRegion);
				}
			}

		}

		try {
			createParentDir(outFile);
			outputStream = new FileOutputStream(outFile);
			workbook.write(outputStream);
		} catch (IOException e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	private static int getDataRowCountOfCurSheet(int currentSheetNum, ExcelDataSheet sheetData) {
		int dataRowEndIndex, sheetCount;
		sheetCount = getSheetCountOfSheetData(sheetData);
		if (currentSheetNum == sheetCount) {
			// 当currentSheetNum等于总sheet数，既currentSheetNum已到最后的sheet
			dataRowEndIndex = sheetData.getRowCount();
		} else {
			dataRowEndIndex = currentSheetNum * MAX_ROW_COUNT;
		}
		return dataRowEndIndex;
	}

	private static int getSheetCountOfSheetData(ExcelDataSheet sheetData) {
		int sheetCount = sheetData.getRowCount() / MAX_ROW_COUNT;
		if (sheetData.getRowCount() % MAX_ROW_COUNT >= 0) {
			++sheetCount;
		}
		return sheetCount;
	}

	public static void main(String[] arg) throws IOException {
		List sheetDataList;

		sheetDataList = new ArrayList(3);
		for (int sheetIndex = 0; sheetIndex < 3; sheetIndex++) {
			ExcelDataSheet sheetData = new ExcelDataSheet(25);
			for (int i = 0; i < 25; i++) {
				List row = new ArrayList(10);
				for (int j = 0; j < 11; j++) {
					row.add(String.valueOf(i * 10 + j));
				}
				sheetData.addRow(row);
			}
			sheetData.setTitle("title11111111111");
			// sheetData.addMergedRegion(0, 0, 0, 9);
			sheetData.setSheetName("AA" + sheetIndex);
			sheetData.setCellValue(1, 2, "wujun");

			sheetDataList.add(sheetData);
		}

		convertIntoExcel("C:/out.xls", sheetDataList);
	}

	private static void setCellValue(HSSFCell cell, Object data) {
		if (data == null) {
			cell.setCellValue(new HSSFRichTextString(""));
			return;
		}

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
		case HSSFCell.CELL_TYPE_FORMULA:
			String strValue = data.toString().trim();
			cell.setCellValue(new HSSFRichTextString(strValue));
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (data instanceof Short) {
				Short value = (Short) data;
				cell.setCellValue(value.shortValue());
			}
			if (data instanceof Integer) {
				Integer value = (Integer) data;
				cell.setCellValue(value.intValue());
			}
			if (data instanceof Long) {
				Long value = (Long) data;
				cell.setCellValue(value.longValue());
			}
			if (data instanceof Float) {
				Float value = (Float) data;
				cell.setCellValue(value.floatValue());
			}
			if (data instanceof Double) {
				Double value = (Double) data;
				cell.setCellValue(value.doubleValue());
			}
			if (data instanceof String) {
				String value = data.toString().trim();
				cell.setCellValue(Double.parseDouble(value));
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
		case HSSFCell.CELL_TYPE_BOOLEAN:
		case HSSFCell.CELL_TYPE_ERROR:
		default:
			cell.setCellValue(new HSSFRichTextString(data.toString()));
			break;
		}
	}

	/**
	 * 转换成csv或DB2的del文件
	 * 
	 * @param sheetName 要转换的sheetName
	 * @param path 输出路径
	 * @param fileName 输出文件名
	 * @param appendedContents 追加列数据。<br>
	 *            格式: new String[]{<列数据0>,<列数据1>,...}
	 * @param ignoredColIndexes 忽略列的索引
	 * 
	 * @throws IOException
	 * @throws ExcelConvertorException
	 */
	public void convertIntoCsvOrDelFile(String sheetName, String path, String fileName, String[] appendedContents, int[] ignoredColIndexes) throws IOException,
			ExcelConvertorException {
		StringBuffer strBuffer;
		HSSFSheet sheet;
		HSSFRow row;
		HSSFCell cell;

		if (this.workbook == null) {
			throw new NullPointerException("ExcelConvertor.workbook为null");
		}

		sheet = this.workbook.getSheet(sheetName);
		if (sheet == null) {
			throw new ExcelConvertorException("本文件不是数据文件，因为不存在sheet页: " + sheetName);
		}

		strBuffer = new StringBuffer();

		int lastColNum = sheet.getRow(sheet.getTopRow()).getLastCellNum();
		for (int rowIndex = sheet.getFirstRowNum(); rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			// 略过表头
			if (rowIndex == sheet.getTopRow()) {
				continue;
			}

			row = sheet.getRow(rowIndex);

			// 有效行前存在空行时
			if (row == null) {
				continue;
			}

			for (short cellIndex = row.getFirstCellNum(); cellIndex < lastColNum; cellIndex++) {
				// 跳过要忽略的列
				if (isIgnoredCol(ignoredColIndexes, cellIndex)) {
					continue;
				}
				cell = row.getCell(cellIndex);
				if (cell == null) {
					// 有效行中有空单元格时
					strBuffer.append(',');
				} else {
					strBuffer.append(getCellStringValue(cell)).append(',');
				}
			}

			// 其他追加的信息
			if (appendedContents != null) {
				for (int i = 0; i < appendedContents.length; i++) {
					strBuffer.append(appendedContents[i] + ',');
				}
			}
			strBuffer.append("\r\n");
		}

		createDir(path);

		genCsvFile(path, fileName, strBuffer);
	}

	private boolean isIgnoredCol(int[] ignoredColIndexes, short cellIndex) {
		boolean wantIgnoreCol = false;
		if (ignoredColIndexes != null) {
			for (int i = 0; i < ignoredColIndexes.length; i++) {
				if (cellIndex == ignoredColIndexes[i]) {
					wantIgnoreCol = true;
					break;
				}
			}
		}
		return wantIgnoreCol;
	}

	private void createDir(String path) {
		File dir;
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	private static void createParentDir(String file) {
		File f, dir;
		f = new File(file);
		dir = f.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	private void genCsvFile(String path, String fileName, StringBuffer content) throws IOException {
		java.io.OutputStream out = new java.io.FileOutputStream(new File(path + '/' + fileName));
		java.io.Writer wr = new java.io.OutputStreamWriter(out, "GBK");
		try {
			wr.write(content.toString());
		} finally {
			wr.close();
		}
	}

	private String getCellStringValue(HSSFCell cell) {
		String cellValue = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString();
			if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
				cellValue = "";
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}

		// 把","改成中文逗号
		cellValue = cellValue.replaceAll("[,]", "，");

		return cellValue;
	}

}
