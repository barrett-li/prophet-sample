<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ page import="com.resoft.prophet.charts.fusioncharts.FusionChart" %>
<%@ page import="com.resoft.prophet.charts.fusioncharts.Set" %>
<%
// 禁用客户端缓存
response.setHeader("Pragma", "No-Cache");
response.setHeader("Cache-Control", "No-Cache");
response.setDateHeader("Expires", 0);

FusionChart fusionChart = new FusionChart();

// 设置<chart/>属性
fusionChart.addAttribute("palette", "2");
fusionChart.addAttribute("caption", "单位销售量");
fusionChart.addAttribute("xAxisName", "月份");
fusionChart.addAttribute("yAxisName", "单位");
fusionChart.addAttribute("showValues", "0");
fusionChart.addAttribute("decimals", "0");
fusionChart.addAttribute("formatNumberScale", "0");
fusionChart.addAttribute("useRoundEdges", "1");
fusionChart.addAttribute("baseFont", "黑体");
fusionChart.addAttribute("rotateYAxisName", "0");

// 增加<set/>
fusionChart.addElement(new Set("一月", new Integer("462")));
fusionChart.addElement(new Set("二月", new Integer("857")));
fusionChart.addElement(new Set("三月", new Integer("671")));
fusionChart.addElement(new Set("四月", new Integer("494")));
fusionChart.addElement(new Set("五月", new Integer("761")));
fusionChart.addElement(new Set("六月", new Integer("960")));

out.print(fusionChart.toXML("GBK"));
%>