package Contracts;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class Contracts {

	public static void main(String[] args) throws BiffException, IOException {

		writeChartToPDF(
				generateBarChart(),
				500,
				400,
				"C://Users//cian//Desktop//Work Java//Proiecte ERNI Interne//Soft Recrutare//Recruiting Report//Contracts.pdf");
	}

	// --------------------------------------------------------------------------
	public static JFreeChart generateBarChart() throws IOException,
			BiffException {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// --------------------------------------------------------
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

		// --------------------------------------------------------
		Workbook workbook = Workbook.getWorkbook(new File("Book1.xls"));
		Sheet sheet2 = workbook.getSheet(0);

		int NumberOfCell = 0;
		List<String> var = new ArrayList<>();
		Cell cell1;
		for (int i = 1; i <= contorRow; i++) {
			cell1 = sheet2.getCell(15, i);
			var.add(cell1.getContents());
		}

		workbook.close();
		int contorLuna = 0;
		int contorAn = 0;
		Integer AnVal;
		int ContorNrVal = 0;

		Set setValLuna = new HashSet();
		String[] valAnDepunereCV = new String[100];

		String[] vectPart2Date = new String[200];
		String[] vectPart3Date = new String[200];
		for (String s : var) {
			// System.out.println(s);
			if (!s.equals("")) {
				String[] Splited = s.split("/");
				String part1Date = Splited[0];
				String part2Date = Splited[1];
				String part3Date = Splited[2];

				vectPart2Date[ContorNrVal] = part2Date;
				setValLuna.add(part2Date);
				vectPart3Date[ContorNrVal] = part3Date;

				ContorNrVal++;
			}
		}

		// --------determin numarul de ani si valoarea lor----------
		String ValAnInitial = vectPart3Date[0];
		int[] contori = new int[20];
		int valNrContori = 0;

		for (int i = 0; i < ContorNrVal; i++) {
			if (vectPart3Date[i].equals(ValAnInitial)) {
				contorAn++;
			} else {

				ValAnInitial = vectPart3Date[i];
				contori[valNrContori] = contorAn;
				valAnDepunereCV[valNrContori] = vectPart3Date[i - 1];
				valNrContori++;
				contorAn = 0;
			}
			if (i == ContorNrVal - 1) {

				contori[valNrContori] = contorAn;
				valAnDepunereCV[valNrContori] = ValAnInitial;
				valNrContori++;
			}
		}

		// --------------------------------------------------------
		Map<String, Integer> mapAn1 = new HashMap<String, Integer>();
		Map<String, Integer> mapAn2 = new HashMap<String, Integer>();

		for (int j = 0; j < valNrContori; j++) {
			Iterator it = setValLuna.iterator();
			while (it.hasNext()) {
				Object iter = it.next();
				// System.out.println(iter);
				int contor = 0;

				for (String s : var) {
					// System.out.println(s);
					if (!s.equals("") && s != null) {
						String[] Splited = s.split("/");

						String part2DateProba = Splited[1];
						String part3DateProba = Splited[2];
						if (j == 0 && part3DateProba.equals(valAnDepunereCV[j])
								&& iter.equals(part2DateProba)) {
							contor++;
							mapAn1.put((String) iter, contor);
						} else if (j == 1
								&& part3DateProba.equals(valAnDepunereCV[j])
								&& iter.equals(part2DateProba)) {
							contor++;
							mapAn2.put((String) iter, contor);
						}
					}
				}
			}
			Map<String, String> mapFinal1 = new HashMap<String, String>();
			Set<Map.Entry<String, Integer>> st1 = mapAn1.entrySet();
			Set<Map.Entry<String, Integer>> st2 = mapAn2.entrySet();
			System.out.println("Anul : " + valAnDepunereCV[j]);
			// --------------------------------------------------------------
			if (j == 0) {
				for (Map.Entry<String, Integer> mapFinal : st1) {
					System.out.println(mapFinal.getKey() + " : "
							+ mapFinal.getValue());
					dataSet.setValue(mapFinal.getValue(), "series1",
							mapFinal.getKey());
				}
			} else {
				for (Map.Entry<String, Integer> mapFinal : st2) {
					System.out.println(mapFinal.getKey() + " : "
							+ mapFinal.getValue());
					dataSet.setValue(mapFinal.getValue(), "series2",
							mapFinal.getKey());
				}
			}
		}
		// ---------------------------------------------------------

		JFreeChart chart = ChartFactory.createBarChart("Contracts",
				"Month", "Number of Persons", dataSet, PlotOrientation.VERTICAL,
				false, true, false);

		return chart;
	}

	// --------------------------------------------------------------------------
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
