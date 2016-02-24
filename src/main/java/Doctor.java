import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Doctor {
  private int id;
  private String first;
  private String last;
  private String specialty;

  // Date sqlBirthDate = java.sql.Date.valueOf(birthdate);

  public int getId() {
    return id;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public String getSpecialty() {
    return specialty;
  }


  public Doctor(String first, String last, String specialty) {
    this.first = first;
    this.last = last;
    this.specialty = specialty;
  }

  public static List<Doctor> all() {
    String sql = "SELECT id, first, last, specialty FROM Doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctor) {
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getFirst().equals(newDoctor.getFirst()) &&
        this.getLast().equals(newDoctor.getLast()) &&
        this.getSpecialty().equals(newDoctor.getSpecialty()) &&
        this.getId() == newDoctor.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors(first, last, specialty) VALUES (:first, :last, :specialty)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first", this.first)
        .addParameter("last", this.last)
        .addParameter("specialty", this.specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Doctors where id=:id";
      Doctor doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
      return doctor;
    }
  }

  public List<Patient> getPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctorid=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Patient.class);
    }
  }
}

// public class Category {
//   private int id;
//   private String name;
//
//   public int getId() {
//     return id;
//   }
//
//   public String getName() {
//     return name;
//   }
//
//   public Category(String name) {
//     this.name = name;
//   }
//
//   public static List<Category> all() {
//     String sql = "SELECT id, name FROM Categories ORDER by name ASC";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Category.class);
//     }
//   }
//
//   @Override
//   public boolean equals(Object otherCategory){
//     if (!(otherCategory instanceof Category)) {
//       return false;
//     } else {
//       Category newCategory = (Category) otherCategory;
//       return this.getName().equals(newCategory.getName());
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO Categories(name) VALUES (:name)";
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("name", this.name)
//         .executeUpdate()
//         .getKey();
//     }
//   }
//
//   public static Category find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM Categories where id=:id";
//       Category Category = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Category.class);
//       return Category;
//     }
//   }
//
//   public List<Task> getTasks() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM tasks where categoryId=:id";
//       return con.createQuery(sql)
//         .addParameter("id", this.id)
//         .executeAndFetch(Task.class);
//     }
//   }
// }
