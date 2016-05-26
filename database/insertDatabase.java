import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

// import com.gemstone.gemfire.DataSerializable;
// import com.gemstone.gemfire.cache.Region;
// import com.gemstone.gemfire.cache.client.*;
// import com.gemstone.gemfire.cache.query.FunctionDomainException;
// import com.gemstone.gemfire.cache.query.NameResolutionException;
// import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
// import com.gemstone.gemfire.cache.query.TypeMismatchException;
// import com.gemstone.gemfire.DataSerializable;
// import com.gemstone.gemfire.Instantiator;
// import com.gemstone.gemfire.internal.lang.ObjectUtils;
// import com.gemstone.gemfire.pdx.PdxReader;
// import com.gemstone.gemfire.pdx.PdxSerializable;
// import com.gemstone.gemfire.pdx.PdxWriter;


 
public class insertDatabase {
    public static void csvtoRegion(String file_name, ) {
        String line = null;
        int data_index = 0;
        try {
            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] split_line = line.split(",");
                for (int index = 0; index < split_line.length; index++) {
                    System.out.println(split_line[index]);
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        //ClientCache cache = new ClientCacheFactory().addPoolLocator("localhost", 10334).create();
        //Region<int, Admission> regionA = cache.<int, Admission>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY).create("regionA");

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
    private admission_type;
}