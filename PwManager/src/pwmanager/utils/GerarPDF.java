package pwmanager.utils;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Adriano
 */
public class GerarPDF {

    Document doc = null;
    OutputStream os = null;
    Statement st;
    ResultSet rs;
    int contador = 0;

    public GerarPDF(Connection con) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException {
        Document doc = null;
        OutputStream os = null;
        try {
            doc = new Document(PageSize.A4, 10, 10, 10, 10);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String data = dateFormat.format(date);
            os = new FileOutputStream("Relatorio_Doações.pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            Image img = Image.getInstance("relatorio.png");
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);
            Font f = new Font(FontFamily.COURIER, 18, Font.BOLD);
            Font f2 = new Font(FontFamily.COURIER, 12, Font.BOLD);
            Paragraph p = new Paragraph("Relatório gerado dia: " + data, f);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(12f);
            doc.add(p);
            PdfPTable table = new PdfPTable(4);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setPadding(5);
            table.setWidths(new float[]{40f, 40f, 60f, 60f});
            table.addCell("Login");
            table.addCell("Quantia");
            table.addCell("Pedido");
            table.addCell("Entrega");
            String query = "SELECT * FROM pw.usecashlog INNER JOIN pw.users ON users.id = usecashlog.userid";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int quantia = rs.getInt("cash");
                String quantiafinal = String.valueOf(quantia / 100) + ",00";
                table.addCell(rs.getString("name"));
                table.addCell(quantiafinal);
                table.addCell(rs.getString("creatime"));
                table.addCell(rs.getString("fintime"));
                contador++;
            }
            doc.add(table);
            Paragraph p1 = new Paragraph("Resultados gerados: " + contador, f);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(12f);
            doc.add(p1);

            Paragraph p2 = new Paragraph("Desenvolvedor Bola, Skype para contato e encomendas: Adrianolls", f2);
            p2.setAlignment(Element.ALIGN_RIGHT);
            p2.setSpacingAfter(12f);
            doc.add(p2);

        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }
}
