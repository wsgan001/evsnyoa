/**
 * 
 */
package com.xnjd.hynm.action;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.title.TextTitle;

import org.jfree.data.category.DefaultCategoryDataset;



import java.awt.Font;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Administrator
 *
 */
public class JfreechartAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;

	/**
	 * @return the chart
	 */
	public JFreeChart getChart() {
		
		chart = ChartFactory.createBarChart("评价统计图", "评价项目", "评价分数(满分10分）", getDataSet(),PlotOrientation.HORIZONTAL, false, false, false);
		chart.setTitle(new TextTitle("评价统计图", new Font("黑体", Font.ITALIC, 22)));
		CategoryPlot plot = (CategoryPlot)chart.getPlot();

		// 取得横轴
		CategoryAxis categoryAxis = plot.getDomainAxis();
		// 设置横轴显示标签的字体
		categoryAxis.setLabelFont(new Font("楷体", Font.BOLD, 20)); //横轴的字体
        categoryAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 15));//横轴的标签字体
		// 取得纵轴
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		// 设置纵轴显示标签的字体
		numberAxis.setLabelFont(new Font("楷体", Font.BOLD, 20));

		
		return chart;
		
	}
  
	/**
	 * @param chart the chart to set
	 */
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	 
	private  DefaultCategoryDataset getDataSet(){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 dataset.addValue(10, "", "是否延期");
		 dataset.addValue(7, "", "问题解决");
		 dataset.addValue(5, "", "技术难度");
		 dataset.addValue(10, "","文档质量");
		 
		 return dataset;
		
		
	}
	

}
