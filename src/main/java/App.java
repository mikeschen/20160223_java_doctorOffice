import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String [] args){
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String,Object>model = new HashMap<String, Object>();
      // model.put("patients", request.session().attribute("patients"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/patients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("patients", Patient.all());
      model.put("template", "templates/patients.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("patients/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/patients-form.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/patients", (request,response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      Doctor doctor = Doctor.find(Integer.parseInt(request.queryParams("doctorId")));
      String first = request.queryParams("first");
      String last = request.queryParams("last");
      String birthdate = request.queryParams("birthdate");
      Patient newpatient = new Patient(first, last, birthdate, doctor.getId());
      newpatient.save();
      //model.put("doctor", doctor);
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/patients/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Patient patient = Patient.find(Integer.parseInt(request.params(":id")));
      model.put("patient", patient);
      model.put("template", "templates/patient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctors", Doctor.all());
      model.put("template", "templates/doctors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("doctors/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/doctor-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/doctors", (request,response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      String first = request.queryParams("first");
      String last = request.queryParams("last");
      String specialty = request.queryParams("specialty");
      Doctor newdoctor = new Doctor(first, last, specialty);
      newdoctor.save();
      // model.put("doctor", newdoctor);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctor", Doctor.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/doctor.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/doctors/:id/patients/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("doctor", Doctor.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/doctor-patients-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
