package Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Reminder {

    int reminderId;
    String dateTake;
    String timeTake;
    int patientId;
    int prescriptionId;
    String adhered;

    public Reminder(int reminderId, String dateTake, String timeTake, int patientId, int prescriptionId, String adhered) {
        this.reminderId = reminderId;
        this.dateTake = dateTake;
        this.timeTake = timeTake;
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.adhered = adhered;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getDateTake() {
        return dateTake;
    }

    public void setDateTake(String dateTake) {
        this.dateTake = dateTake;
    }

    public String getTimeTake() {
        return timeTake;
    }

    public void setTimeTake(String timeTake) {
        this.timeTake = timeTake;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getAdhered() {
        return adhered;
    }

    public void setAdhered(String adhered) {
        this.adhered = adhered;
    }


    public static JsonArray getRequestBuilder(String queryMethod, String paramName, String param) {
//        final String[] returnStatement = new String[1];
        final OkHttpClient client = new OkHttpClient();


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("epione-dialogflow.herokuapp.com")
                .port(80)
                .addPathSegment("database")
                .addPathSegment(queryMethod)
                .addQueryParameter(paramName, param)
                .build();

        System.out.println("Ev3Dev log url " + httpUrl.toString());


        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response responses = null;


        try {
            responses = client.newCall(requesthttp).execute();

//
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonData = null;


        try {
            jsonData = responses.body().string();
            System.out.println("Sys call");
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonData);
        return jsonArray;


    }

    public static JsonObject getRequestBuilderParameter2(String queryMethod, String paramName, String param, String paramName2, String param2) {
//        final String[] returnStatement = new String[1];
        final OkHttpClient client = new OkHttpClient();


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("epione-dialogflow.herokuapp.com")
                .port(80)
                .addPathSegment("database")
                .addPathSegment(queryMethod)
                .addQueryParameter(paramName, param)
                .addQueryParameter(paramName2, param2)
                .build();

        System.out.println("Ev3Dev log url " + httpUrl.toString());


        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response responses = null;


        try {
            responses = client.newCall(requesthttp).execute();

//
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonData = null;


        try {
            jsonData = responses.body().string();
            System.out.println("Sys call");
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonArray = (JsonObject) jsonParser.parse(jsonData);
        return jsonArray;


    }


    public static JsonObject getRequestBuilderParameterReminderInsert(String dateTake, String timeTake, String patientId, String prescriptionId, String adhered) {
//        final String[] returnStatement = new String[1];
        final OkHttpClient client = new OkHttpClient();


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("epione-dialogflow.herokuapp.com")
                .port(80)
                .addPathSegment("database")
                .addPathSegment("insertDataIntoReminder")
                .addQueryParameter("dateTake", dateTake)
                .addQueryParameter("timeTake", timeTake)
                .addQueryParameter("patientId", patientId)
                .addQueryParameter("prescriptionId", prescriptionId)
                .addQueryParameter("adhered", adhered)
                .build();

        System.out.println("Ev3Dev log url " + httpUrl.toString());


        Request requesthttp = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response responses = null;


        try {
            responses = client.newCall(requesthttp).execute();

//
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonData = null;


        try {
            jsonData = responses.body().string();
            System.out.println("Sys call");
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonParser jsonParser = new JsonParser();
        JsonObject jsonArray = (JsonObject) jsonParser.parse(jsonData);
        return jsonArray;


    }


    public static List<Reminder> getAllReminder() {
        List<Reminder> allpat = new ArrayList<>();
        JsonArray jsArr = getRequestBuilder("getAllReminder", "", "");

        for (int i = 0; i < jsArr.size(); i++) {
            JsonObject jobj = (JsonObject) jsArr.get(i);
            System.out.println(jobj);

            Reminder obj = new Reminder(jobj.get("reminderId").getAsInt(), jobj.get("dateTake").getAsString(), jobj.get("timeTake").getAsString(), jobj.get("patientId").getAsInt(), jobj.get("prescriptionId").getAsInt(), jobj.get("adhered").getAsString());


            allpat.add(obj);


        }
        return allpat;
    }

    public static List<Reminder> getAllReminderById(String para) {
        List<Reminder> allpat = new ArrayList<>();
        JsonArray jsArr = getRequestBuilder("getAllReminderById", "reminderId", para);

        for (int i = 0; i < jsArr.size(); i++) {
            JsonObject jobj = (JsonObject) jsArr.get(i);
            System.out.println(jobj);

            Reminder obj = new Reminder(jobj.get("reminderId").getAsInt(), jobj.get("dateTake").getAsString(), jobj.get("timeTake").getAsString(), jobj.get("patientId").getAsInt(), jobj.get("prescriptionId").getAsInt(), jobj.get("adhered").getAsString());


            allpat.add(obj);


        }
        return allpat;
    }


    public static List<Reminder> getAllReminderByPatientId(String para) {
        List<Reminder> allpat = new ArrayList<>();
        JsonArray jsArr = getRequestBuilder("getAllReminderByPatientId", "patientId", para);

        for (int i = 0; i < jsArr.size(); i++) {
            JsonObject jobj = (JsonObject) jsArr.get(i);
            System.out.println(jobj);

            Reminder obj = new Reminder(jobj.get("reminderId").getAsInt(), jobj.get("dateTake").getAsString(), jobj.get("timeTake").getAsString(), jobj.get("patientId").getAsInt(), jobj.get("prescriptionId").getAsInt(), jobj.get("adhered").getAsString());


            allpat.add(obj);


        }
        return allpat;
    }

    public static List<Reminder> getAllReminderByDate(String para) {
        List<Reminder> allpat = new ArrayList<>();
        JsonArray jsArr = getRequestBuilder("getAllReminderByDate", "dateTake", para);

        for (int i = 0; i < jsArr.size(); i++) {
            JsonObject jobj = (JsonObject) jsArr.get(i);
            System.out.println(jobj);

            Reminder obj = new Reminder(jobj.get("reminderId").getAsInt(), jobj.get("dateTake").getAsString(), jobj.get("timeTake").getAsString(), jobj.get("patientId").getAsInt(), jobj.get("prescriptionId").getAsInt(), jobj.get("adhered").getAsString());


            allpat.add(obj);


        }
        return allpat;
    }


    public static String updateReminderPrescriptionTaken(String reminderId, String adherence) {

        JsonObject te = getRequestBuilderParameter2("updateReminderPrescriptionTaken", "setAdhered", adherence, "reminderId", reminderId);

        String ret = te.get("message").getAsString();
        return ret;

    }

    public static String insertDataIntoReminder(String patientId, String prescriptionId, int amtToTakeEachDay, int timesToTakeEachDay, int medicineQuantity) {

        int hrsInDay = 24;
        int initTime = 0;


        int hrsToAdd = hrsInDay / timesToTakeEachDay;
        int howManyTimesToLoop = medicineQuantity / amtToTakeEachDay;


        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("HHmm");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, initTime);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);


        ArrayList<ArrayList<String>> dateTimeAll = new ArrayList<>();
        for (int i = 0; i < howManyTimesToLoop; i++) {
            ArrayList<String> dateTimeEach = new ArrayList<>();
            cal.add(Calendar.HOUR_OF_DAY, hrsToAdd);


            System.out.println(cal.getTime());
            System.out.println(formatterDate.format(cal.getTime()));
            System.out.println(formatterTime.format(cal.getTime()));

            dateTimeEach.add(formatterDate.format(cal.getTime()));
            dateTimeEach.add(formatterTime.format(cal.getTime()));

            dateTimeAll.add(dateTimeEach);
        }
        String ret = "Empty";
        for (int i = 0; i < dateTimeAll.size(); i++) {

            System.out.println(dateTimeAll.get(i).get(0));
            System.out.println(dateTimeAll.get(i).get(1));

            JsonObject te = getRequestBuilderParameterReminderInsert(dateTimeAll.get(i).get(0), dateTimeAll.get(i).get(1), patientId, prescriptionId, "false");

            ret = te.get("message").getAsString();
            System.out.println("------------");

        }


        return ret;

    }


    public static void main(String[] args) {
        insertDataIntoReminder("1", "1", 3, 3, 30);

//        List<Reminder> arr = getAllReminderByDate("11/6/2018");
//
//        for(int i=0;i<arr.size();i++){
//            System.out.println(arr.get(i).getReminderId());
//            System.out.println(arr.get(i).getDateTake());
//            System.out.println(arr.get(i).getTimeTake());
//            System.out.println(arr.get(i).getPatientId());
//            System.out.println(arr.get(i).getPrescriptionId());
//            System.out.println(arr.get(i).getAdhered());
//
//
//        }

//        System.out.println(insertDataIntoReminder());

        // time calculator


    }


}
