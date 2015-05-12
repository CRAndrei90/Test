package Technologies;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.common.base.Splitter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class Tech {

	public static void main(String[] args) throws IOException, BiffException {

		writeChartToPDF(
				generateBarChart(),
				600,
				400,
				"C://Users//cian//Desktop//Work Java//Proiecte Interne//Recruiting Report//barchartTech.pdf");

	}

	public static JFreeChart generateBarChart() throws IOException,
			BiffException {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// ------------------------------------------------------------
		InputStream inputStream = null;
		inputStream = new FileInputStream("Book1.xls");

		POIFSFileSystem fileSystem = null;
		fileSystem = new POIFSFileSystem(inputStream);
		HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);

		HSSFSheet sheet = workBook.getSheetAt(0);

		Iterator rows = sheet.rowIterator();
		int contorRow = 0;
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();

			contorRow = row.getRowNum();
		}

		Workbook workbook = Workbook.getWorkbook(new File("Book1.xls"));
		Sheet sheet2 = workbook.getSheet(0);

		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		Cell cell1;
		for (int i = 1; i <= contorRow; i++) {
			cell1 = sheet2.getCell(3, i);
			hm.put(i, cell1.getContents());
		}

		

		workbook.close();

		Map<String, Integer> finalMap = new HashMap<String, Integer>();
		Set<Map.Entry<Integer, String>> st = hm.entrySet();
		int contorizareValori = 0;
		Set set = new HashSet();

		List<String> listData = new ArrayList<String>();
		for (Map.Entry<Integer, String> me : st) {
			List<String> list = Splitter.on(";#").splitToList(me.getValue());

			System.out.println(me.getKey() + ":" + me.getValue());
			for (String element : list) {
				listData.add(element);

			}

		}
		System.out.println("Numarul de .net este :" + contorizareValori);
		for (String elem1 : listData) {
			set.add(elem1);
		}
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object iter = it.next();
			contorizareValori = 0;
			for (String element : listData) {
				if (iter.equals(element))
					contorizareValori++;
			}
			finalMap.put((String) iter, contorizareValori);

		}
		Set<Map.Entry<String, Integer>> st1 = finalMap.entrySet();

		// --------------------------------------------------------------
		for (Map.Entry<String, Integer> mapFinal : st1) {
			System.out.println(mapFinal.getKey() + " : " + mapFinal.getValue());
			dataSet.setValue(mapFinal.getValue(), "Population",
					mapFinal.getKey());
		}

		

		JFreeChart chart = ChartFactory.createBarChart("How many Technologies",
				"Technologies", "Technologies", dataSet,
				PlotOrientation.HORIZONTAL, false, true, false);

		return chart;
	}

	public static void writeChartToPDF(JFreeChart chart, int width, int height,
			String fileName) {
		PdfWriter writer = null;

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height);
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}
}
