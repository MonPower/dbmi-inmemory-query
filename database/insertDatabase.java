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
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory().addPoolLocator("localhost", 10334).create();
        Region<Integer, Admissions> regionA = cache.<Integer, Admissions>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("regionA");
        insertDatabase.insertAdmission("./csv/admissions.csv", regionA);
    }
}

// ADMISSIONS Table class
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
		String[] new_split = new String[17];
		int new_index = 0;
		if (split_line.length > 17) {
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
		/*
		System.out.println("----------------------------------------");
        System.out.println("Keys");
        System.out.println("----------------------------------------");
        for (int index = 0; index < split_line.length; index++) {
            System.out.println(split_line[index]);
        }
		*/
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
