import java.util.List;
import org.sql2o.*;
import java.util.Date;


public class Patient {
  private int id;
  private String first;
  private String last;
  private String birthdate;
  private int doctorId;

  public Patient(String first, String last, String birthdate, int doctorId) {
    this.first = first;
    this.last = last;
    this.birthdate = birthdate;
    this.doctorId = doctorId;
  }

  public int getId() {
    return id;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public int getDoctorId() {
    return doctorId;
  }



  @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getFirst().equals(newPatient.getFirst()) &&
        this.getLast().equals(newPatient.getLast()) &&
        this.getBirthdate().equals(newPatient.getBirthdate()) &&
        this.getId() == newPatient.getId() &&
        this.getDoctorId() == newPatient.getDoctorId();
    }
  }

  public static List<Patient> all() {
    String sql = "SELECT id, first, last, birthdate, doctorId FROM Patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Patients(first, last, birthdate, doctorId) VALUES (:first, :last, :birthdate, :doctorId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first", this.first)
        .addParameter("last", this.last)
        .addParameter("birthdate", this.birthdate)
        .addParameter("doctorId", this.doctorId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Patients where id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
      return patient;
    }
  }
}
