package index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import edu.pitt.sis.iris.squirrel.analysis.TextAnalyzer;
import com.carsFinder.dao.DBO;

import java.sql.ResultSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import common.Constants;

public class indexWritter {

    private Directory directory;
    private IndexWriter ixwriter;
    TokenStream tokenStream;
    String outPutPathDirectory = Constants.IndexDirectoryPath;
    ResultSet rs;
    Analyzer analyzer;
    private int collectionFrequency=0;
    public indexWritter() {
        try {

            directory = FSDirectory
                    .open(new File(Constants.IndexDirectoryPath));
            analyzer = new StandardAnalyzer(Version.LUCENE_36);
            ixwriter = new IndexWriter(directory, new IndexWriterConfig(
                    Version.LUCENE_36, TextAnalyzer.get("lc", "std tk",
                            "indri stop", "nostem")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void TokenizeAll() {

        try {
            DBO dbo = new DBO();
            dbo.open();
            rs = dbo.executeQuery("SELECT year,price,mileage,engineDescription,color,doorCount,driveTrain,bodyStyle,transmission,RecordID FROM record");
            while (rs.next()) {
                collectionFrequency=collectionFrequency+15;
                String year = getNotNullString(rs.getString(1));
                String price = getNotNullString(rs.getString(2));
                String mileage = getNotNullString(rs.getString(3));
                String engineDescription = getNotNullString(rs.getString(4));
                String color = getNotNullString(rs.getString(5));
                String doorCount = getNotNullString(rs.getString(6));
                String driveTrain = getNotNullString(rs.getString(7));
                String bodyStyle = getNotNullString(rs.getString(8));
                String transmission = getNotNullString(rs.getString(9));
                String RecordID = getNotNullString(rs.getString(10));
                Index(Constants.RECORDID, RecordID, Constants.YEAR, year, Constants.PRICE,
                        price, Constants.MILEAGE, mileage, Constants.ENGINEDESCRIPTION, engineDescription,
                        Constants.COLOR, color, Constants.DOORCOUNT, doorCount, Constants.DRIVETRAIN, driveTrain,
                        Constants.BODYSTYLE, bodyStyle, Constants.TRANSMISSION, transmission);
            }
            rs.close();
            dbo.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getNotNullString(String value){
        if(value==null){
            return "";
        }else{
            return value;
        }
    }
    public void Index(String indexType, String index,
            String type1, String s1, String type2, String s2, String type3, String s3, String type4, String s4, String type5, String s5, String type6, String s6,
            String type7, String s7, String type8, String s8, String type9, String s9) throws IOException {
        Document doc = new Document();
        doc.add(new Field(indexType, index, Field.Store.YES,
                Field.Index.NOT_ANALYZED));
        doc.add(new Field(indexType, index, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type1, s1, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type2, s2, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type3, s3, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type4, s4, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type5, s5, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type6, s6, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type7, s7, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type8, s8, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        doc.add(new Field(type9, s9, Field.Store.NO, Field.Index.ANALYZED,
                Field.TermVector.YES));
        ixwriter.addDocument(doc);
    }

    public void close() throws Exception {
        BufferedWriter outputCF = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Constants.IndexDirectoryPath
                        + Constants.CollectionFrequencyPath), "UTF-8"));
        outputCF.write(String.valueOf(collectionFrequency));
        outputCF.close();
        ixwriter.close();
        directory.close();
    }

    public static void main(String[] args) {
        try {
            indexWritter index = new indexWritter();
            index.TokenizeAll();
            index.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
