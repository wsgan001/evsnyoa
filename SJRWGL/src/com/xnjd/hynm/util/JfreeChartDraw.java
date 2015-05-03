/**
 * 
 */
package com.xnjd.hynm.util;

import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.xnjd.hynm.model.Evaluation;

/**
 * @author Administrator
 *
 */
public class JfreeChartDraw {
	
	public static JFreeChart createBarChart(String clsj,List<Evaluation> cList) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 
		
		   
		if (cList!=null) {
			for (int i = 0; i < cList.size(); i++) {
				dataset.addValue(cList.get(i).getFinlInfo(),"", "问题解决" );
				dataset.addValue( cList.get(i).getLateTime(),"", "是否延期");
				dataset.addValue(cList.get(i).getEventNd(),"", "技术难度");
				dataset.addValue(cList.get(i).getFileInfo(),"", "文档质量");
				//dataset.addValue(Integer.valueOf(oList.get(i).getPaizhao()),oList.get(i).getName(),"");
			}
		}
		JFreeChart chart = ChartFactory.createBarChart3D("", "考核项目", "分数", dataset, PlotOrientation.HORIZONTAL, true, false, false);
		// 重新设置图标标题，改变字体
		
			chart.setTitle(new TextTitle(clsj+"绩效考核值", new Font("黑体", Font.ITALIC, 22)));
		
		
		CategoryPlot plot = chart.getCategoryPlot();

		// 取得横轴
		CategoryAxis categoryAxis = plot.getDomainAxis();
		// 设置横轴显示标签的字体
		categoryAxis.setLabelFont(new Font("楷体", Font.BOLD, 15));

		// 取得纵轴
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		// 设置纵轴显示标签的字体
		numberAxis.setLabelFont(new Font("楷体", Font.BOLD, 12));

		// 取得注释字体
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(new Font("楷体", Font.BOLD, 13));// 设置注释字体

	

		return chart;
	}

}
