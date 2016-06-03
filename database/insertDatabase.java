import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

import com.gemstone.gemfire.DataSerializable;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.TypeMismatchException;
import com.gemstone.gemfire.DataSerializable;
import com.gemstone.gemfire.Instantiator;
import com.gemstone.gemfire.internal.lang.ObjectUtils;
import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;


 
public class insertDatabase {
    public static void insertAdmission(String file_name, Region<Integer, Admissions> regionA) {
        String line = null;
        int data_index = 0;
        try {
            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] split_line = line.split(",");
                Admissions admission = new Admissions(split_line);
                regionA.put(data_index, admission);
                data_index += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseCSV(String[] split_line, int num_args) {
        String[] new_split = new String[num_args];
        int new_index = 0;
        if (split_line.length > num_args) {
            for (int index = 0; index < split_line.length; index++) {
                if (split_line[index].length() > 0 && split_line[index].charAt(0) == '"' && split_line[index].charAt(split_line[index].length() - 1) != '"') {
                    String tempString = split_line[index];
                    index += 1;
                    while (index < split_line.length) {
                        if (split_line[index].length() > 0 && split_line[index].charAt(split_line[index].length() - 1) != '"') {
                            tempString += split_line[index];
                        }
                        else {
                            tempString += split_line[index];
                            break;
                        }
                        index += 1;
                    }
                    new_split[new_index] = tempString;
                }
                else {
                    new_split[new_index] = split_line[index];
                }
                new_index += 1;
            }
            split_line = new_split;
        }
    }

    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory().addPoolLocator("localhost", 10334).create();
        Region<Integer, Admissions> regionA = cache.<Integer, Admissions>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("regionA");
        insertDatabase.insertAdmission("./csv/admissions.csv", regionA);
    }
}

/* 
ADMISSIONS Table class
*/
class Admissions implements PdxSerializable{
    private int row_id;
    private int subject_id;
    private int hadm_id;
    private String admittime;
    private String dischtime;
    private String deathtime;
    private String admission_type;
    private String admission_location;
    private String discharge_location;
    private String insurance;
    private String language;
    private String religion;
    private String maritial_status;
    private String ethnicity;
    private String diagnosis;
    private int has_inevents_data;
    private int has_chartevents_data;

    public Admissions(String[] split_line) {
        int admission_colums = 17;
        insertDatabase.parseCSV(split_line, admission_colums);
        this.row_id = Integer.parseInt(split_line[0]);
        this.subject_id = Integer.parseInt(split_line[1]);
        this.hadm_id = Integer.parseInt(split_line[2]);
        this.admittime = split_line[3];
        this.dischtime = split_line[4];
        this.deathtime = split_line[5];
        this.admission_type = split_line[6];
        this.admission_location = split_line[7];
        this.discharge_location = split_line[8];
        this.insurance = split_line[9];
        this.language = split_line[10];
        this.religion = split_line[11];
        this.maritial_status = split_line[12];
        this.ethnicity = split_line[13];
        this.diagnosis = split_line[14];
        this.has_inevents_data = Integer.parseInt(split_line[15]);
        this.has_chartevents_data = Integer.parseInt(split_line[16]);
    }

    public Admissions(final int row_id, final int subject_id, final int hadm_id, final String admittime,
        final String dischtime, final String deathtime, final String admission_type, final String admission_location,
        final String discharge_location, final String insurance, final String language, final String religion,
        final String maritial_status, final String ethnicity, final String diagnosis, final int has_inevents_data, final int has_chartevents_data) {
        this.row_id = row_id;
        this.subject_id = subject_id;
        this.hadm_id = hadm_id;
        this.admittime = admittime;
        this.dischtime = dischtime;
        this.deathtime = deathtime;
        this.admission_type = admission_type;
        this.admission_location = admission_location;
        this.discharge_location = discharge_location;
        this.insurance = insurance;
        this.language = language;
        this.religion = religion;
        this.maritial_status = maritial_status;
        this.ethnicity = ethnicity;
        this.diagnosis = diagnosis;
        this.has_inevents_data = has_inevents_data;
        this.has_chartevents_data = has_chartevents_data;
    }

    @Override
    public void fromData(PdxReader pr) {
        row_id = pr.readInt("row_id");
        subject_id = pr.readInt("subject_id");
        hadm_id = pr.readInt("hadm_id");
        admittime = pr.readString("admittime");
        dischtime = pr.readString("dischtime");
        deathtime = pr.readString("deathtime");
        admission_type = pr.readString("admission_type");
        admission_location = pr.readString("admission_location");
        discharge_location = pr.readString("discharge_location");
        insurance = pr.readString("insurance");
        language = pr.readString("language");
        religion = pr.readString("religion");
        maritial_status = pr.readString("maritial_status");
        ethnicity = pr.readString("ethnicity");
        diagnosis = pr.readString("diagnosis");
        has_inevents_data = pr.readInt("has_inevents_data");
        has_chartevents_data = pr.readInt("has_chartevents_data");
    }

    @Override
    public void toData(PdxWriter pw) {
        pw.writeInt("row_id", row_id);
        pw.writeInt("subject_id", subject_id);
        pw.writeInt("hadm_id", hadm_id);
        pw.writeString("admittime", admittime);
        pw.writeString("dischtime", dischtime);
        pw.writeString("deathtime", deathtime);
        pw.writeString("admission_type", admission_type);
        pw.writeString("admission_location", admission_location);
        pw.writeString("discharge_location", discharge_location);
        pw.writeString("insurance", insurance);
        pw.writeString("language", language);
        pw.writeString("religion", religion);
        pw.writeString("maritial_status", maritial_status);
        pw.writeString("ethnicity", ethnicity);
        pw.writeString("diagnosis", diagnosis);
        pw.writeInt("has_inevents_data", has_inevents_data);
        pw.writeInt("has_chartevents_data", has_chartevents_data);

    }
}


/* 
CALLOUT table class
*/
class Callout implements PdxSerializable{
    private int row_id;
    private int subject_id;
    private int hadm_id;
    private int submit_wardid;
    private String submit_careunit;
    private int curr_wardid;
    private String curr_careunit;
    private int callout_wardid;
    private String callout_service;
    private int request_tele;
    private int request_resp;
    private int request_cdiff;
    private int request_mrsa;
    private int request_vre;
    private String callout_status;
    private String callout_outcome;
    private int discharge_wardid;
    private String acknowledge_status;
    private String createtime;
    private String updatetime;
    private String acknowledgetime;
    private String outcometime;
    private String firstreservationtime;
    private String currentreservationtime;

    public Callout(String[] split_line) {
        int callout_columns = 24;
        insertDatabase.parseCSV(split_line, callout_columns);
        row_id = split_line[0];
        subject_id = split_line[1];
        hadm_id = split_line[2];
        submit_wardid = split_line[3];
        submit_careunit = split_line[4];
        curr_wardid = split_line[5];
        curr_careunit = split_line[6];
        callout_wardid = split_line[7];
        callout_service = split_line[8];
        request_tele = split_line[9];
        request_resp = split_line[10];
        request_cdiff = split_line[11];
        request_mrsa = split_line[12];
        request_vre = split_line[13];
        callout_status = split_line[14];
        callout_outcome = split_line[15];
        discharge_wardid = split_line[16];
        acknowledge_status = split_line[17];
        createtime = split_line[18];
        updatetime = split_line[19];
        acknowledgetime = split_line[20];
        outcometime = split_line[21];
        firstreservationtime = split_line[22];
        currentreservationtime = split_line[23];
    }

    @Override
    public void fromData(PdxReader pr) {
        row_id = pr.readInt("row_id");
        subject_id = pr.readInt("subject_id");
        hadm_id = pr.readInt("hadm_id");
        submit_wardid = pr.readInt("submit_wardid");
        submit_careunit = pr.writeString("submit_careunit");
        curr_wardid = pr.readInt("curr_wardid");
        curr_careunit = pr.readString("curr_careunit");
        callout_wardid = pr.readInt("callout_wardid");
        callout_service = pr.readString("callout_service");
        request_tele = pr.readInt("request_tele");
        request_resp = pr.readInt("request_tele");
        request_cdiff = pr.readInt("request_resp");
        request_mrsa = pr.readInt("request_mrsa");
        request_vre = pr.readInt("request_vre");
        callout_status = pr.readString("callout_status");
        callout_outcome = pr.readString("callout_outcome");
        discharge_wardid = pr.readInt("discharge_wardid");
        createtime = pr.readString("createtime");
        updatetime = pr.readString("updatetime");
        acknowledgetime = pr.readString("acknowledgetime");
        outcometime = pr.readString("outcometime");
        firstreservationtime = pr.readString("firstreservationtime");
        currentreservationtime = pr.readString("currentreservationtime");
    }

    @Override
    public void toData(PdxWriter pw) {
        pw.writeInt("row_id". row_id);
        pw.writeInt("subject_id", subject_id);
        pw.writeInt("hadm_id", hadm_id);
        pw.writeInt("submit_wardid", submit_wardid);
        pw.writeString("submit_careunit", submit_careunit);
        pw.writeInt("curr_wardid", curr_wardid);
        pw.writeString("curr_careunit", curr_careunit);
        pw.writeInt("callout_wardid", callout_wardid);
        pw.writeString("callout_service", callout_service);
        pw.writeInt("request_tele", request_tele);
        pw.writeInt("request_resp", request_resp);
        pw.writeInt("request_cdiff", request_cdiff);
        pw.writeInt("request_mrsa", request_mrsa);
        pw.writeInt("request_vre"m request_vre);
        pw.writeString("callout_status", callout_status);
        pw.writeString("callout_outcome", callout_outcome);
        pw.writeInt("discharge_wardid", discharge_wardid);
        pw.writeString("acknowledge_status", acknowledge_status);
        pw.writeString("createtime", createtime);
        pw.writeString("updatetime", updatetime);
        pw.writeString("acknowledgetime", acknowledgetime);
        pw.writeString("outcometime", outcometime);
        pw.writeString("firstreservationtime", firstreservationtime);
        pw.writeString("currentreservationtime", currentreservationtime);
    }
}