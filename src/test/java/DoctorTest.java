import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("Doogie", "Howser", "general");
    Doctor secondDoctor = new Doctor("Doogie", "Howser", "general");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
     Doctor newDoctor = new Doctor("Doogie", "Howser", "general");
     newDoctor.save();
     assertTrue(Doctor.all().get(0).equals(newDoctor));
 }

 @Test
 public void find_findDoctorInDataBase_true() {
   Doctor newDoctor = new Doctor("Doogie", "Howser", "general");
   newDoctor.save();
   Doctor savedDoctor = Doctor.find(newDoctor.getId());
   assertTrue(newDoctor.equals(savedDoctor));
 }

 @Test
 public void getPatients_retrievesAllpatients() {
   Doctor myDoctor = new Doctor("Doogie", "Howser", "family");
   myDoctor.save();
   Patient firstPatient = new Patient("Joe", "Blow", "1965-05-01", myDoctor.getId());
   firstPatient.save();
   Patient secondPatient = new Patient("John", "Low", "1970-05-01",  myDoctor.getId());
   secondPatient.save();
   Patient[] patients = new Patient[] { firstPatient, secondPatient };
   assertTrue(myDoctor.getPatients().containsAll(Arrays.asList(patients)));
 }
}
