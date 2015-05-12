import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class ReadExcel {

	public static void main(String[] args) throws BiffException, IOException,
			WriteException {

		// --- Folosit pentru Scriere----
		/*
		 * WritableWorkbook wworkbook; wworkbook = Workbook.createWorkbook(new
		 * File("FisrtExcelFile.xls")); WritableSheet wsheet =
		 * wworkbook.createSheet("First Sheet", 0); Label label = new Label(0,
		 * 2, "A label record"); wsheet.addCell(label); Number number = new
		 * Number(3, 4, 3.1459); wsheet.addCell(number); wworkbook.write();
		 * wworkbook.close();
		 */

		writeChartToPDF(
				generateBarChart(),
				500,
				400,
				"C://Users//cian//Desktop//Work Java//Proiecte ERNI Interne//Soft Recrutare//Recruiting Report//barchart.pdf");

	}

	public static JFreeChart generateBarChart() throws IOException,
			BiffException {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// --------------------------------------------------------
		InputStream inputStream = null;
		inputStream = new FileInputStream("C://Users//cian//Desktop//Work Java//Proiecte Interne//Book1.xls");

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

		//System.out.println("Numarul de randuri " + contorRow);
		// --------------------------------------------------------
		Workbook workbook = Workbook.getWorkbook(new File("Book1.xls"));
		Sheet sheet2 = workbook.getSheet(0);

		int NumberOfCell = 0;
		List<String> var = new ArrayList<>();
		Cell cell1;
		for (int i = 1; i <= contorRow; i++) {
			cell1 = sheet2.getCell(2, i);
			var.add(cell1.getContents());
		}

		workbook.close();
		int contorLuna = 0;
		int contorAn = 0;
		Integer AnVal;
		int ContorNrVal = 0;
		String[] valAnDepunereCV = new String[100];

		String[] vectPart2Date = new String[200];
		String[] vectPart3Date = new String[200];
		for (String s : var) {
			//System.out.println(s);
			String[] Splited = s.split("/");
			String part1Date = Splited[0];
			String part2Date = Splited[1];
			String part3Date = Splited[2];

			vectPart2Date[ContorNrVal] = part2Date;

			vectPart3Date[ContorNrVal] = part3Date;

			ContorNrVal++;

		}
		String ValAnInitial = vectPart3Date[0];
		int[] contori = new int[20];
		int valNrContori = 0;
		//System.out.println("Numarul de valori este :" + ContorNrVal);

		for (int i = 0; i < ContorNrVal; i++) {
			if (vectPart3Date[i].equals(ValAnInitial)) {
				contorAn++;
			} else {
				//System.out.println("Numarul de CV-uri pe Anul " + ValAnInitial
					//	+ " sunt :" + contorAn);

				ValAnInitial = vectPart3Date[i];
				contori[valNrContori] = contorAn;
				valAnDepunereCV[valNrContori] = vectPart3Date[i - 1];
				valNrContori++;
				contorAn = 0;
			}
			if (i == ContorNrVal - 1) {
			//System.out.println("Numarul de CV-uri pe Anul " + ValAnInitial
				//		+ " sunt :" + contorAn);

				contori[valNrContori] = contorAn;
				valAnDepunereCV[valNrContori] = ValAnInitial;
				valNrContori++;
			}
		}

		// --------------------------------------------------------
		for (int j = 0; j < valNrContori; j++) {

			System.out.println(valAnDepunereCV[j] + "  " + contori[j]);
		}
		for (int i = 0; i < valNrContori; i++) {
			dataSet.setValue(contori[i], "Population", valAnDepunereCV[i]);
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Numarul de CV-uri depuse", "AN",
				"Numarul de CV-uri depuse", dataSet, PlotOrientation.VERTICAL,
				false, true, false);

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
