/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.util.export;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.Region;

/**
 * 用于Excel转换Sheet数据结构。
 * 
 * @author 伍军军
 */
public class ExcelDataSheet {
	private int initColSize = 10;
	private int colCount;
	private List rows;
	private List mergedRegions;
	private List styles;
	private List types;
	private String title;
	private String sheetName;

	public ExcelDataSheet(int initColSize) {
		super();
		this.initColSize = initColSize;
	}

	public void addCellValue(int rowIndex, Object value) {
		List row;
		rowRangeCheck(rowIndex);
		row = (List) rows.get(rowIndex);
		row.add(value);
		refreshColCount(row);
	}

	public void addMergedRegion(int rowFrom, int colFrom, int rowTo, int colTo) {
		addMergedRegion(new Region(rowFrom, (short) colFrom, rowTo, (short) colTo));
	}

	public void addRow() {
		rows.add(new ArrayList(initColSize));
	}

	public void addRow(List row) {
		if (rows == null) {
			rows = new LinkedList();
		}
		rows.add(row);
		refreshColCount(row);
	}

	public void addRows(List rows) {
		List row;
		if (this.rows == null) {
			this.rows = new LinkedList();
		}
		for (Iterator iter = rows.iterator(); iter.hasNext();) {
			row = (List) iter.next();
			refreshColCount(row);
		}
		this.rows.addAll(rows);
	}

	public void addStyle(HSSFCellStyle style) {
		if (styles == null) {
			styles = new ArrayList(initColSize);
		}
		styles.add(style);
	}

	public void addType(int type) {
		if (types == null) {
			types = new ArrayList(initColSize);
		}
		types.add(new Integer(type));
	}

	public Object getCellValueAt(int rowIndex, int colIndex) {
		List row;

		colRangeCheck(rowIndex, colIndex);
		row = (List) rows.get(rowIndex);
		return row.get(colIndex);
	}

	public int getColCount() {
		return colCount;
	}

	public Region getMergedRegion(int regionIndex) {
		return (Region) mergedRegions.get(regionIndex);
	}

	public int getMergedRegionCount() {
		if (mergedRegions == null) {
			return 0;
		}
		return mergedRegions.size();
	}

	public int getRowCount() {
		if (rows == null) {
			return 0;
		}
		return rows.size();
	}

	public int getRowSizeAt(int rowIndex) {
		List row;
		rowRangeCheck(rowIndex);
		row = (List) rows.get(rowIndex);
		return row.size();
	}

	public String getSheetName() {
		return sheetName;
	}

	public int getStyleCount() {
		if (styles == null) {
			return 0;
		}
		return styles.size();
	}

	public String getTitle() {
		return title;
	}

	public int getType(int colIndex) {
		Integer type;
		if (types == null) {
			return -1;
		}
		type = (Integer) types.get(colIndex);
		return type.intValue();
	}

	public int getTypeCount() {
		if (types == null) {
			return 0;
		}
		return types.size();
	}

	public void setCellValue(int rowIndex, int colIndex, Object value) {
		List row;
		colRangeCheck(rowIndex, colIndex);
		row = (List) rows.get(rowIndex);
		row.set(colIndex, value);
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	void addMergedRegion(Region region) {
		if (mergedRegions == null) {
			mergedRegions = new ArrayList();
		}
		mergedRegions.add(region);
	}

	List getMergedRegions() {
		return mergedRegions;
	}

	List getRows() {
		return rows;
	}

	HSSFCellStyle getStyle(int colIndex) {
		if (styles == null) {
			return null;
		}
		return (HSSFCellStyle) styles.get(colIndex);
	}

	List getStyles() {
		return styles;
	}

	List getTypes() {
		return types;
	}

	private void colRangeCheck(int rowIndex, int colIndex) {
		List row;
		if (rows == null) {
			rows = new ArrayList(initColSize);
		}
		rowRangeCheck(rowIndex);
		row = (List) rows.get(rowIndex);
		if (colIndex > row.size()) {
			throw new IndexOutOfBoundsException("colIndex: " + colIndex + ", colSize: " + row.size());
		}
	}

	private void refreshColCount(List row) {
		colCount = row.size() > colCount ? row.size() : colCount;
	}

	private void rowRangeCheck(int rowIndex) {
		if (rows == null) {
			rows = new LinkedList();
		}
		if (rowIndex >= rows.size()) {
			throw new IndexOutOfBoundsException("rowIndex: " + rowIndex + ", rowSize: " + rows.size());
		}
	}
}
