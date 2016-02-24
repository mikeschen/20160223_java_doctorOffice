import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

// public class AppTest extends FluentTest {
  // public WebDriver webDriver = new HtmlUnitDriver();
  //
  // @Override
  // public WebDriver getDefaultDriver() {
  //     return webDriver;
  // }
  //
  // @ClassRule
  // public static ServerRule server = new ServerRule();
  //
  // @Test
  // public void rootTest() {
  //     goTo("http://localhost:4567/");
  //     assertThat(pageSource()).contains("Task list");
  // }

//   @Test
//   public void categoryIsCreatedTest() {
//     goTo("http://localhost:4567/");
//     click("a", withText("Add a New Category"));
//     fill("#name").with("Household chores");
//     submit(".btn");
//     assertThat(pageSource()).contains("Your category has been saved.");
//   }
//
//   @Test
//   public void categoryIsDisplayedTest() {
//     Category myCategory = new Category("Household chores");
//     String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
//     goTo(categoryPath);
//     assertThat(pageSource()).contains("Household chores");
//   }
//
// @Test
// public void allTasksDisplayDescriptionOnCategoryPage() {
//   Category myCategory = new Category("Household chores");
//   myCategory.save();
//   Task firstTask = new Task("Mow the lawn", myCategory.getId());
//   firstTask.save();
//   Task secondTask = new Task("Do the dishes", myCategory.getId());
//   secondTask.save();
//   String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
//   goTo(categoryPath);
//   assertThat(pageSource()).contains("Mow the lawn");
//   assertThat(pageSource()).contains("Do the dishes");
// }
// }
