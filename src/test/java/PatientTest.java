import org.junit.Rule;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionAreTheSame() {
    Patient firstPatient = new Patient("Joe", "Blow", "1965-05-01", 1);
    Patient secondPatient = new Patient("Joe", "Blow", "1965-05-01", 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_returnsTrue() {
    Patient newPatient = new Patient("Joe", "Blow", "1965-05-01", 1);
    newPatient.save();
    assertTrue(Patient.all().get(0).equals(newPatient));
  }

  @Test
  public void save_assignsIdToObject() {
    Patient newPatient = new Patient("Joe", "Blow", "1965-05-01", 1);
    newPatient.save();
    Patient savedPatient = Patient.all().get(0);
    assertEquals(newPatient.getId(), savedPatient.getId());
  }

  @Test
  public void find_findsTaskinDB_true() {
    Patient newPatient = new Patient("Joe", "Blow", "1965-05-01", 1);
    newPatient.save();
    Patient savedPatient = Patient.find(newPatient.getId());
    assertTrue(newPatient.equals(savedPatient));
  }
}
