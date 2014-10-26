

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import common.Constants;
import com.carsFinder.dao.DBO;
import java.sql.ResultSet;
import java.util.TreeMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import com.carsFinder.entity.CarInfo;
public class GetRecordId {

    ResultSet rs;
    MyRetrievalModel myRetrievalModel;
    public List<CarInfo> start(String recordId) throws Exception {
        String[] ids=recordId.split(" ");
        indexReader ixreader1 = null;
        indexReader ixreader2 = null;
        indexReader ixreader3 = null;
        indexReader ixreader4 = null;
        indexReader ixreader5 = null;
        indexReader ixreader6 = null;
        indexReader ixreader7 = null;
        indexReader ixreader8 = null;
        indexReader ixreader9 = null;
        String year = "";
        String price = "";
        String mileage = "";
        String engineDescription = "";
        String color = "";
        String doorCount = "";
        String driveTrain = "";
        String bodyStyle = "";
        String transmission = "";
        try {
            DBO dbo = new DBO();
            dbo.open();
            for(int i=0;i<ids.length;i++){
	            rs = dbo.executeQuery("SELECT year,price,mileage,engineDescription,color,doorCount,driveTrain,bodyStyle,transmission FROM record WHERE recordId=" + ids[i]);
	            while (rs.next()) {
	                year += getNotNullString(rs.getString(1))+" ";
	                price += getNotNullString(rs.getString(2))+" ";
	                mileage += getNotNullString(rs.getString(3))+" ";
	                engineDescription += getNotNullString(rs.getString(4))+" ";
	                color += getNotNullString(rs.getString(5))+" ";
	                doorCount += getNotNullString(rs.getString(6))+" ";
	                driveTrain += getNotNullString(rs.getString(7))+" ";
	                bodyStyle += getNotNullString(rs.getString(8))+" ";
	                transmission += getNotNullString(rs.getString(9))+" ";
	                
	            }
        	}
            ixreader1 = new indexReader(Constants.YEAR, Constants.RECORDID);
            ixreader2 = new indexReader(Constants.PRICE, Constants.RECORDID);
            ixreader3 = new indexReader(Constants.MILEAGE, Constants.RECORDID);
            ixreader4 = new indexReader(Constants.ENGINEDESCRIPTION, Constants.RECORDID);
            ixreader5 = new indexReader(Constants.COLOR, Constants.RECORDID);
            ixreader6 = new indexReader(Constants.DOORCOUNT, Constants.RECORDID);
            ixreader7 = new indexReader(Constants.DRIVETRAIN, Constants.RECORDID);
            ixreader8 = new indexReader(Constants.BODYSTYLE, Constants.RECORDID);
            ixreader9 = new indexReader(Constants.TRANSMISSION, Constants.RECORDID);
            rs.close();
            dbo.close();

        } catch (Exception e) {
            System.out.println("ERROR: cannot initiate index directory.");
            e.printStackTrace();
        }

        myRetrievalModel = new MyRetrievalModel();
        Map<String, Double> resultMap = new TreeMap<String, Double>();
        getDocumentScore(year,resultMap,ixreader1);
        getDocumentScore(price,resultMap,ixreader2);
        getDocumentScore(mileage,resultMap,ixreader3);
        getDocumentScore(engineDescription,resultMap,ixreader4);
        getDocumentScore(color,resultMap,ixreader5);
        getDocumentScore(doorCount,resultMap,ixreader6);
        getDocumentScore(driveTrain,resultMap,ixreader7);
        getDocumentScore(bodyStyle,resultMap,ixreader8);
        getDocumentScore(transmission,resultMap,ixreader9);
        int i=0;
        List<String> recordIdList=new LinkedList<String>();
        Map<String, Double> sortedMap=sortByComparator(resultMap);
        for ( Map.Entry entry : sortedMap.entrySet()) {
            if(i==10){
                break;
            }else{
                recordIdList.add((String)entry.getKey());
            }
            i++;
        }
        List<CarInfo> cars=new ArrayList<CarInfo>();
        try {
            DBO dbo = new DBO();
            dbo.open();
            for(int j=0;j<recordIdList.size();j++){
	            rs = dbo.executeQuery("SELECT * FROM record WHERE recordId=" + recordIdList.get(j));
	            while (rs.next()) {
	                CarInfo car=new CarInfo();
                        car.setRecordID(Integer.valueOf(getNotNullString(rs.getString(1))));
                        car.setCarName(getNotNullString(rs.getString(2)));
	                car.setYear(getNotNullString(rs.getString(3)));
                        car.setPrice(getNotNullString(rs.getString(4)));
                        car.setMileage(getNotNullString(rs.getString(5)));
                        car.setEngineDescription(getNotNullString(rs.getString(6)));
                        car.setColor(getNotNullString(rs.getString(7)));
                        car.setDoorCount(getNotNullString(rs.getString(8)));
                        car.setDriveTrain(getNotNullString(rs.getString(9)));
                        car.setBodyStyle(getNotNullString(rs.getString(10)));
                        car.setTransmission(getNotNullString(rs.getString(11)));
                        car.setImageUrl(getNotNullString(rs.getString(12)));
                        cars.add(car);
	            }
        	}
            rs.close();
            dbo.close();

        } catch (Exception e) {
            System.out.println("ERROR: cannot initiate index directory.");
            e.printStackTrace();
        }
        return cars;
    }

    private void getDocumentScore(String field,Map<String, Double> documentScore, indexReader reader) throws Exception {
        myRetrievalModel.setIndex(reader);
        Map<Integer, Double> fieldScore = myRetrievalModel.search(field, 50);
        for ( Map.Entry entry : fieldScore.entrySet()) {
            if(documentScore.containsKey(reader.getNodeId((Integer)entry.getKey()))){
                documentScore.put(reader.getNodeId((Integer)entry.getKey()), documentScore.get(reader.getNodeId((Integer)entry.getKey()))+(Double)entry.getValue());
            }else{
                documentScore.put(reader.getNodeId((Integer)entry.getKey()),(Double)entry.getValue());
            }
        }
    }

    private String getNotNullString(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }

    private static Map sortByComparator(Map unsortMap) {
		// Sort map by value
        // these codes come from internet
        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

		// put sorted list into map again
        // LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static void main(String args[]) throws Exception {
        GetRecordId test = new GetRecordId();
        test.start("5 4");
    }
}
