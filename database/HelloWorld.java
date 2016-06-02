import com.gemstone.gemfire.DataSerializable;
import java.util.Map;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.*;
import com.gemstone.gemfire.DataSerializable;
import com.gemstone.gemfire.Instantiator;
import java.util.Map.Entry;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.TypeMismatchException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.gemstone.gemfire.DataSerializable;
import com.gemstone.gemfire.Instantiator; 
 import com.gemstone.gemfire.internal.lang.ObjectUtils;
 import com.gemstone.gemfire.pdx.PdxReader;
 import com.gemstone.gemfire.pdx.PdxSerializable;
 import com.gemstone.gemfire.pdx.PdxWriter;

public class HelloWorld {
  public static void main(String[] args) throws Exception {
    ClientCache cache = new ClientCacheFactory()
      .addPoolLocator("localhost", 10334)
      .create();
    Region<String, Person> region = cache
      .<String, Person>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
	  .create("regionA");
 
    Person p = new Person(102L,  "Sachin",  "Ramesh",  "Tendulkar");
	region.put("4", p);
    for (Entry<String, Person> entry : region.entrySet()) {
        System.out.format("key = %s, value = %s\n", entry.getKey(),
                entry.getValue()); 
    }
	cache.close();
  }
}
/**
 * The Person class is an abstraction modeling a person.
 */

class Person  implements PdxSerializable /*ResourceSupport  implements DomainObject< Long>*/  {

   //private static final long serialVersionUID = 42108163264l;

   protected static final String DOB_FORMAT_PATTERN =  "MM/dd/yyyy";

   private Long id;



   private String firstName;
   private String middleName;
   private String lastName;

   public Person() {
  }

   public Person( final Long id) {
     this.id = id;
  }

   public Person( final String firstName,  final String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
  }
  
   public Person( final Long id,  final String firstName,  final String middleName,  final String lastName) {
     this.id = id;
     this.firstName = firstName;
     this.middleName = middleName;
     this.lastName = lastName;
  }
/*
   public Long getId() {
     return id;
  }

   public void setId( final Long id) {
     this.id = id;
  }

   public String getFirstName() {
     return firstName;
  }

   public void setFirstName( final String firstName) {
     this.firstName = firstName;
  }

   public String getLastName() {
     return lastName;
  }

   public void setLastName( final String lastName) {
     this.lastName = lastName;
  }

   public String getMiddleName() {
     return middleName;
  }

   public void setMiddleName( final String middleName) {
     this.middleName = middleName;
  }


  @Override
   public boolean equals( final Object obj) {
     if (obj ==  this) {
       return true;
    }

     if (!(obj  instanceof Person)) {
       return false;
    }

     final Person that = (Person) obj;

     return (ObjectUtils.equals( this.getId(), that.getId())
      || (
      ObjectUtils.equals( this.getLastName(), that.getLastName())
      && ObjectUtils.equals( this.getFirstName(), that.getFirstName())));
  }

  @Override
   public int hashCode() {
     int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getLastName());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getFirstName());
     return hashValue;
  }

  @Override
   public String toString() {
     final StringBuilder buffer =  new StringBuilder( "{ type = ");
    buffer.append(getClass().getName());
    buffer.append( ", id = ").append(getId());
    buffer.append( ", firstName = ").append(getFirstName());
    buffer.append( ", middleName = ").append(getMiddleName());
    buffer.append( ", lastName = ").append(getLastName());
    buffer.append( " }");
     return buffer.toString();
  }
*/
  @Override
   public void fromData(PdxReader pr) {
	
    id = pr.readLong( "id");
    firstName = pr.readString( "firstName");
    middleName = pr.readString( "middleName");
    lastName = pr.readString( "lastName");
  }

  @Override
   public void toData(PdxWriter pw) {
    pw.writeLong( "id", id);
    pw.writeString( "firstName", firstName);
    pw.writeString( "middleName", middleName);
    pw.writeString( "lastName", lastName);
  }

}
