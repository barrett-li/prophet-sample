/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.resoft.prophet.util.FileUtils;
import com.resoft.prophet.util.export.ExcelConvertor;
import com.resoft.prophet.util.export.ExcelConvertorException;
import com.resoft.prophet.util.export.ExcelDataSheet;

/**
 * 
 * 导出excel的基类。<br/>
 * 
 * @author 伍军军
 */
public abstract class ExcelAction extends WebWorkAction {

	/**
	 * 针对现有需求无模板导出excel公用函数<br/>
	 * 
	 * @param headList excel表头内容列表
	 * @param dataList excel内容列表
	 * @param exportExcelName excel文件名
	 */
	public void excelForNoTemplate(List headList, List dataList, String exportExcelName) {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		List dataSheetList = new ArrayList();
		ExcelDataSheet sheetData = new ExcelDataSheet(10);
		sheetData.addRow(headList);
		sheetData.addRows(dataList);
		dataSheetList.add(sheetData);

		String tmpFile = request.getSession().getServletContext().getRealPath("/") + Math.random() + ".xls";
		try {
			ExcelConvertor.convertIntoExcel(tmpFile, dataSheetList);
			FileUtils.downLoadFile(response, tmpFile, exportExcelName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 针对现有需求根据相应模板导出excel公用函数<br/>
	 * 
	 * @param dataList excel内容列表
	 * @param exportExcelName excel文件名
	 * @param templateFile 文件所在项目的路径
	 */
	public void excelForTemplate(List dataList, String exportExcelName, String templateFile) {
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		List dataSheetList = new ArrayList();
		ExcelDataSheet sheetData = new ExcelDataSheet(20);
		sheetData.addRows(dataList);
		dataSheetList.add(sheetData);

		String _templateFile = request.getSession().getServletContext().getRealPath("/") + "/" + templateFile;
		String tmpFile = request.getSession().getServletContext().getRealPath("/") + Math.random() + ".xls";
		try {
			ExcelConvertor.convertIntoExcelByTemplate(_templateFile, tmpFile, dataSheetList);
			FileUtils.downLoadFile(response, tmpFile, exportExcelName);
		} catch (ExcelConvertorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
